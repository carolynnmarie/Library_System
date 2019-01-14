package io.carolynn.library;

import java.util.*;

public class Library {

    private HashMap<Book, String> books;
    private ArrayList<User> users;
    private Calendar calendarDate;
    private Date date;

    public Library(HashMap<Book, String> books, ArrayList<User> users){
        this.books = books;
        this.users = users;
        this.calendarDate = Calendar.getInstance();
        this.date = calendarDate.getTime();
    }

    public Library(){
        this.books = new HashMap<>();
        this.users = new ArrayList<>();
    }

    public HashMap<Book, String> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, String> books) {
        this.books = books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addBooks(ArrayList<Book> moreBooks){
        HashMap<Book,String> addBooks = new HashMap<>();
        for(Book book: moreBooks){
            addBooks.put(book,"in");
        }
        books.putAll(addBooks);
    }

    public void addBook(Book book){
        books.put(book,"in");
    }

    public String checkOut(Book book){
        User user = findUser();
        String cOutBook = "";
        if(!findBook(book)){
            cOutBook = "The library does not currently have " + book.getName() + ".";
        } else {
            if(!checkAvailability(book)){
                cOutBook = book.getName() + " is currently checked out by another user.";
            } else {
                user.getLibraryCard().addBook(book,date);
                for(Map.Entry<Book,String> entry: books.entrySet()){
                    if(entry.getKey().equals(book)){
                        entry.setValue("out");
                    }
                }
                cOutBook = "You have successfully checked out " + book.getName() + ". It is due back in two weeks.  The late fee is" +
                        " 25 cents per day.";
            }
        }
        return cOutBook;
    }

    private User findUser(){
        String lastName = "";
        String firstName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your last name");
        lastName = scanner.nextLine();
        System.out.println("Please enter your first name");
        firstName = scanner.nextLine();
        User user = new User(lastName,firstName);
        for(User user1: users){
            if(user1.getLastName().equals(lastName) && user1.getFirstName().equals(firstName)){
                user = user1;
            }
        }
        return user;
    }

    private boolean findBook(Book book){
        boolean isPresent = false;
        for(Map.Entry<Book,String> entry: books.entrySet()){
            if(entry.getKey().equals(book)){
                isPresent = true;
            }
        }
        return isPresent;
    }

    private boolean checkAvailability(Book book){
        boolean isAvailable = false;
        for(Map.Entry<Book,String> entry: books.entrySet()){
            if(entry.getKey().equals(book) && entry.getValue().equals("in")){
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    public String returnBook(Book book, User user){
        StringBuilder builder = new StringBuilder();
        int daysOut = 0;
        double fine = 0;
        if(!user.getLibraryCard().getLoans().containsKey(book)){
            builder.append("You do not currently have that book checked out");
        } else {
            daysOut = user.getLibraryCard().getLoans().get(book).compareTo(date);
            builder.append(book.getName())
                    .append(" is successfully returned. ");
            if(daysOut>14){
                fine = (daysOut-14)*.25;
                builder.append("However, you checked ")
                        .append(book.getName())
                        .append(" out more than 2 weeks ago. Your fine is $")
                        .append(fine);
            }
        }
        return builder.toString();
    }


    public String getUserLoans(User user){
        StringBuilder builder = new StringBuilder();
        HashMap<Book,Date> loans = user.getLibraryCard().getLoans();
        if(loans.isEmpty()){
            builder.append("You have no books checked out at this time.");
        } else {
            for(Map.Entry<Book, Date> entry: loans.entrySet()){
                builder.append(entry.getKey().getName())
                        .append(" is due back on ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        return builder.toString();
    }

}

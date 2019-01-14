package io.carolynn.library;

import java.util.*;

public class LibraryCard {

    private HashMap<Book, Date> loans;

    public LibraryCard(){
        this.loans = new HashMap<>();
    }

    public HashMap<Book, Date> getLoans() {
        return loans;
    }

    public void setLoans(HashMap<Book, Date> loans) {
        this.loans = loans;
    }

    public void addBook(Book book, Date date){
        loans.put(book, date);
    }

    public void removeBook(Book book){
        loans.remove(book);
    }

    public Date getCheckoutDate(Book book){
        Date date = new Date();
        for(Map.Entry<Book, Date> bookEntry: loans.entrySet()){
            if(bookEntry.getKey().getName().equals(book.getName())){
                date = bookEntry.getValue();
            }
        }
        return date;
    }
}

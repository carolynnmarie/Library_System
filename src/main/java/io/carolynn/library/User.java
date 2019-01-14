package io.carolynn.library;

public class User {

    private String lastName;
    private String firstName;
    private LibraryCard libraryCard;

    public User(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
        this.libraryCard = new LibraryCard();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }
}

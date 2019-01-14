package io.carolynn.library;

import java.util.*;

public class Book {

    private String name;
    private ArrayList<Author> authors;

    public Book(String name){
        this.name = name;
        this.authors = new ArrayList<>();
    }

    public Book(String name, ArrayList<Author> authors){
        this.authors = authors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, authors);
    }
}

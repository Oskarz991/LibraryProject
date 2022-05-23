package com.example.libraryproject.LibProject;

public class Book {

    public int Id;
    public String Title;
    public String Author;
    public int ISBN;

    public int Quantity;

    public Book(int id,String titel,int isbn,int quantity,String author){
        this.Id=id;
        this.Title=titel;
        this.ISBN = isbn;
        this.Quantity= quantity;
        this.Author=author;
    }
    public Book(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String export (Book book){

        return book.Id + "," + book.Title + "," + book.ISBN +","+book.Quantity +","+book.Author;
    }

    public String loanExport (Book book){
        return book.Title + "," + book.ISBN + "," +book.Author;
    }

}

package com.example.libraryproject.LibProject;

public class Book {

    public int Id;
    public String Title;
    public String Author;
    public int ISBN;

    public int Quantity;

    public Book(int id,String titel,String author,int isbn,int quantity){
        this.Id=id;
        this.Title=titel;
        this.Author=author;
        this.ISBN = isbn;
        this.Quantity= quantity;
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

        return book.Id + "," + book.Title +","+ book.Author + "," + book.ISBN + ","+book.Quantity;
    }

//Här skriver vi metoder

}

package com.example.libraryproject.LibProject;

public class Storage {

    public int Id;
    public String Title;
    public String Author;
    public int ISBN;
    public int Quantity;
    public Boolean BlackList;

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

    public Boolean getBlackList() {
        return BlackList;
    }

    public void setBlackList(Boolean blackList) {
        BlackList = blackList;
    }

    //Här skriver vi metoder

}

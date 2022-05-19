package com.example.libraryproject.LibProject;

public class Librarian {

    public String Name;
    public String SurName;
    public int PNumber;
    public int Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public int getPNumber() {
        return PNumber;
    }

    public void setPNumber(int PNumber) {
        this.PNumber = PNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    // new methods

   public void addUser(String name, String surName, int pNumber, String role){

   }

   public void deleteUser(int id){

   }

   public void giveTimeout(int id){

   }

   public Book getBookByISBN(int ISBN){

        Book temp = new Book();

        return temp;
   }

   public Book lendBook(Book theBook){
        Book temp = new Book();
        return temp;
   }

}

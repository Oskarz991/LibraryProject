package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void addBook(int bookId,String titel,String author,int isbn,int quantity) throws IOException {
        File bookFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
        Scanner bookScan = new Scanner(bookFile).useDelimiter(",");
        ArrayList<Book>bookList = new ArrayList<Book>();
        while (bookScan.hasNext()){
            int Id = Integer.parseInt(bookScan.next());
            String Name = bookScan.next();
            String Author = bookScan.next();
            int ISBN = Integer.parseInt(bookScan.next());
            int Quantity = Integer.parseInt(bookScan.next());
            bookList.add(new Book(Id,Name,Author,ISBN,Quantity));
        }


        Book newBook = new Book(bookId,titel,author,isbn,quantity);


        PrintWriter printWriter = new PrintWriter(bookFile);

        printWriter.println(newBook.export(newBook));

        printWriter.close();

    }

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

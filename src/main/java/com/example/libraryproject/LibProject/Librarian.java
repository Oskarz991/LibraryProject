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
            int ISBN = Integer.parseInt(bookScan.next());
            int Quantity = Integer.parseInt(bookScan.next());
            String Author = bookScan.nextLine();
            Author = Author.replace(",","");
            bookList.add(new Book(Id,Name,ISBN,Quantity,Author));
        }

        Book newBook = new Book(bookId,titel,isbn,quantity,author);

        bookList.add(newBook);


        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i+1; j < bookList.size(); j++)
            if (bookList.get(i).Title.equals(bookList.get(j).Title)){
                bookList.remove(i);
                System.out.println("Duplicate removed");
            }
        }

        PrintWriter printWriter = new PrintWriter(bookFile);

        for (Book books:bookList) {
            printWriter.println(newBook.export(books));
        }

        printWriter.close();

    }

   public void addUser(String name, String surName, int pNumber, String role) throws IOException{
       File userFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");

       Scanner userScan = new Scanner(userFile).useDelimiter(",");

       ArrayList<User>userList = new ArrayList<User>();
       ArrayList<User>blackList = new ArrayList<User>();

       while (userScan.hasNext()){
           String Name = userScan.next();
           String SurName = userScan.next();

           int PNumber = Integer.parseInt(userScan.next());
           int Id = Integer.parseInt(userScan.next());

           int LoanCounter = Integer.parseInt(userScan.next());
           int ViolationCounter = Integer.parseInt(userScan.next());

           String Role = userScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,role));
       }

       int id = (int) (Math.random()*999) +1;
       int loanCounter;
       int violationCounter = 0;

       if (role.equalsIgnoreCase("Undergraduate Student")){
           loanCounter = 3;
           id = id+1000;
       }else if (role.equalsIgnoreCase("Post Graduate student")){
           loanCounter = 5;
           id = id+2000;
       }else if (role.equalsIgnoreCase("PhD Student")){
           loanCounter = 7;
           id = id+3000;
       }else if (role.equalsIgnoreCase("Teacher")){
           loanCounter = 10;
           id = id+4000;
       }else{
           id = id+5000;
           loanCounter = 9999;
       }

       User newUser = new User(name,surName,pNumber,id,loanCounter,violationCounter,role);
       userList.add(newUser);

        File blackfile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
        Scanner blacklisted = new Scanner(blackfile);

       while (blacklisted.hasNext()){
           String Name = blacklisted.next();
           String SurName = blacklisted.next();

           int PNumber = Integer.parseInt(blacklisted.next());
           int Id = Integer.parseInt(blacklisted.next());

           int LoanCounter = Integer.parseInt(blacklisted.next());
           int ViolationCounter = Integer.parseInt(blacklisted.next());

           String Role = blacklisted.nextLine();
           Role = Role.replace(",","");

           blackList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,role));
       }

       for (int i = 0; i < userList.size(); i++) {
           for (int j = i+1; j < blackList.size(); j++)
               if (userList.get(i).equals(userList.get(j))){
                   userList.remove(i);
                   System.out.println("Duplicate removed");
               } else if (userList.get(i).equals(blackList.get(j-1))){
                   userList.remove(i);
                   System.out.println("User is blacklisted");
               }
       }

       PrintWriter printWriter = new PrintWriter(userFile);

       for (User user:userList) {
           printWriter.println(newUser.export(user));
       }

       printWriter.close();

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


    public static void main(String[] args)throws IOException {
        Librarian librarian = new Librarian();
        librarian.addBook(2,"Oskars resor","Stefan",2334,9);

    }

}

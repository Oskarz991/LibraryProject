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
    public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
    public File userLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");


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

    // new methods _____________________________

    public void addBook(int bookId,String titel,int isbn,int quantity,String author) throws IOException {

      //  File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");

        Scanner bookScan = new Scanner(AllBooksFile).useDelimiter(",");

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

        PrintWriter printWriter = new PrintWriter(AllBooksFile);

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

   public Book getBookByISBN(int ISBN)throws IOException{

   //    File bookFileISBN = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");

       Scanner bookScanByISBN = new Scanner(AllBooksFile).useDelimiter(",");

       ArrayList<Book>bookList = new ArrayList<Book>();

       while (bookScanByISBN.hasNext()){
           int Id = Integer.parseInt(bookScanByISBN.next());
           String Name = bookScanByISBN.next();
           int ISBNScan = Integer.parseInt(bookScanByISBN.next());
           int Quantity = Integer.parseInt(bookScanByISBN.next());
           String Author = bookScanByISBN.nextLine();
           Author = Author.replace(",","");
           bookList.add(new Book(Id,Name,ISBNScan,Quantity,Author));
       }

       Book theBook = new Book();

       for (Book books: bookList) {
           if (books.ISBN == ISBN){
               theBook = books;
           }
       }
        return theBook;
   }

   public void lendBook(Book theBook, User theUser)throws IOException {

       Scanner bookScanLend = new Scanner(AllBooksFile).useDelimiter(",");

       ArrayList<Book> bookList = new ArrayList<Book>();

       while (bookScanLend.hasNext()) {
           int Id = Integer.parseInt(bookScanLend.next());
           String Name = bookScanLend.next();
           int ISBNScan = Integer.parseInt(bookScanLend.next());
           int Quantity = Integer.parseInt(bookScanLend.next());
           String Author = bookScanLend.nextLine();
           Author = Author.replace(",", "");
           bookList.add(new Book(Id, Name, ISBNScan, Quantity, Author));
       }

       for (Book books : bookList) {
           if (books == theBook) {

               Scanner userLoanScan = new Scanner(userLoanFile).useDelimiter(",");

               books.Quantity--;

               PrintWriter printWriter = new PrintWriter(userLoanFile);

               printWriter.println(books.Title + "," + books.ISBN + "," + theUser.Id);

               printWriter.close();

           } else {
               System.out.println("The book does not exists");
           }

       }
   }



    public static void main(String[] args)throws IOException {
        Librarian librarian = new Librarian();

        librarian.addBook(2,"Oskars resor",2334,9,"Stefan");

        Book testBook = librarian.getBookByISBN(2334);

        testBook = librarian.getBookByISBN(2334);
        System.out.println(testBook.Title);

        User testUser = new User();

        librarian.lendBook(testBook,testUser);

    }

}
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
    public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
    public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");

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
       Scanner userScan = new Scanner(UserFile).useDelimiter(",");

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

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       int id = (int) (Math.random()*999) +1;
       int loanCounter;
       int violationCounter = 0;

       if (role.equalsIgnoreCase("Undergraduate Student")){
           loanCounter = 3;
           id = id+1000;
       }else if (role.equalsIgnoreCase("Postgraduate student")){
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

       for (int i = 0; i < userList.size(); i++) {
           for (int j = i+1; j < userList.size(); j++){
               if (userList.get(i).PNumber == (userList.get(j).PNumber)){
                   userList.remove(i);
                   System.out.println("Duplicate removed");
               }
           }
       }

        File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
        Scanner blacklisted = new Scanner(blackFile).useDelimiter(",");

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
           for (int j = 0; j < blackList.size(); j++)
               if (userList.get(i).PNumber == blackList.get(j).PNumber){
                   userList.remove(i);
                   System.out.println("Whitelisted removed because of blacklist");
                   break;
               }
       }

       PrintWriter printWriter = new PrintWriter(UserFile);

       for (User user:userList) {
           printWriter.println(user.export(user));
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

   public void lendBook(Book theBook, int userId)throws IOException {

       Scanner userScan = new Scanner(UserFile).useDelimiter(",");

       ArrayList<User>userList = new ArrayList<User>();

       while (userScan.hasNext()){
           String Name = userScan.next();
           String SurName = userScan.next();
           int PNumber = Integer.parseInt(userScan.next());
           int Id = Integer.parseInt(userScan.next());
           int LoanCounter = Integer.parseInt(userScan.next());
           int ViolationCounter = Integer.parseInt(userScan.next());
           String Role = userScan.nextLine();
           Role = Role.replace(",","");

           userList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
       }

       User tempUser = new User();

       for (User user:userList) {
           if (userId == user.Id){
               tempUser = user;
           }
       }

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

       if (tempUser.LoanCounter > 0){
           for (Book book:bookList) {
               if (book.Title.equals(theBook.Title)){
                   if (book.Quantity > 0){

                       book.Quantity--;



                       Scanner userLoanScan = new Scanner(UserLoanFile).useDelimiter(",");



                   }
               }
           }

       }






   }


    public static void main(String[] args)throws IOException {
        Librarian librarian = new Librarian();

        librarian.addBook(2,"Oskars resor",2334,9,"Stefan");

        Book testBook;
        Book testBook2;

        testBook = librarian.getBookByISBN(2334);
        testBook2 = librarian.getBookByISBN(3434);
        System.out.println(testBook.Title);

        User testUser = new User("Oskar","Andersson",1999,1571,3,0,"Undergraduate Student");
        User testUser2 = new User("Stefan","Andersson",3999,1471,3,0,"Undergraduate Student");

        System.out.println(testUser.getLoanCounter());
        System.out.println(testUser2.getLoanCounter());

        librarian.lendBook(testBook,321);
        librarian.lendBook(testBook2,123);

        System.out.println(testUser.getLoanCounter());
        System.out.println(testUser2.getLoanCounter());

        librarian.addUser("Oskar","Andersson",1999,"Undergraduate Student");
        librarian.addUser("Rolf","Andersson",8299,"PhD Student");

    }
}
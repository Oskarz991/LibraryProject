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

   public void addUser(String name, String surName, int pNumber, String role){

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
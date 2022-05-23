package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class User {

    public String Name;
    public String Surname;
    public String Role;
    public int PNumber;
    public int Id;
    public int LoanCounter;
    public int ViolationCounter;
    public LocalDate Timer;
    public String Request;
    public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
    public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
    public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
    public File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
    public File timeoutFile = new File("src/main/java/com/example/libraryproject/LibProject/TimeoutList.txt");
    public File pendingWorkFile = new File("src/main/java/com/example/libraryproject/LibProject/PendingWork.txt");

   public User(String name,String surname,int pnumber,int id,int loancounter,int violationcounter,String role){
        this.Name = name;
        this.Surname = surname;
        this.Role = role;
        this.PNumber = pnumber;
        this.Id = id;
        this.LoanCounter = loancounter;
        this.ViolationCounter = violationcounter;
    }

    public User(String name,String surname,int pnumber,int id,int loancounter,int violationcounter,String role, LocalDate timer){
        this.Name = name;
        this.Surname = surname;
        this.Role = role;
        this.PNumber = pnumber;
        this.Id = id;
        this.LoanCounter = loancounter;
        this.ViolationCounter = violationcounter;
        this.Timer = timer;
   }

    public User(String name,String surname,int id,String request){
        this.Name = name;
        this.Surname = surname;
        this.Id = id;
        this.Request = request;
    }

    public User(){
    }

    public LocalDate getTimer() {
        return Timer;
    }

    public void setTimer(LocalDate timer) {
        Timer = timer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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

    public int getLoanCounter() {
        return LoanCounter;
    }

    public void setLoanCounter(int loanCounter) {
        LoanCounter = loanCounter;
    }

    public int getViolationCounter() {
        return ViolationCounter;
    }

    public void setViolationCounter(int violationCounter) {
        ViolationCounter = violationCounter;
    }

    public Book searchTitle(String title)throws IOException {

        Scanner bookScan = new Scanner(AllBooksFile).useDelimiter(",");

        ArrayList<Book> bookList = new ArrayList<Book>();

        while (bookScan.hasNext()) {
            int Id = Integer.parseInt(bookScan.next());
            String Name = bookScan.next();
            int ISBN = Integer.parseInt(bookScan.next());
            int Quantity = Integer.parseInt(bookScan.next());
            String Author = bookScan.nextLine();
            Author = Author.replace(",", "");

            bookList.add(new Book(Id, Name, ISBN, Quantity, Author));
        }

        Book theBook = new Book();

        for (Book book : bookList) {
            if (title.equals(book.Title)) {
                theBook = book;
            }
        }
        return theBook;
    }

    public void requestDelete(int id, String name, String surName)throws IOException{
        Scanner pendingScan = new Scanner(pendingWorkFile).useDelimiter(",");

        ArrayList<User> pendingList = new ArrayList<User>();

        while (pendingScan.hasNext()) {
            String Name = pendingScan.next();
            String Surname = pendingScan.next();
            int Id = Integer.parseInt(pendingScan.next());
            String Request = pendingScan.nextLine();
            Request = Request.replace(",", "");

            pendingList.add(new User(Name,Surname,Id,Request));
        }


        User tempUser = new User(name,surName,id,"Delete");
        pendingList.add(tempUser);

        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i+1; j < pendingList.size(); j++){
                if (pendingList.get(i).Request.equalsIgnoreCase(pendingList.get(j).Request)){
                    pendingList.remove(j);
                    System.out.println("You have already requested a delete");
                }
            }
        }

        PrintWriter printWriterPendingFile = new PrintWriter(pendingWorkFile);

        for (User user : pendingList) {
            printWriterPendingFile.println(user.Name + "," + user.Surname + "," + user.Id + "," + user.Request);
        }

        printWriterPendingFile.close();



    }

    public void requestLoan(String bookTitle, String name, String surName, int id)throws IOException{

        Scanner pendingScan = new Scanner(pendingWorkFile).useDelimiter(",");

        ArrayList<User> pendingList = new ArrayList<User>();

        while (pendingScan.hasNext()) {
            String Name = pendingScan.next();
            String Surname = pendingScan.next();
            int Id = Integer.parseInt(pendingScan.next());
            String Request = pendingScan.nextLine();
            Request = Request.replace(",", "");

            pendingList.add(new User(Name,Surname,Id,Request));
        }


        String request = ("Loan: " + bookTitle);

        User tempUser = new User(name,surName,id,request);
        pendingList.add(tempUser);

        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i+1; j < pendingList.size(); j++){
                if (pendingList.get(i).Request.equalsIgnoreCase(pendingList.get(j).Request)){
                    pendingList.remove(j);
                    System.out.println("You have already requested a loan of this book");
                }
            }
        }

        PrintWriter printWriterPendingFile = new PrintWriter(pendingWorkFile);

        for (User user : pendingList) {
            printWriterPendingFile.println(user.Name + "," + user.Surname + "," + user.Id + "," + user.Request);
        }

        printWriterPendingFile.close();


    }

    public void requestTime (Date time){

    }

    public void returnBook(Book bookTitle, int id){




    }

    public String export (User user){

        return user.Name + "," + user.Surname + "," + user.PNumber+"," + user.Id + ","+ user.LoanCounter + "," + user.ViolationCounter+ "," + user.Role;
    }

    public String loanExport (User user){

        return user.Name + "," + user.Surname + "," + user.Id + ",";}

    public String timeoutExport (User user){

        return  user.Name + "," + user.Surname + "," + user.PNumber + "," + user.Id + "," + user.LoanCounter + "," + user.ViolationCounter + "," + user.Timer + "," + user.Role;
   }


    public static void main(String[] args)throws IOException {
       User testUser = new User();
       Book testBook = new Book();

       testBook = testUser.searchTitle("Oskars resor");
       System.out.println(testBook.Title);


       testUser.requestDelete(1234,"Oskar","Andersson");
       testUser.requestLoan("Oskars resor","Oskar","Andersson",1234);

    }
}
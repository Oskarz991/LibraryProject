package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public Storage storage = new Storage();

    public User(String name, String surname, int pnumber, int id, int loancounter, int violationcounter, String role) {
        this.Name = name;
        this.Surname = surname;
        this.Role = role;
        this.PNumber = pnumber;
        this.Id = id;
        this.LoanCounter = loancounter;
        this.ViolationCounter = violationcounter;
    }

    public User(String name, String surname, int pnumber, int id, int loancounter, int violationcounter, String role, LocalDate timer) {
        this.Name = name;
        this.Surname = surname;
        this.Role = role;
        this.PNumber = pnumber;
        this.Id = id;
        this.LoanCounter = loancounter;
        this.ViolationCounter = violationcounter;
        this.Timer = timer;
    }

    public User(String name,int persNR, int id, String request) {
        this.Name = name;
        this.PNumber = persNR;
        this.Id = id;
        this.Request = request;
    }

    public User() {
    }

    public User(Storage obj){
        this.storage = obj;
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

    public Book searchTitle(String title) throws IOException {

        ArrayList<Book> bookList = storage.getBooks();

        Book theBook =new Book();

        for (Book book : bookList) {
            if (title.equals(book.Title)) {
                theBook = book;
                return theBook;
            }
        }
        return null;
    }

    public void requestDelete(int id, String name, int persNr) throws IOException {

        ArrayList<User> pendingList = storage.getPendingList();

        User tempUser = new User(name,persNr, id, "Delete");
        pendingList.add(tempUser);

        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).Request.equalsIgnoreCase(pendingList.get(j).Request) && pendingList.get(i).Id == pendingList.get(j).Id) {
                    pendingList.remove(j);
                    System.out.println("You have already requested a delete");
                }
            }
        }

        storage.updatePendingFile(pendingList);
    }

    public void requestLoan(int id, String bookTitle, String name, int persNr) throws IOException {

        ArrayList<User> pendingList = storage.getPendingList();

        String request = ("Loan: " + bookTitle);

        User tempUser = new User(name, persNr, id, request);
        pendingList.add(tempUser);

        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).Request.equalsIgnoreCase(pendingList.get(j).Request) && pendingList.get(i).Id == pendingList.get(j).Id) {
                    pendingList.remove(j);
                    System.out.println("You have already requested a loan of this book");
                }
            }
        }

       storage.updatePendingFile(pendingList);
    }

    public void returnBook(Book book, int id) throws IOException {

        ArrayList<User> userList = storage.getUserList();

        ArrayList<Book> bookList = storage.getBooks();

        ArrayList<String> userLoanList = storage.getUserLoanList();

        boolean verify = true;
            for (String row : userLoanList) {
                if (row.contains(book.Title) && row.contains(String.valueOf(id))) {
                    userLoanList.remove(row);
                    verify = false;
                    break;

                }
            }

        if (!verify){
            for (User user: userList){
            if (user.Id == id) {
                if (user.Id > 999 && user.Id < 2000 && user.LoanCounter < 4 || user.Id > 1999 & user.Id < 3000 && user.LoanCounter < 6 || user.Id > 2999 && user.Id < 4000 && user.LoanCounter < 8 || user.Id > 3999 && user.Id < 5000 && user.LoanCounter < 11 || user.Id > 4999) {
                    user.LoanCounter++;
                    break;
                }
            }

            }
        }

        if (!verify) {
            for (Book books : bookList) {
                if (books.Title.equals(book.Title)) {
                    books.Quantity++;
                    break;
                }

            }
        }

        storage.updateUserLoanFile(userLoanList);
        storage.updateUserFile(userList);
        storage.updateBookFile(bookList);
    }

    public void requestAddUser(String name, int persNr, String role) throws IOException {

        ArrayList<User> pendingList = storage.getPendingList();

        String request = ("AddMe: " + role);

            User tempUser = new User(name,persNr,0,request);
            pendingList.add(tempUser);

        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).Request.equalsIgnoreCase(pendingList.get(j).Request)) {
                    pendingList.remove(j);
                    System.out.println("You have already requested a loan of this book");
                }
            }
        }

       storage.updatePendingFile(pendingList);
    }


    public boolean loginUser (String name, int id)throws IOException{

       boolean verify = false;

        ArrayList<User>userList = storage.getUserList();

        for (User user:userList) {
            if (user.Name.equalsIgnoreCase(name) && user.Id == id && id < 5000) {
                verify = true;
            }
        }
       return verify;
    }


    public ArrayList<Book> myBooks (int id, String name)throws IOException{

        ArrayList<String> loanedBooks = storage.getUserLoanList();
        ArrayList<Book> returnList = new ArrayList<>();
        ArrayList<Book> allBooks = storage.getBooks();
        ArrayList<String> myLoanedBooks = new ArrayList<>();

        for (String row:loanedBooks) {
            if (row.contains(String.valueOf(id)) && row.contains(name)){
                myLoanedBooks.add(row);
            }
        }

        for (Book book:allBooks) {
            for (String row:myLoanedBooks) {
                if (row.contains(book.Title) && row.contains(String.valueOf(book.ISBN))){
                    returnList.add(book);
                }
            }
        }

        return returnList;
    }


    public String export (User user){

        return user.Name + "," + user.Surname + "," + user.PNumber+"," + user.Id + ","+ user.LoanCounter + "," + user.ViolationCounter+ "," + user.Role;
    }

    public String loanExport (User user){

        return user.Name + "," + user.Surname + "," + user.Id + ",";}

    public String timeoutExport (User user){

        return  user.Name + "," + user.Surname + "," + user.PNumber + "," + user.Id + "," + user.LoanCounter + "," + user.ViolationCounter + "," + user.Timer + "," + user.Role;
   }
   
}
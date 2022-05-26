package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public ArrayList<Book> bookList = new ArrayList<>();
    public ArrayList<User> userList = new ArrayList<>();
    public ArrayList<User> blackList = new ArrayList<>();
    public ArrayList<String> loanedBooksList = new ArrayList<>();
    public ArrayList<User> timeoutList = new ArrayList<>();
    public ArrayList<String> logList = new ArrayList<>();
    public ArrayList<String> userLoanList = new ArrayList<>();
    public ArrayList<User> pendingList = new ArrayList<>();

    public File pendingWorkFile = new File("src/main/java/com/example/libraryproject/LibProject/PendingWork.txt");
    public File AllBooksFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");
    public File UserLoanFile = new File("src/main/java/com/example/libraryproject/LibProject/LoanedBooks.txt");
    public File UserFile = new File("src/main/java/com/example/libraryproject/LibProject/WhiteList.txt");
    public File blackFile = new File("src/main/java/com/example/libraryproject/LibProject/BlackList.txt");
    public File timeoutFile = new File("src/main/java/com/example/libraryproject/LibProject/TimeoutList.txt");

    public Storage() throws ExceptionInInitializerError{
    }

    public ArrayList<Book> getBooks() throws IOException {
        Scanner bookScan = new Scanner(AllBooksFile).useDelimiter(",");
        bookList.clear();
        while (bookScan.hasNext()){
            int Id = Integer.parseInt(bookScan.next());
            String Name = bookScan.next();
            int ISBN = Integer.parseInt(bookScan.next());
            int Quantity = Integer.parseInt(bookScan.next());
            String Author = bookScan.nextLine();
            Author = Author.replace(",","");
            bookList.add(new Book(Id,Name,ISBN,Quantity,Author));
        }

        return bookList;
    }

    public void updateBookFile(ArrayList<Book> bookList) throws IOException{

        PrintWriter printWriter = new PrintWriter(AllBooksFile);

        for (Book books:bookList) {
            printWriter.println(books.export(books));
        }

        printWriter.close();
    }

    public ArrayList<User> getUserList() throws IOException{
        Scanner userScan = new Scanner(UserFile).useDelimiter(",");
        userList.clear();

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
        return userList;
    }

    public void updateUserFile(ArrayList<User> userList) throws IOException{

        PrintWriter printWriter = new PrintWriter(UserFile);

        for (User user:userList) {
            printWriter.println(user.export(user));
        }

        printWriter.close();
    }

    public ArrayList<User> getBlackList() throws IOException{
        Scanner blacklisted = new Scanner(blackFile).useDelimiter(",");
        blackList.clear();

        while (blacklisted.hasNext()){
            String Name = blacklisted.next();
            String SurName = blacklisted.next();
            int PNumber = Integer.parseInt(blacklisted.next());
            int Id = Integer.parseInt(blacklisted.next());
            int LoanCounter = Integer.parseInt(blacklisted.next());
            int ViolationCounter = Integer.parseInt(blacklisted.next());
            String Role = blacklisted.nextLine();
            Role = Role.replace(",","");

            blackList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role));
        }

        return blackList;
    }

    public void updateBlackFile(ArrayList<User> blackList) throws IOException{

        PrintWriter printWriterBlackList = new PrintWriter(blackFile);

        for (User user : blackList) {
            printWriterBlackList.println(user.export(user));
        }

        printWriterBlackList.close();

    }

    public ArrayList<User> getTimeoutList() throws IOException{
        Scanner timeoutListScan = new Scanner(timeoutFile).useDelimiter(",");
        timeoutList.clear();

        while (timeoutListScan.hasNext()){
            String Name = timeoutListScan.next();
            String SurName = timeoutListScan.next();
            int PNumber = Integer.parseInt(timeoutListScan.next());
            int Id = Integer.parseInt(timeoutListScan.next());
            int LoanCounter = Integer.parseInt(timeoutListScan.next());
            int ViolationCounter = Integer.parseInt(timeoutListScan.next());

            LocalDate timer = LocalDate.parse(timeoutListScan.next());

            String Role = timeoutListScan.nextLine();
            Role = Role.replace(",","");

            timeoutList.add(new User(Name,SurName,PNumber,Id,LoanCounter,ViolationCounter,Role,timer));
        }

        return timeoutList;
    }

    public void updatetimeoutFile(ArrayList<User> timeoutList) throws IOException{

        PrintWriter printWriterTimeoutList = new PrintWriter(timeoutFile);

        for (User us : timeoutList) {
            printWriterTimeoutList.println(us.timeoutExport(us));
        }

        printWriterTimeoutList.close();

    }

    public ArrayList<String> getUserLoanList() throws IOException{
        userLoanList.clear();
        Scanner userLoanScan = new Scanner(UserLoanFile).useDelimiter(",");

        while (userLoanScan.hasNext()) {
            String Name = userLoanScan.next();
            String Surname = userLoanScan.next();
            String Id = userLoanScan.next();

            String Title = userLoanScan.next();
            String ISBN = userLoanScan.next();
            String Author = userLoanScan.nextLine();
            Author = Author.replace(",", "");

            userLoanList.add(Name + "," + Surname + "," + Id + "," + Title + "," + ISBN + "," + Author);
        }
        return userLoanList;
    }

    public void updateUserLoanFile(ArrayList<String> userLoanList) throws IOException{
        PrintWriter printWriterUserLoan = new PrintWriter(UserLoanFile);

        for (String userLoaner: userLoanList) {
            printWriterUserLoan.println(userLoaner);
        }

        printWriterUserLoan.close();
    }

    public ArrayList<User> getPendingList() throws IOException{
        Scanner pendingScan = new Scanner(pendingWorkFile).useDelimiter(",");
        pendingList.clear();

        while (pendingScan.hasNext()) {
            String Name = pendingScan.next();
            int PersNr = Integer.parseInt(pendingScan.next());
            int Id = Integer.parseInt(pendingScan.next());
            String Request = pendingScan.nextLine();
            Request = Request.replace(",", "");

            pendingList.add(new User(Name, PersNr, Id, Request));
        }

        return pendingList;
    }

    public void updatePendingFile(ArrayList<User> pendingList) throws IOException{
        PrintWriter printWriterPendingFile = new PrintWriter(pendingWorkFile);

        for (User user : pendingList) {
            printWriterPendingFile.println(user.Name + "," + user.PNumber + "," + user.Id + "," + user.Request);
        }

        printWriterPendingFile.close();
    }
}

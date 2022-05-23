package com.example.libraryproject.LibProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public ArrayList<Book> bookList = new ArrayList<>();
    public ArrayList<User> userList = new ArrayList<>();
    public ArrayList<User> blackList = new ArrayList<>();
    public ArrayList<String> loanedBooksList = new ArrayList<>();
    public ArrayList<User> timeoutList = new ArrayList<>();
    public ArrayList<String> logList = new ArrayList<>();

    public Storage(){
        ArrayList<Book> bookList = new ArrayList<>();
         ArrayList<User> userList = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() throws IOException {
        ArrayList<Book> bookList = new ArrayList<>();
        File bookFile = new File("src/main/java/com/example/libraryproject/LibProject/AllBooks.txt");

        Scanner bookScan = new Scanner(bookFile).useDelimiter(",");

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


    //Här skriver vi metoder

}

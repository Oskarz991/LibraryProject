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

    public Storage() throws ExceptionInInitializerError{
        ArrayList<Book> bookList = new ArrayList<>();
         ArrayList<User> userList = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() throws IOException {
        ArrayList<Book> bookList = new ArrayList<>();


        return bookList;
    }


    //Här skriver vi metoder

}

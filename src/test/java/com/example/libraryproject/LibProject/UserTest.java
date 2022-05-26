package com.example.libraryproject.LibProject;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserTest {

    @Test
    void searchTitle() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();

         user.Name = "Victor";
         user.Surname = "Almqvist";
         user.PNumber = 2005;
         user.Id = 2345;
         user.LoanCounter = 5;
         user.ViolationCounter = 0;
         user.Role = "PhD Student";

        Book book = new Book(1,"Stefan kollar på mobilen",6969,69,"Stefan");

        bookList.add(book);
        userList.add(user);

        when(storage.getBooks()).thenReturn(bookList);
        when(storage.getUserList()).thenReturn(userList);

        assertEquals(user.searchTitle("Stefan kollar på mobilen"),book);
    }

    @Test
    void searchTitleNothingThere() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();

        user.Name = "Victor";
        user.Surname = "Almqvist";
        user.PNumber = 2005;
        user.Id = 2345;
        user.LoanCounter = 5;
        user.ViolationCounter = 0;
        user.Role = "PhD Student";

        Book book = new Book(1,"Stefan kollar på mobilen",6969,69,"Stefan");

        bookList.add(book);
        userList.add(user);

        when(storage.getBooks()).thenReturn(bookList);
        when(storage.getUserList()).thenReturn(userList);

        assertNull(user.searchTitle("Stefan har fokus"));
    }

    @Test
    void requestDelete() throws IOException{//behöver vi göra en till metod för att se så att man inte kan requesta mer än en gång?
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> pendingWorkList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();

        user.Name = "Victor";
        user.Surname = "Almqvist";
        user.PNumber = 2005;
        user.Id = 2345;
        user.LoanCounter = 5;
        user.ViolationCounter = 0;
        user.Role = "PhD student";

        //userList.add(realUser);
        userList.add(user);

        when(storage.getUserList()).thenReturn(userList);
        when(storage.getPendingList()).thenReturn(pendingWorkList);

        user.requestDelete(2345,"Victor",2005);
        user.requestDelete(2345,"Victor",2005);

        assertEquals(storage.getPendingList(),pendingWorkList);
    }

    @Test
    void requestLoan() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> pendingWorkList = new ArrayList<>();

        when(storage.getPendingList()).thenReturn(pendingWorkList);

        user.requestLoan(2345,"Victors vilda kväll","Victor",2005);

        assertEquals(storage.getPendingList(),pendingWorkList);
    }

    @Test
    void returnBook() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();

        Book book = new Book(32,"Svennsons resa",9199,1,"Rolf");

        user.Name = "Victor";
        user.Surname = "Almqvist";
        user.PNumber = 2005;
        user.Id = 2345;
        user.LoanCounter = 5;
        user.ViolationCounter = 0;
        user.Role = "PhD student";

        bookList.add(book);
        userList.add(user);

        when(storage.getBooks()).thenReturn(bookList);
        when(storage.getUserList()).thenReturn(userList);

        user.returnBook(book,2345);

        assertEquals(storage.getUserList(),userList);
        assertEquals(storage.getBooks(),bookList);
    }

    @Test
    void requestAddUser()throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> pendingList = new ArrayList<>();

        when(storage.getPendingList()).thenReturn(pendingList);

        user.requestAddUser("Victor","Almqvist",1999,"Undergraduate student");
        assertEquals(storage.getPendingList(),pendingList);
    }

    @Test
    void loginUser() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("Victor","Almqvist",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);

        when(storage.getUserList()).thenReturn(userList);

        assertTrue(user.loginUser("Victor",1122));
    }
}
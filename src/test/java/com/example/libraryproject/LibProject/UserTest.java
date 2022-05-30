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
    void loginUser() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("Victor",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);

        when(storage.getUserList()).thenReturn(userList);

        assertTrue(user.loginUser("Victor",1122));
    }

    @Test
    void loginUserFail() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("Victor",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);

        when(storage.getUserList()).thenReturn(userList);

        assertFalse(user.loginUser("Oskar",9393));
    }

    @Test
    void myBooks() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<String> userLoanList = new ArrayList<>();


        Book book = new Book(1,"Leauge of Legends",1044,10,"Malamoz");
        User user1 = new User("Victor",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);
        bookList.add(book);


            { when(storage.getBooks()).thenReturn(bookList);
                {
                    when(storage.getUserLoanList()).thenReturn(userLoanList);
                    librarian.lendBook(book, 1122);

                    assertEquals(userLoanList, user.myBooks(1122, "Victor"));
                }}
    }

    @Test
    void myBooksEmpty() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<String> userLoanList = new ArrayList<>();
        ArrayList<String> empty = new ArrayList<>();


        Book book = new Book(1,"Leauge of Legends",1044,10,"Malamoz");
        User user1 = new User("Victor",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);
        bookList.add(book);


        { when(storage.getBooks()).thenReturn(bookList);
            {
                when(storage.getUserLoanList()).thenReturn(userLoanList);

                assertEquals(empty, user.myBooks(9999, "Oskar"));
            }}
    }

    @Test
    void searchByTitle() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<Book> bookList = new ArrayList<>();
        Book book = new Book(1,"Leauge of Legends",1044,10,"Malamoz");

        bookList.add(book);

        when(storage.getBooks()).thenReturn(bookList);

        assertEquals(book,user.searchTitle("Leauge of Legends"));
    }

    @Test
    void searchByTitleFail() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<Book> bookList = new ArrayList<>();
        Book book = new Book(1,"Leauge of Legends",1044,10,"Malamoz");

        bookList.add(book);

        when(storage.getBooks()).thenReturn(bookList);

        assertNull(user.searchTitle("Oskars resa"));
    }

}
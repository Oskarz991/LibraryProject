package com.example.libraryproject.LibProject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

class LibrarianTest {

    @Test
    void getBookByISBN() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        Book book = new Book(1, "Test", 1111, 1, "Victor");
        Book book1 = new Book(2,"Stefans resor",2222,1,"Oskar");
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);

        when(storage.getBooks()).thenReturn(bookList);

        assertEquals(book,librarian.getBookByISBN(1111));
    }

    @Test
    void getBookByISBNWrong() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        Book book = new Book(1, "Test", 1111, 1, "Victor");
        Book book1 = new Book(2,"Stefans resor",2222,1,"Oskar");

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);

        when(storage.getBooks()).thenReturn(bookList);

        assertNull(librarian.getBookByISBN(8889));
    }

    @Test
    void loginLibrarian() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whitelisted = new ArrayList<>();
        User user = new User("Victor", "Almqvist",2001,5234,3,0,"");
        User user2 = new User("Victor", "Almqvist",1123,5434,3,0,"");

        whitelisted.add(user);
        whitelisted.add(user2);

        when(storage.getUserList()).thenReturn(whitelisted);

        assertTrue(librarian.loginLibrarian("Victor", 5234));
    }

    @Test
    void loginLibrarianFail() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whitelisted = new ArrayList<>();
        User user = new User("Victor", "Almqvist",2001,5234,3,0,"");
        User user2 = new User("Victor", "Almqvist",1123,5434,3,0,"");

        whitelisted.add(user);
        whitelisted.add(user2);

        when(storage.getUserList()).thenReturn(whitelisted);

        assertFalse(librarian.loginLibrarian("Oskar", 9999));
    }
}

package com.example.libraryproject.LibProject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

class LibrarianTest {

    @Test
    void addBook_Normal() throws IOException {

        Storage storage = Mockito.mock(Storage.class);
         Librarian librarian = new Librarian(storage);
        // Book book = new Book(1,"Test",1111,1,"Victor");
        librarian.addBook(1,"Test",1111,1,"Victor");

         when(storage.getBooks().get(0)).thenReturn(new Book(1,"Test",1111,1,"Victor"));

         assertEquals(storage.getBooks().get(0),new Book(1,"Test",1111,1,"Victor"));

    }

    @Test
    void addBook() {
    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void giveTimeout() {
    }

    @Test
    void getBookByISBN() {
    }

    @Test
    void lendBook() {
    }
}

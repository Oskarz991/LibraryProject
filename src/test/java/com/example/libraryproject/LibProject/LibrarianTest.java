package com.example.libraryproject.LibProject;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LibrarianTest {

    @Test
    void addbook_Normal() throws IOException {
         Librarian librarian = Mockito.mock(Librarian.class);
            librarian.addBook(4,"testBook",1111,10,"Test");
        {
            when(librarian.getBookByISBN(1111)).thenReturn(new Book(4, "testBook", 1111, 10, "Test"));

            assertEquals(librarian.getBookByISBN(1111), new Book(4, "testBook", 1111, 10, "Test"));
            // verify((purchaseManager),atLeastOnce()).sumOfMonth(2021,10);
        }
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

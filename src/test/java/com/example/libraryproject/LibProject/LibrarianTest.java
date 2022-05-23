package com.example.libraryproject.LibProject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

class LibrarianTest {

    @Test
    void addBook_Normal() throws IOException {
            Librarian librarian = Mockito.mock(Librarian.class);
           doThrow(new RuntimeException()).when(librarian).addBook(isA(Integer.class),isA(String.class),isA(Integer.class),isA(Integer.class),isA(String.class));
           librarian.addBook(0,"test",1111,10,"victor");

           verify(librarian,times(1)).addBook(0,"test",1111,10,"victor");
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

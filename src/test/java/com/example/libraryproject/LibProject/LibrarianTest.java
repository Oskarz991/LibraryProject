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

         Librarian librarian = Mockito.mock(Librarian.class);

            doNothing().when(librarian).addBook(isA(Integer.class),isA(String.class),isA(Integer.class),isA(Integer.class),isA(String.class));
            librarian.addBook(1,"Test",1111,1,"Victor");

            verify(librarian,times(1)).addBook(1, "Test",1111,1,"Victor");
    }

    @Test
    void addBookTimesTwo() throws IOException{
        Librarian librarian = Mockito.mock(Librarian.class);

        doNothing().when(librarian).addBook(isA(Integer.class),isA(String.class),isA(Integer.class),isA(Integer.class),isA(String.class));
        librarian.addBook(4,"Oskars resor",2222,1,"Oskar andersson");

        verify(librarian,times(1)).addBook(4,"Oskars resor",2222,1,"Oskar andersson");
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

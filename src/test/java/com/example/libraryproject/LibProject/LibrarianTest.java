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
    void addBook_Normal() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
         Librarian librarian = new Librarian(storage);

         Book book = new Book(1, "Test", 1111, 1, "Victor");
         ArrayList<Book> bookList = new ArrayList<>();
         bookList.add(book);

        when(storage.getBooks()).thenReturn(bookList);

        assertEquals(bookList,librarian.addBook(1, "Test", 1111, 1, "Victor"));
        //verify(librarian, times(1)).addBook(1, "Test", 1111, 1, "Victor");
    }

    @Test
    void addBookDuplicate() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        Book book = new Book(1, "Test", 1111, 1, "Victor");
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(storage.getBooks()).thenReturn(bookList);
        librarian.addBook(1, "Test", 1111, 1, "Victor");

        assertEquals(bookList,librarian.addBook(1, "Test", 1111, 1, "Victor"));
        //verify(librarian, times(1)).addBook(1, "Test", 1111, 1, "Victor");
    }

    @Test
    void adduser() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("Victor");
        user.setSurname("Almqvist");
        user.setPNumber(2001);
        user.setRole("");

        userList.add(user);

        when(storage.getUserList()).thenReturn(userList);

        assertEquals(userList,librarian.addUser("Victor", "Almqvist",2001,""));
    }

    @Test
    void addUserDuplicate() throws IOException{//Fråga Lars om detta är knas!
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("Victor");
        user.setSurname("Almqvist");
        user.setPNumber(2001);
        user.setRole("");

        userList.add(user);

        when(storage.getUserList()).thenReturn(userList);
        librarian.addUser("Victor","Alqmvist",2001,"");

        assertEquals(userList,librarian.addUser("Victor", "Almqvist",2001,""));

    }

    @Test
    void addUserBlackListed() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<User> blackList = new ArrayList<>();
        User user = new User();
        user.setName("Victor");
        user.setSurname("Almqvist");
        user.setPNumber(2001);
        user.setRole("");

        blackList.add(user);

        when(storage.getUserList()).thenReturn(userList);

        assertEquals(userList,librarian.addUser("Victor", "Almqvist",2001,""));
    }



    @Test
    void deleteUser() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whiteList = new ArrayList<>();
        ArrayList<User> realList = new ArrayList<>();

        User user = new User("Victor", "Almqvist",2300,1002,3,0,"Undergraduate student");
        User user2 = new User("Victor", "Almqvist",2300,1032,3,0,"Undergraduate student");

        whiteList.add(user);
        whiteList.add(user2);

        realList.add(user2);

        when(storage.getUserList()).thenReturn(whiteList);
        librarian.deleteUser(1002,true);

        assertEquals(storage.getUserList(),realList);
    }

    @Test
    void deleteUserHaveNotReturnedBooks() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whiteList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();

        User user = new User("Victor", "Almqvist",2300,1002,3,0,"Undergraduate student");
        User user2 = new User("Victor", "Almqvist",2300,1032,3,0,"Undergraduate student");
        Book book = new Book(2,"Victors resa",2311,3,"Stefan Bucei");

        bookList.add(book);
        whiteList.add(user);
        whiteList.add(user2);

        when(storage.getUserList()).thenReturn(whiteList);
        when(storage.getBooks()).thenReturn(bookList);
        librarian.lendBook(book,1002);
        librarian.deleteUser(1002,true);

        assertEquals(storage.getUserList(),whiteList);
    }

    @Test
    void giveTimeout() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whiteList = new ArrayList<>();
        ArrayList<User> timeoutList = new ArrayList<>();

        User user = new User("Victor", "Almqvist",2300,1002,3,0,"Undergraduate student");
        User user2 = new User("Victor", "Almqvist",2300,1032,3,0,"Undergraduate student");

        whiteList.add(user);
        whiteList.add(user2);
        LocalDate time = LocalDate.now();
        user.setTimer(time);

        when(storage.getUserList()).thenReturn(whiteList);
        when(storage.getTimeoutList()).thenReturn(timeoutList);

        librarian.giveTimeout(1002);

        assertEquals(storage.getUserList(),whiteList);
        assertEquals(storage.getTimeoutList(),timeoutList);
    }

    @Test
    void giveTimeoutWhenTimeouted() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whiteList = new ArrayList<>();
        ArrayList<User> timeoutList = new ArrayList<>();

        User user = new User("Victor", "Almqvist",2300,1002,3,0,"Undergraduate student");
        User user2 = new User("Victor", "Almqvist",2300,1032,3,0,"Undergraduate student");

        whiteList.add(user2);
        LocalDate time = LocalDate.now();
        user.setTimer(time);
        timeoutList.add(user);

        when(storage.getUserList()).thenReturn(whiteList);
        when(storage.getTimeoutList()).thenReturn(timeoutList);

        librarian.giveTimeout(1002);

        assertEquals(storage.getUserList(),whiteList);
        assertEquals(storage.getTimeoutList(),timeoutList);
    }

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
    void lendBook() throws IOException {
        Storage storage = Mockito.mock(Storage.class);
        Librarian librarian = new Librarian(storage);

        ArrayList<User> whiteList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();

        User user = new User("Victor", "Almqvist",2300,1002,3,0,"Undergraduate student");
        User user2 = new User("Victor", "Almqvist",2300,1032,3,0,"Undergraduate student");
        Book book = new Book(2,"Victors resa",2311,3,"Stefan Bucei");
        Book book2 = new Book(2,"Victors resa",2311,2,"Stefan Bucei");

        bookList.add(book2);
        bookList.add(book);
        whiteList.add(user);
        whiteList.add(user2);

        when(storage.getUserList()).thenReturn(whiteList);
        when(storage.getBooks()).thenReturn(bookList);

        System.out.println(bookList.get(0).Quantity);

        librarian.lendBook(book,1002);

        System.out.println(bookList.get(0).Quantity);

        assertEquals(storage.getBooks(),bookList);
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

        assertEquals(true,librarian.loginLibrarian("Victor",5234));
    }
}

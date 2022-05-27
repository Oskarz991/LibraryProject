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
        User user1 = new User("Victor","Almqvist",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);

        when(storage.getUserList()).thenReturn(userList);

        assertTrue(user.loginUser("Victor",1122));
    }

    @Test
    void loginUserFail() throws IOException{
        Storage storage = Mockito.mock(Storage.class);
        User user = new User(storage);

        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("Victor","Almqvist",1230,1122,3,0,"Undergraduate student");

        userList.add(user1);

        when(storage.getUserList()).thenReturn(userList);

        assertTrue(user.loginUser("Oskar",9393));
    }
}
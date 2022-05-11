package com.example.libraryproject.Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestningClassTest {

    @Test
    void getAverage() {
            TestningClass average = new TestningClass();
            average.listArray = new int[] {10,20,30,40};

            assertEquals( 25,average.getAverage());
        }
//Förändring
    @Test
    void getAverage2() {
        TestningClass average = new TestningClass();
        average.listArray = new int[] {10,20,30,40};

        assertEquals( 25,average.getAverage());
    }

    @Test
    void getAverage3() {
        TestningClass average = new TestningClass();
        average.listArray = new int[] {10,20,30,40};

        assertEquals( 25,average.getAverage());
    }

    }

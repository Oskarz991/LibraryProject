package com.example.libraryproject.Test;

import com.example.libraryproject.LibProject.Scenery;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SceneryTest {

    @Test
    void getAverage() {
            Scenery average = new Scenery();
            average.listArray = new int[] {10,20,30,40};

            assertEquals( 25,average.getAverage());
        }
    }

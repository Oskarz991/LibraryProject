package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AverageTest {

    @Test
    void getAverage() {
        Average average = new Average();
        average.listArray = new int[] {10,20,30,40};

        assertEquals( 25,average.getAverage());
    }
}

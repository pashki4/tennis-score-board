package vyshniakov.po;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyClassTest {

    @Test
    void test() {
        String first = "String";
        String second = "String";
        assertEquals(first, second);
    }

    @Test
    void test1() {
        assertTrue(5 == 5);
    }
}
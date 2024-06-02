package com.pcy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JunitPracticeTest {

    @Test
    public void assertEqualsTest() {
        String expect = "Something";
        String actual = "Something";

        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void assertNotEqualsTest() {
        String expect = "Something";
        String actual = "hello";

        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    public void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        Assertions.assertTrue(a.equals(b));
    }

    @Test
    public void assertFalseTest() {
        Integer a = 10;
        Integer b = 20;

        Assertions.assertFalse(a.equals(b));
    }

    @Test
    public void asserThrowTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의발생 Error");
        });
    }

    @Test
    public void assertNotNullTest() {
        String value = "hello";
        Assertions.assertNotNull(value);
    }

    @Test
    public void assertNullTest() {
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    public void assertIterableTest() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    public void assertAllTest() {
        String expect = "Something";
        String actual = "Something";

        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertAll("Assert All", List.of(
                () -> {Assertions.assertEquals(expect, actual);},
                () -> {Assertions.assertIterableEquals(list1, list2);}
        ));
    }
}

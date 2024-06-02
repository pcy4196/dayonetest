package com.pcy.dayonetest;

import org.junit.jupiter.api.*;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JunitPracticeTest {

//    @Test
//    public void assert_equals_test() {
//        String expect = "Something";
//        String actual = "Something";
//
//        Assertions.assertEquals(expect, actual);
//    }

    @Test
    @DisplayName("assert Equals Test 메서드 테스트")
    public void assertEqualsTest() {
        String expect = "Something";
        String actual = "Something";

        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("assert Not Equals Test 메서드 테스트")
    public void assertNotEqualsTest() {
        String expect = "Something";
        String actual = "hello";

        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("assert True Test 메서드 테스트")
    public void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        Assertions.assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("assert False Test 메서드 테스트")
    public void assertFalseTest() {
        Integer a = 10;
        Integer b = 20;

        Assertions.assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("assert False Test 메서드 테스트")
    public void asserThrowTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의발생 Error");
        });
    }

    @Test
    @DisplayName("assert Not Null Test 메서드 테스트")
    public void assertNotNullTest() {
        String value = "hello";
        Assertions.assertNotNull(value);
    }

    @Test
    @DisplayName("assert Null Test 메서드 테스트")
    public void assertNullTest() {
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    @DisplayName("assert Iterable Test 메서드 테스트")
    public void assertIterableTest() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    @DisplayName("assert All 메서드 테스트")
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

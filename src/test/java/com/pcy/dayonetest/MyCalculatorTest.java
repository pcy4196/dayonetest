package com.pcy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    @Test
    @DisplayName("MyCalculator 덧셈 테스트")
    void addTest() {
        // AAA 패턴
        // Arrange - 준비
        MyCalculator myCalculator = new MyCalculator();
        // Act - 행동
        myCalculator.add(10.0);
        // Assert - 검증
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 뺄셈 테스트")
    void minus() {
        // GWT 패턴
        // Given - 준비
        MyCalculator myCalculator = new MyCalculator(10.0);
        // When - 행동
        myCalculator.minus(5.0);
        // Then - 검증
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 곱셈 테스트")
    void multiply() {
        // Given
        MyCalculator myCalculator = new MyCalculator(10.0);
        // When
        myCalculator.multiply(2.0);
        // Then
        Assertions.assertEquals(20.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 나눗셈 테스트")
    void divide() {
        // Given
        MyCalculator myCalculator = new MyCalculator(10.0);
        // When
        myCalculator.divide(2.0);
        // Then
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 사칙연산 테스트")
    void complicatedCalculateTest() {
        // given
        MyCalculator myCalculator = new MyCalculator();

        // when
        Double result = myCalculator
                .add(10.0)
                .minus(4.0)
                .multiply(2.0)
                .divide(3.0)
                .getResult();

        // then
        Assertions.assertEquals(4.0, result);
    }

    @Test
    @DisplayName("0으로 나눗셈할때 ZeroDivisionException 발생여부 확인")
    void divideZeroTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when & then
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () ->{
            myCalculator.divide(0.0);
        });
    }
}
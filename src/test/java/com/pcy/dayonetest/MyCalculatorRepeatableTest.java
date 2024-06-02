package com.pcy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyCalculatorRepeatableTest {

    @DisplayName("뺄셈 테스트 메서드를 5번 수행")
    @RepeatedTest(5)
    public void repeatedAddTest() {
        // Given - 준비
        MyCalculator myCalculator = new MyCalculator(10.0);
        // When - 행동
        myCalculator.minus(5.0);
        // Then - 검증
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @DisplayName("덧셈 테스트 메서드를 4번 수행(다양한 값을 입력하여..)")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameters")
    public void parameterizedTest(Double addValue, Double ExpectedValue) {
        // Given - 준비
        MyCalculator myCalculator = new MyCalculator(0.0);
        // When - 행동
        myCalculator.add(10.0);
        // Then - 검증
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    public static Stream<Arguments> parameterizedTestParameters() {
        return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(4.0, 4.0),
                Arguments.of(16.0, 16.0),
                Arguments.of(13.0, 13.0)
        );
    }

    @DisplayName("파라미터를 넣으며 사칙연산 2번 반복 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedComplicatedCalculateTestParameters")
    public void parameterizedComplicatedCalculateTest(
            Double addValue,
            Double minusValue,
            Double multiplyValue,
            Double divideValue,
            Double expectedValue
    ) {
        // given
        MyCalculator myCalculator = new MyCalculator();

        // when
        Double result = myCalculator
                .add(addValue)
                .minus(minusValue)
                .multiply(multiplyValue)
                .divide(divideValue)
                .getResult();

        // then
        Assertions.assertEquals(expectedValue, result);
    }

    public static Stream<Arguments> parameterizedComplicatedCalculateTestParameters() {
        return Stream.of(
                Arguments.of(10.0, 4.0, 2.0, 3.0, 4.0),
                Arguments.of(4.0, 2.0, 4.0, 4.0, 2.0)
        );
    }
}

package com.pcy.dayonetest;

public class MyCalculator {

    private Double result;

    // 생성자
    public MyCalculator() {
        this.result = 0.0;
    }

    // 생성자
    public MyCalculator(Double initResult) {
        this.result = initResult;
    }

    // 더하기
    public MyCalculator add(Double number) {
        this.result += number;
        return this;
    }

    // 빼기
    public MyCalculator minus(Double number) {
        this.result -= number;
        return this;
    }

    // 곱하기
    public MyCalculator multiply(Double number) {
        this.result *= number;
        return this;
    }

    // 나누기
    public MyCalculator divide(Double number) {
        if (number == 0.0) {
            throw new ZeroDivisionException();
        }

        this.result /= number;
        return this;
    }

    // 결과값 반환
    public Double getResult() {
        return this.result;
    }

    public static class ZeroDivisionException extends RuntimeException {
    }

}

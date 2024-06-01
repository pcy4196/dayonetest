package com.pcy.dayonetest;

public class MyCalculatorApplication {

    public static void main(String[] args) {
        // MyCalculator 실행 확인
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(10.0)
                    .minus(2.0)
                    .multiply(2.0);
        System.out.println(myCalculator.getResult());

        // Exceptiom 실행 확인
        MyCalculator myCalculator1 = new MyCalculator();
        myCalculator1.add(2.0)
                    .divide(0.0);
        System.out.println(myCalculator1.getResult());
    }
}

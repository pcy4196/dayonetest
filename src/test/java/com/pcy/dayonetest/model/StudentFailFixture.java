package com.pcy.dayonetest.model;

import com.pcy.dayonetest.MyCalculator;

public class StudentFailFixture {

    public static StudentFail create(StudentScore studentScore) {
        MyCalculator myCalculator = new MyCalculator(0.0);
        return StudentFail
                .builder()
                .studentName(studentScore.getStudentName())
                .exam(studentScore.getExam())
                .avgScore(
                        myCalculator
                                .add(studentScore.getKorScore().doubleValue())
                                .add(studentScore.getEnglishScore().doubleValue())
                                .add(studentScore.getMathScore().doubleValue())
                                .divide(3.0)
                                .getResult()
                ).build();
    }

    public static StudentFail create(String studentName, String exam) {
        return StudentFail
                .builder()
                .studentName(studentName)
                .exam(exam)
                .avgScore(40.0)
                .build();
    }
}
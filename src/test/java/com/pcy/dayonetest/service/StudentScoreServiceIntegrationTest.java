package com.pcy.dayonetest.service;

import com.pcy.dayonetest.IntegrationTest;
import com.pcy.dayonetest.MyCalculator;
import com.pcy.dayonetest.controller.response.ExamFailStudentResponse;
import com.pcy.dayonetest.controller.response.ExamPassStudentResponse;
import com.pcy.dayonetest.model.StudentScore;
import com.pcy.dayonetest.model.StudentScoreTestDataBuilder;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentScoreServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void savePassedStudentScoreTest() {
        // given
        StudentScore studentScore = StudentScoreTestDataBuilder.passed()
                .studentName("pcy")
                .build();

        // when
        studentScoreService.saveScore(
                studentScore.getStudentName(),
                studentScore.getExam(),
                studentScore.getKorScore(),
                studentScore.getEnglishScore(),
                studentScore.getMathScore()
        );

        entityManager.flush();
        entityManager.clear();

        // then
        List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentsList(studentScore.getExam());

        Assertions.assertEquals(1, responses.size());

        ExamPassStudentResponse examPassStudentResponse = responses.get(0);

        MyCalculator calculator = new MyCalculator(0.0);

        Assertions.assertEquals(studentScore.getStudentName(), examPassStudentResponse.getStudentName());
        Assertions.assertEquals(
                calculator
                        .add(studentScore.getMathScore().doubleValue())
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .divide(3.0)
                        .getResult(),
                examPassStudentResponse.getAvgScore()
        );
    }

    @Test
    public void saveFailedStudentScoreTest() {
        // given
        StudentScore studentScore = StudentScoreTestDataBuilder.failed()
                .studentName("pcy")
                .build();

        // when
        studentScoreService.saveScore(
                studentScore.getStudentName(),
                studentScore.getExam(),
                studentScore.getKorScore(),
                studentScore.getEnglishScore(),
                studentScore.getMathScore()
        );

        entityManager.flush();
        entityManager.clear();

        // then
        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentsList(studentScore.getExam());

        Assertions.assertEquals(1, responses.size());

        ExamFailStudentResponse examFailStudentResponse = responses.get(0);

        MyCalculator calculator = new MyCalculator(0.0);

        Assertions.assertEquals(studentScore.getStudentName(), examFailStudentResponse.getStudentName());
        Assertions.assertEquals(
                calculator
                        .add(studentScore.getMathScore().doubleValue())
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .divide(3.0)
                        .getResult(),
                examFailStudentResponse.getAvgScore()
        );
    }
}

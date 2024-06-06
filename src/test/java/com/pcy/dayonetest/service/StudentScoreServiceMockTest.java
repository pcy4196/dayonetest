package com.pcy.dayonetest.service;

import com.pcy.dayonetest.controller.response.ExamFailStudentResponse;
import com.pcy.dayonetest.controller.response.ExamPassStudentResponse;
import com.pcy.dayonetest.model.*;
import com.pcy.dayonetest.repository.StudentFailRepository;
import com.pcy.dayonetest.repository.StudentPassRepository;
import com.pcy.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

public class StudentScoreServiceMockTest {

    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepository studentFailRepository;

    @BeforeEach
    public void beforeEach() {
        studentFailRepository  = Mockito.mock(StudentFailRepository.class);
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        studentPassRepository  = Mockito.mock(StudentPassRepository.class);

        studentScoreService = new StudentScoreService(
                studentFailRepository,
                studentScoreRepository,
                studentPassRepository
        );
    }


    @Test
    @DisplayName("첫번째 Mock 테스트")
    public void firstSaveScoreMockTest() {
        // given
        String givenStudentName = "pcy";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인 경우")
    public void saveScoreMockTest() {
        // given : 평균 점수가 60점 이상인 경우

        // studentScore
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed()
                .studentName("pcy")     // 오버라이딩 사용 가능
                .build();

        // studentPass
        StudentPass exepectStudentPass = StudentPassFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        // when
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        Assertions.assertEquals(studentScoreArgumentCaptor.getValue(), expectStudentScore);

        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        Assertions.assertEquals(studentPassArgumentCaptor.getValue(), exepectStudentPass);

        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인 경우")
    public void saveScoreMockTest2() {
        // given : 평균 점수가 60점 미만인 경우
        // studentScore
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.failed()
                .studentName("pcy")     // 오버라이딩 사용 가능
                .build();

        // studentFail
        StudentFail expectStudentFail = StudentFailFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail.class);

        // when
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        Assertions.assertEquals(studentScoreArgumentCaptor.getValue(), expectStudentScore);

        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());

        Mockito.verify(studentFailRepository, Mockito.times(1)).save(studentFailArgumentCaptor.capture());
        Assertions.assertEquals(studentFailArgumentCaptor.getValue(), expectStudentFail);
    }

    @Test
    @DisplayName("합격자 명단 조회 검증")
    public void getPassStudentsListTest() {
        // given
        String givenTestExam = "testexam";

        StudentPass es1 =  StudentPassFixture.create("pcy", givenTestExam);
        StudentPass es2 =  StudentPassFixture.create("ccc", givenTestExam);
        StudentPass nes1 =  StudentPassFixture.create("anotherStudnet", "anotherExam");

        // 가짜 데이터 생성
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                es1,
                es2,
                nes1
        ));

        // when
        List<ExamPassStudentResponse> expectResponses = Stream.of(es1, es2)
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
        List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentsList(givenTestExam);

        // then
        Assertions.assertIterableEquals(expectResponses, responses);
    }

    @Test
    @DisplayName("불합격자 명단 조회 검증")
    public void getFailStudentsListTest() {
        // given
        String givenTestExam = "testexam";

        StudentFail es1 =  StudentFailFixture.create("pcy", givenTestExam);
        StudentFail es2 =  StudentFailFixture.create("ccc", givenTestExam);
        StudentFail nes1 =  StudentFailFixture.create("anotherStudnet", "anotherExam");

        // 가짜 데이터 생성
        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                es1,
                es2,
                nes1
        ));

        // when
        List<ExamFailStudentResponse> expectResponses = Stream.of(es1, es2)
                .map((pass) -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentsList(givenTestExam);

        // then
        Assertions.assertIterableEquals(expectResponses, responses);
    }
}

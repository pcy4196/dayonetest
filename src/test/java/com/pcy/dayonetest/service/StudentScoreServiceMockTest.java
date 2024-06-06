package com.pcy.dayonetest.service;

import com.pcy.dayonetest.MyCalculator;
import com.pcy.dayonetest.controller.response.ExamFailStudentResponse;
import com.pcy.dayonetest.controller.response.ExamPassStudentResponse;
import com.pcy.dayonetest.model.StudentFail;
import com.pcy.dayonetest.model.StudentPass;
import com.pcy.dayonetest.model.StudentScore;
import com.pcy.dayonetest.repository.StudentFailRepository;
import com.pcy.dayonetest.repository.StudentPassRepository;
import com.pcy.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class StudentScoreServiceMockTest {


    @Test
    @DisplayName("첫번째 Mock 테스트")
    public void firstSaveScoreMockTest() {
        // given
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentFailRepository.class),
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class)
        );
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
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentFailRepository,
                studentScoreRepository,
                studentPassRepository
        );

        String givenStudentName = "pcy";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        // studentScore 예상 인자값
        StudentScore expectStudentScore = StudentScore
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .korScore(givenKorScore)
                .englishScore(givenEnglishScore)
                .mathScore(givenMathScore)
                .build();

        // studentPass 예상 인자값
        StudentPass expectStudentPass = StudentPass
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .avgScore(
                        new MyCalculator(0.0)
                                .add(givenKorScore.doubleValue())
                                .add(givenEnglishScore.doubleValue())
                                .add(givenMathScore.doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        Assertions.assertEquals(studentScoreArgumentCaptor.getValue(), expectStudentScore);

        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        Assertions.assertEquals(studentPassArgumentCaptor.getValue(), expectStudentPass);

        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인 경우")
    public void saveScoreMockTest2() {
        // given : 평균 점수가 60점 미만인 경우
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentFailRepository,
                studentScoreRepository,
                studentPassRepository
        );

        String givenStudentName = "pcy";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 20;
        Integer givenMathScore = 10;

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail.class);

        // studentScore 예상 인자값
        StudentScore expectStudentScore = StudentScore
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .korScore(givenKorScore)
                .englishScore(givenEnglishScore)
                .mathScore(givenMathScore)
                .build();

        // studentPass 예상 인자값
        StudentFail expectStudentFail = StudentFail
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .avgScore(
                        new MyCalculator(0.0)
                                .add(givenKorScore.doubleValue())
                                .add(givenEnglishScore.doubleValue())
                                .add(givenMathScore.doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
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
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentFailRepository,
                studentScoreRepository,
                studentPassRepository
        );

        String givenTestExam = "testexam";

        StudentPass es1 =  StudentPass.builder().id(1L).studentName("pcy").exam(givenTestExam).avgScore(70.0).build();
        StudentPass es2 =  StudentPass.builder().id(2L).studentName("test").exam(givenTestExam).avgScore(80.0).build();
        StudentPass nes1 =  StudentPass.builder().id(3L).studentName("test").exam("mathexam").avgScore(90.0).build();


        // 가짜 데이터 생성
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                es1,
                es2,
                nes1
        ));

        // when
        List<ExamPassStudentResponse> expectResponses = List.of(es1, es2)
                .stream()
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
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentFailRepository,
                studentScoreRepository,
                studentPassRepository
        );

        String givenTestExam = "testexam";

        StudentFail es1 =  StudentFail.builder().id(1L).studentName("pcy").exam(givenTestExam).avgScore(40.0).build();
        StudentFail es2 =  StudentFail.builder().id(2L).studentName("test").exam(givenTestExam).avgScore(50.0).build();
        StudentFail nes1 =  StudentFail.builder().id(3L).studentName("test").exam("mathexam").avgScore(55.0).build();

        // 가짜 데이터 생성
        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                es1,
                es2,
                nes1
        ));

        // when
        List<ExamFailStudentResponse> expectResponses = List.of(es1, es2)
                .stream()
                .map((pass) -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentsList(givenTestExam);

        // then
        Assertions.assertIterableEquals(expectResponses, responses);
    }
}

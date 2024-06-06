package com.pcy.dayonetest.service;

import com.pcy.dayonetest.repository.StudentFailRepository;
import com.pcy.dayonetest.repository.StudentPassRepository;
import com.pcy.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인 경우")
    public void saveScoreMockTes2t() {
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
        Integer givenEnglishScore = 20;
        Integer givenMathScore = 10;

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());
    }
}

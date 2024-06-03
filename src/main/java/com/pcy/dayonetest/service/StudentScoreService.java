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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentScoreService {
    private final StudentFailRepository studentFailRepository;
    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;

    public void saveScore(
            String studentName,
            String exam,
            Integer korScore,
            Integer englishScore,
            Integer mathScore) {
        StudentScore studentScore = StudentScore.builder()
                .exam(exam)
                .studentName(studentName)
                .korScore(korScore)
                .englishScore(englishScore)
                .mathScore(mathScore)
                .build();

        studentScoreRepository.save(studentScore);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator.add(korScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if (avgScore >= 60) {
            studentPassRepository.save(StudentPass.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build());
        } else {
            studentFailRepository.save(StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build());
        }
    }

    public List<ExamPassStudentResponse> getPassStudentsList(String exam) {
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream()
                .filter(l -> l.getExam().equals(exam))
                .map(l -> new ExamPassStudentResponse(l.getStudentName(), l.getAvgScore()))
                .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentsList(String exam) {
        List<StudentFail> studentFails = studentFailRepository.findAll();

        return studentFails.stream()
                .filter(l -> l.getExam().equals(exam))
                .map(l -> new ExamFailStudentResponse(l.getStudentName(), l.getAvgScore()))
                .toList();
    }
}

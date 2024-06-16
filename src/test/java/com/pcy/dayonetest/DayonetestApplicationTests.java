package com.pcy.dayonetest;

import com.pcy.dayonetest.model.StudentScore;
import com.pcy.dayonetest.model.StudentScoreTestDataBuilder;
import com.pcy.dayonetest.repository.StudentScoreRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayonetestApplicationTests extends IntegrationTest {

	@Autowired
	private StudentScoreRepository studentScoreRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		StudentScore studentScore = StudentScoreTestDataBuilder.passed()
				.studentName("pcy")     // 오버라이딩 사용 가능
				.build();
		StudentScore savedStudentScore = studentScoreRepository.save(studentScore);

		entityManager.flush();
		entityManager.clear();

		StudentScore queryStudentScore = studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();
		Assertions.assertEquals(savedStudentScore, savedStudentScore);
	}

}

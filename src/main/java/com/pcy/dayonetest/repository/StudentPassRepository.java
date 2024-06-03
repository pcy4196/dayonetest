package com.pcy.dayonetest.repository;

import com.pcy.dayonetest.model.StudentPass;
import com.pcy.dayonetest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {

}

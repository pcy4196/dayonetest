package com.pcy.dayonetest.repository;

import com.pcy.dayonetest.model.StudentFail;
import com.pcy.dayonetest.model.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {

}

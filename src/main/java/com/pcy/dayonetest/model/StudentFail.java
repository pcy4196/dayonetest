package com.pcy.dayonetest.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_fail")
@Entity
public class StudentFail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_fail_id")
    private Long id;

    @Column(name = "exam")
    private String exam;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "avg_score")
    private Double avgScore;
}

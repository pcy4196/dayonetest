package com.pcy.dayonetest.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class SaveExamScoreRequest {
    private final String studentName;
    private final Integer korScore;
    private final Integer englishScore;
    private final Integer mathScore;
}

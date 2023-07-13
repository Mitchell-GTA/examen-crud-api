package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int totalQuestions;
    private int totalTrue;
    private int score;
}

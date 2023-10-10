package com.exam.domain.ReqVO;

import lombok.Data;

@Data
public class Result {
    private int totalCorrectAnswer;
    private int totalNoOfQuestions;
    private double fullMarks;
    private double totalMarksGained;
    private int totalAttempted;
    private int notVisited;
    private double totalPercentage;

}

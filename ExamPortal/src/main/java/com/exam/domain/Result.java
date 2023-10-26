package com.exam.domain;

import com.exam.domain.ReqVO.QuestionVO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    private Long quizId;
    private String userName;
    private int totalNumberOfQuestions;
    private double fullMarks;
    private double totalMarksGained;
    private int totalQuestionAttempted;
    private int totalCorrectAnswer;
    private int notVisited;
    private double percentage;
    private String totalTimeTaken;
//    private QuestionVO [] questionList;
}

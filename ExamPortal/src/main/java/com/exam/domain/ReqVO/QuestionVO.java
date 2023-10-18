package com.exam.domain.ReqVO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;

@Data
public class QuestionVO {
    private Long questionId;
    private String content;
    private String selectedAnswer;
    private String image;
    private String answer;

}

package com.exam.service;

import com.exam.domain.Question;
import org.springframework.http.ResponseEntity;

public interface QuestionService {
    public ResponseEntity<?> saveQuestion(Question question);
    public ResponseEntity<?> getQuestion(Long questionId);
    public ResponseEntity<?> getAllQuestionsByQuizId(Long quizId);
    public ResponseEntity<?> getLimitedQuestionsByQuizId(Long quizId);
//    public ResponseEntity<?> getAllQuestionsByCategories(Long categoryId);
    public ResponseEntity<?> updateQuestion( Question question);
    public ResponseEntity<?> deleteQuestion(Long questionId);
}

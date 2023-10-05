package com.exam.service;

import com.exam.domain.Quiz;
import org.springframework.http.ResponseEntity;

public interface QuizService {
    public ResponseEntity<?> saveQuiz(Quiz quiz);
    public ResponseEntity<?> getQuizzesByCategory(Long categoryId);
    public ResponseEntity<?> getQuizzes();
    public ResponseEntity<?> getQuiz(Long quizId);
    public ResponseEntity<?> updateQuiz(Quiz quiz);
    public ResponseEntity<?> deleteQuiz(Long quizId);
}

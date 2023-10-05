package com.exam.controller;

import com.exam.domain.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")

public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> saveQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
        return quizService.getQuiz(quizId);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getQuizzesByCategory(@PathVariable Long categoryId) {
        return quizService.getQuizzesByCategory(categoryId);
    }
    @GetMapping("/")
    public ResponseEntity<?> getQuizzesByCategory() {
        return quizService.getQuizzes();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {
        return quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        return quizService.deleteQuiz(quizId);
    }
}

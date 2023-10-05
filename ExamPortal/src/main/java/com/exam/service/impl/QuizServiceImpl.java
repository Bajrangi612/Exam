package com.exam.service.impl;

import com.exam.domain.Category;
import com.exam.domain.Quiz;
import com.exam.response.CategoryRepository;
import com.exam.response.QuizRepository;
import com.exam.response.ResponseDomain;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
   private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return ResponseDomain.successResponse("Quiz Added successfully");

    }

    @Override
    public ResponseEntity<?> getQuizzesByCategory(Long categoryId) {
        List<Quiz> quizList  = new ArrayList<>();
        Category category = categoryRepository.findById(categoryId).get();
        if(category != null){
            quizList = category.getQuizzes();
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getQuizzes() {
        System.out.print("");
        return new ResponseEntity<>(quizRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getQuiz(Long quizId) {
        return new ResponseEntity<>(quizRepository.findById(quizId).get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> updateQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return ResponseDomain.successResponse("Quiz updated successfully");
    }

    @Override
    public ResponseEntity<?> deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
        return ResponseDomain.successResponse("Quiz deleted successfully");

    }
}

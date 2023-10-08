package com.exam.controller;

import com.exam.domain.Question;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionService.deleteQuestion(questionId);
    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> saveQuestion(@PathVariable Long  qid) {
        return questionService.getAllQuestionsByQuizId(qid);
    }

    @GetMapping("/quiz/limited/{qid}")
    public ResponseEntity<?> getLimitedQuestionsByQuizId(@PathVariable Long  qid) {
        return questionService.getLimitedQuestionsByQuizId(qid);
    }
//
//    @PostMapping("/")
//    public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
//        return questionService.saveQuestion(question);
//    }

}

package com.exam.service.impl;

import com.exam.domain.Question;
import com.exam.domain.Quiz;
import com.exam.response.QuestionRepository;
import com.exam.response.QuizRepository;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public ResponseEntity<?> saveQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("Question Added Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getQuestion(Long questionId) {
        return new ResponseEntity<>(questionRepository.findById(questionId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllQuestionsByQuizId(Long quizId) {
        List<Question> questions = new ArrayList<>();
        Quiz quiz = quizRepository.findById(quizId).get();
        if(quiz!= null) {
             questions = quiz.getQuestions();
            Collections.shuffle(questions);
            if(questions.size()>quiz.getNoOfQuestion()+1)
            questions = questions.subList(0, (int) (quiz.getNoOfQuestion() + 1));
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> updateQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("Question Updated Successfully", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return new ResponseEntity<>("Question deleted Successfully", HttpStatus.OK);
    }
}

package com.exam.service.impl;

import com.exam.domain.Options;
import com.exam.domain.Question;
import com.exam.domain.Quiz;
import com.exam.domain.ReqVO.QuestionVO;
import com.exam.domain.ReqVO.ResultEveluateVO;
import com.exam.domain.ReqVO.ResultVO;
import com.exam.domain.Result;
import com.exam.repository.ResultRepository;
import com.exam.response.OptionRepository;
import com.exam.response.QuestionRepository;
import com.exam.response.QuizRepository;
import com.exam.response.ResponseDomain;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public ResponseEntity<?> saveQuestion(Question question) {
        try {
            List<Options> options = question.getOptions();
            options.stream().forEach(options1 -> {
                options1.setQuestion(question);
            });
            question.setOptions(options);
            questionRepository.save(question);
            return ResponseDomain.successResponse("Question Added Successfully");
        } catch (Exception e) {
            return ResponseDomain.badRequest("Something went wrong");
        }


    }

    @Override
    public ResponseEntity<?> getQuestion(Long questionId) {
        return new ResponseEntity<>(questionRepository.findById(questionId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllQuestionsByQuizId(Long quizId) {
        List<Question> questions = new ArrayList<>();
        try {
            Quiz quiz = quizRepository.findById(quizId).get();
            if (quiz != null) {
                questions = quiz.getQuestions();
//            Collections.shuffle(questions);
//            if(questions.size()>quiz.getNoOfQuestion()+1)
//            questions = questions.subList(0, (int) (quiz.getNoOfQuestion() + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getLimitedQuestionsByQuizId(Long quizId) {
        List<Question> questions = new ArrayList<>();
        try {
            Quiz quiz = quizRepository.findById(quizId).get();
            if (quiz != null) {
                questions = quiz.getQuestions();
                Collections.shuffle(questions);
                if (questions.size() > quiz.getNoOfQuestion())
                    questions = questions.subList(0, (int) (quiz.getNoOfQuestion() + 0));
            }
            questions.stream().forEach(f -> f.setAnswer(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> updateQuestion(Question question) {
        questionRepository.save(question);
        return ResponseDomain.successResponse("Question Updated Successfully");


    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return ResponseDomain.successResponse("Question deleted Successfully");
    }

    @Override
    public ResponseEntity<?> evaluateQuiz(ResultEveluateVO resultEveluateVO) {
        ResultVO result = new ResultVO();
        List<Question> questionVOList = resultEveluateVO.getQuestionVOList();
        if (!questionVOList.isEmpty()) {
            List<Long> ids = questionVOList.stream().map(Question::getQuestionId).collect(Collectors.toList());
            List<QuestionRepository.QuestionData> questionData = questionRepository.getQuestionDetail(ids);
            int[] correctAns = {0};
            int[] notVisited = {0};
            int[] attemptedQuestion = {0};
            questionData.forEach(questionData1 -> {
                Question question = questionVOList.stream().filter(f -> f.getQuestionId() == questionData1.getQuestionId()).findAny().orElse(null);
                if (question != null) {
                    if (question.getSelectedAnswer() != null && !question.getSelectedAnswer().equals("")) {
                        if (question.getSelectedAnswer().trim().equals(questionData1.getAnswer().trim())) {
                            correctAns[0]++;
                        }
                        attemptedQuestion[0]++;
                    } else {
                        notVisited[0]++;
                    }
                }
            });
            result.setFullMarks(questionVOList.get(0).getQuiz().getTotalMarks());
            double singleQuestionMarks = result.getFullMarks() / questionVOList.size();
            result.setTotalMarksGained(singleQuestionMarks * correctAns[0]);
            result.setTotalNoOfQuestions(questionVOList.size());
            result.setTotalAttempted(attemptedQuestion[0]);
            result.setTotalCorrectAnswer(correctAns[0]);
            result.setNotVisited(notVisited[0]);
            result.setTotalPercentage((result.getTotalMarksGained() / result.getFullMarks()) * 100);
//            saveResult(resultEveluateVO.getUserName(),resultEveluateVO.getTotalTime(),result,questionVOList);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public void saveResult(String userName,String totalTime,ResultVO resultVO, List<Question> questionVOList) {
        List<QuestionVO> questionVOList1 = new ArrayList<>();
        for (Question q : questionVOList) {
            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionId(q.getQuestionId());
            questionVO.setContent(q.getContent());
            questionVO.setImage(q.getImage());
            questionVO.setAnswer(q.getAnswer());
            questionVO.setSelectedAnswer(q.getSelectedAnswer());
            questionVOList1.add(questionVO);
        }
        QuestionVO [] questions = new QuestionVO[questionVOList1.size()];
        questionVOList1.toArray(questions);
//       QuestionVO[] questions = (QuestionVO[]) questionVOList1.toArray();
            Result result = new Result();
//            result.setQuestionList(questions);
            result.setPercentage(resultVO.getTotalPercentage());
            result.setQuizId(questionVOList.get(0).getQuiz().getQuizId());
            result.setTotalQuestionAttempted(resultVO.getTotalAttempted());
            result.setFullMarks(resultVO.getFullMarks());
            result.setNotVisited(resultVO.getNotVisited());
            result.setTotalCorrectAnswer(resultVO.getTotalCorrectAnswer());
            result.setTotalNumberOfQuestions(resultVO.getTotalNoOfQuestions());
            result.setTotalMarksGained(result.getTotalMarksGained());
            result.setTotalTimeTaken(totalTime);
            result.setUserName( userName);
          Result result1 =   resultRepository.save(result);
        System.out.println(result1);

    }
}

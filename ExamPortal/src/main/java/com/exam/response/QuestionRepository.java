package com.exam.response;

import com.exam.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    public interface QuestionData{
        Long getQuestionId();
        String getAnswer();
    }
    @Query(nativeQuery = true,value = "select question_id as questionId, answer from question where question_id in :ids")
    List<QuestionData> getQuestionDetail(List<Long> ids);
}

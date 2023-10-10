package com.exam.response;

import com.exam.domain.Category;
import com.exam.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByCategory(Category category);

    List<Quiz> findByIsActive(Boolean aTrue);

    List<Quiz> findByIsActiveAndCategory(Boolean aTrue, Category category);
}

package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByContentContaining(String content);
    void deleteByIdAndUserAccount_UserId(Long answerId, String userId);
}

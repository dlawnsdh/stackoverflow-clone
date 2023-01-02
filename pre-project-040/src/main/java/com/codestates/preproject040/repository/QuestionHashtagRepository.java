package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.QuestionHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionHashtagRepository extends JpaRepository<QuestionHashtag, Long> {
    List<QuestionHashtag> findAllByQuestion_Id(Long questionId);
}

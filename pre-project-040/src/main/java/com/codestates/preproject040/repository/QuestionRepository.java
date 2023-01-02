package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    //findBy(컬럼이름)Containing = 컬럼에서 키워드 포함된 것 찾기
    List<Question> findByTitleContaining(String title);
    List<Question> findByContent1Containing(String content1);
    List<Question> findByContent2Containing(String content2);
}
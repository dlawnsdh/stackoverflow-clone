package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findByContent(String content);
}

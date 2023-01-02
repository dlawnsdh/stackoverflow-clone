package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.PostHashtagDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    private final QuestionService questionService;

    public HashtagService(HashtagRepository hashtagRepository, QuestionService questionService) {
        this.hashtagRepository = hashtagRepository;
        this.questionService = questionService;
    }

    //create
    public Hashtag createHashtag(PostHashtagDto postHashtagDto) {
        return hashtagRepository.save(
                Hashtag.of(postHashtagDto.content())
//                        , questionService.findVerifiedQuestion(postHashtagDto.questionId()))
        );
    }

    //delete
    public void deleteHashtag( Long hashtagId){

        hashtagRepository.deleteById(hashtagId);
    }

    public Hashtag findVerifiedHashtag(Long id){
        Optional<Hashtag> optionalHashtag=
                hashtagRepository.findById(id);
        Hashtag findHashtag=
                optionalHashtag.orElseThrow(()->new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findHashtag;
    }

}

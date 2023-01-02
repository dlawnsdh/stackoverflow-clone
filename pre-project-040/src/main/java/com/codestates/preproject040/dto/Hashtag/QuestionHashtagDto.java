package com.codestates.preproject040.dto.Hashtag;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;

public record QuestionHashtagDto(
        Long questionId,

        Long hashtagId

) {
    public static QuestionHashtagDto of(
            Long questionId,
            Long hashtagId
    ){
        return new QuestionHashtagDto(
                questionId, hashtagId
        );
    }

    public static QuestionHashtagDto from(QuestionHashtag questionHashtag){
        return new QuestionHashtagDto(
                questionHashtag.getQuestion().getId(),
                questionHashtag.getHashtag().getId()
        );
    }

    public QuestionHashtagDto toEntity(Question question, Hashtag hashtag){
        return QuestionHashtagDto.of(
                question.getId(),
                hashtag.getId()
        );
    }
}

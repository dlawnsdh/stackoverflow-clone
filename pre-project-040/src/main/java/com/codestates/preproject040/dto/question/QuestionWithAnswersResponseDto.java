package com.codestates.preproject040.dto.question;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.QuestionHashtagDto;
import com.codestates.preproject040.dto.answer.AnswerDto;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionWithAnswersResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        List<QuestionHashtagDto> questionHashtags,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<AnswerDto> answerDtoList
) {
    public static QuestionWithAnswersResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2,
            List<QuestionHashtagDto> questionHashtags,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            List<AnswerDto> answers
    ){
        return new QuestionWithAnswersResponseDto(
                questionId, nickname, title, content1, content2, questionHashtags, createdAt, modifiedAt, answers
        );
    }

    public static QuestionWithAnswersResponseDto from(QuestionDto questionDto){
        return new QuestionWithAnswersResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                questionDto.hashtags(),
                questionDto.createdAt(),
                questionDto.modifiedAt(),
                questionDto.answers()
        );
    }

}

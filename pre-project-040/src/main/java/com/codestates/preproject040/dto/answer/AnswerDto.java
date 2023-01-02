package com.codestates.preproject040.dto.answer;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.user.UserAccountDto;

import java.time.LocalDateTime;


public record AnswerDto(
        Long id,
        Long questionId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AnswerDto of(Long questionId, UserAccountDto userAccountDto, String content) {
        return new AnswerDto(null, questionId, userAccountDto, content, null, null, null, null);
    }

    public static AnswerDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AnswerDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    public static AnswerDto from(Answer answer) {
        return new AnswerDto(
                answer.getId(),
                answer.getQuestion().getId(),
                UserAccountDto.from(answer.getUserAccount()),
                answer.getContent(),
                answer.getCreatedAt(),
                answer.getCreatedBy(),
                answer.getModifiedAt(),
                answer.getModifiedBy()
        );
    }

    public Answer toEntity(Question question, UserAccount userAccount) {
        return Answer.of(
                content,
                userAccount,
                question
        );
    }


}

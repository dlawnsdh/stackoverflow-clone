package com.codestates.preproject040.dto.question;

import com.codestates.preproject040.domain.*;
import com.codestates.preproject040.dto.Hashtag.QuestionHashtagDto;
import com.codestates.preproject040.dto.user.UserAccountDto;
import com.codestates.preproject040.dto.answer.AnswerDto;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionDto(
        Long questionId,
        UserAccountDto userAccountDto,
        String title,
        String content1,
        String content2,
        List<QuestionHashtagDto> hashtags,
        List<AnswerDto> answers,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)
{
    // Post 역할 -userAccount, -questionHashtag
    public static QuestionDto of(
            String title,
            String content1,
            String content2) {

        return new QuestionDto(
                null, null, title, content1, content2, null,
                null, null, null, null, null);
    }

    // Patch 역할 -questionHashtag
    public static QuestionDto of(
            Long id,
            String title,
            String content1,
            String content2) {

        return new QuestionDto(
                id, null, title, content1, content2, null,
                null, null, null, null, null
        );
    }

    public static QuestionDto of(
            String title,
            String content1,
            String content2,
            UserAccountDto userAccountDto) {

        return new QuestionDto(
                null, userAccountDto, title, content1, content2, null,
                null, null, null, null, null);
    }

    public static QuestionDto from(Question question) {
        return new QuestionDto(
                question.getId(),
                UserAccountDto.from(question.getUserAccount()),
                question.getTitle(),
                question.getContent1(),
                question.getContent2(),
                question.getQuestionHashtags().stream()
                        .map(QuestionHashtagDto::from)
                        .toList(),
                question.getAnswers().stream()
                        .map(AnswerDto::from)
                        .toList(),
                question.getCreatedAt(),
                question.getCreatedBy(),
                question.getModifiedAt(),
                question.getModifiedBy()
        );
    }

    //((임시))임시 user 정보 사용
    public Question toEntity(UserAccount userAccount) {
        return Question.of(
                title,
                content1,
                content2,
                userAccount
        );
    }
}
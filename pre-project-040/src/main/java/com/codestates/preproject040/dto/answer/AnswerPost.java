package com.codestates.preproject040.dto.answer;

import com.codestates.preproject040.dto.user.UserAccountDto;

// AnswerPost용
public record AnswerPost (
        Long questionId,
        String content
) {

    public static AnswerPost of(Long questionId, String content) {
        return new AnswerPost(questionId, content);
    }

    public AnswerDto toDto(UserAccountDto userAccountDto) {
        return AnswerDto.of(
                questionId,
                userAccountDto,
                content
        );
    }

}

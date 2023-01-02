package com.codestates.preproject040.dto.answer;

import com.codestates.preproject040.dto.user.UserAccountDto;

public record AnswerPatch(
        Long answerId,
        Long questionId,
        String content
) {
    public static AnswerPatch of(Long answerId, Long questionId, String content) {
        return new AnswerPatch(answerId, questionId, content);
    }


    public AnswerDto toDto(UserAccountDto userAccountDto) {
        return AnswerDto.of(
                questionId,
                userAccountDto,
                content
        );
    }

}

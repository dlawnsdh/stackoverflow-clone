package com.codestates.preproject040.dto.answer;

import java.time.LocalDateTime;

public record AnswerResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String nickname,
        String userId
) {
    public static AnswerResponse of(Long id, String content, LocalDateTime createdAt, String nickname, String userId) {
        return new AnswerResponse(id, content, createdAt, nickname, userId);
    }

    public static AnswerResponse from(AnswerDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new AnswerResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                nickname,
                dto.userAccountDto().userId()
        );
    }

}

package com.codestates.preproject040.dto.question;

import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.user.UserAccountDto;

import java.util.List;

public record QuestionPatch(
        String title,
        String content1,
        String content2,
        List<HashtagDto> hashtags
) {
    public static QuestionPatch of(
            String title,
            String content1,
            String content2,
            List<HashtagDto> hashtags) {

        return new QuestionPatch(
                title, content1, content2, hashtags
        );
    }

    public QuestionDto toDto(UserAccountDto userAccounts) {
        return QuestionDto.of(
                title,
                content1,
                content2,
                userAccounts
        );
    }
}
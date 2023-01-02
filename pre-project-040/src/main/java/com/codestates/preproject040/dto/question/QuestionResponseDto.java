package com.codestates.preproject040.dto.question;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.HashtagResponseDto;
import com.codestates.preproject040.dto.Hashtag.QuestionHashtagDto;
import com.codestates.preproject040.repository.QuestionHashtagRepository;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        List<HashtagResponseDto> hashtags,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static QuestionResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2,
            List<HashtagResponseDto> hashtags,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ){
        return new QuestionResponseDto(
                questionId, nickname, title, content1, content2, hashtags, createdAt, modifiedAt
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                null,
                questionDto.createdAt(),
                questionDto.modifiedAt()
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto, List<HashtagResponseDto> hashtagDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                hashtagDto,
                questionDto.createdAt(),
                questionDto.modifiedAt()
        );
    }
}

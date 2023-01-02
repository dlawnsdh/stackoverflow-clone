package com.codestates.preproject040.dto.Hashtag;

public record HashtagResponseDto(
        Long id,
        String content
) {
    public static HashtagResponseDto from(HashtagDto hashtagDto) {
        return new HashtagResponseDto(
            hashtagDto.Id(),
            hashtagDto.content()
        );
    }

}

package com.codestates.preproject040.dto.Hashtag;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;

import java.time.LocalDateTime;

public record HashtagDto (
        Long Id,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy

)
{
    public static HashtagDto of(
            String content
    ){
        return new HashtagDto(
                null,content,null,null,null,null
        );
    }

    public static HashtagDto from(Hashtag hashtag){
        return new HashtagDto(
                hashtag.getId(),
                hashtag.getContent(),
                hashtag.getCreatedAt(),
                hashtag.getCreatedBy(),
                hashtag.getModifiedAt(),
                hashtag.getModifiedBy()
        );

    }

    public Hashtag toEntity(){
        return Hashtag.of(
                content
        );
    }
}

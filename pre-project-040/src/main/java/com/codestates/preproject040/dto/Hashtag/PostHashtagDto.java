package com.codestates.preproject040.dto.Hashtag;

public record PostHashtagDto(

        String content
) {

//    public static PostHashtagDto of(Long QuestionId, String Content){
//        return new PostHashtagDto(QuestionId,Content);
//    }
    public static PostHashtagDto of(String Content){
        return new PostHashtagDto(Content);
    }

    public PostHashtagDto toDto(){
        return PostHashtagDto.of(
                content
        );


    }
}

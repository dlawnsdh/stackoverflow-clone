package com.codestates.preproject040.dto.user;

public record UserResponse(
        String userId,
        String userPassword,
        String email,
        String nickname,
        String photoUrl,
        String location,
        Integer reputation
) {
    public static UserResponse from(UserAccountDto dto) {
        return new UserResponse(dto.userId(), dto.userPassword(), dto.email(), dto.nickname(), dto.pictureUrl(), dto.location(), dto.reputation());
    }

}

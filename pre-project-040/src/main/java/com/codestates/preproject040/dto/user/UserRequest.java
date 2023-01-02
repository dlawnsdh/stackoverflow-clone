package com.codestates.preproject040.dto.user;

public record UserRequest(
        String userId,
        String userPassword,
        String email,
        String nickname,
        String createdBy
) {
    public static UserRequest of(String userId, String userPassword, String email, String nickname) {
        return new UserRequest(userId, userPassword, email, nickname, userId);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                userId,
                userPassword,
                email,
                nickname
        );
    }

}

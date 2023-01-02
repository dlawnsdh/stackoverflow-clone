package com.codestates.preproject040.dto.user;

import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto (
        String userId,
        String userPassword,
        String email,
        String nickname,
        String pictureUrl,
        String location,
        Integer reputation,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountDto of(String userId, String userPassword, String email, String nickname) {
        return new UserAccountDto(userId, userPassword, email, nickname, null, null, 0, null, userId, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String pictureUrl, String location) {
        return new UserAccountDto(userId, userPassword, email, nickname, pictureUrl, location, 0,null, userId, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String pictureUrl, String location, Integer reputation, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname, pictureUrl, location, reputation, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getPictureUrl(),
                entity.getLocation(),
                entity.getReputation(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                pictureUrl,
                location
        );
    }

}


package com.codestates.preproject040.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoDto {
    private String userId;
    private String userPassword;
    private String email;
    private String nickname;
    private String pictureUrl;
    private String location;

    private UserInfoDto(String userId, String userPassword, String email, String nickname, String pictureUrl, String location) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.pictureUrl = pictureUrl;
        this.location = location;
    }

    public static UserInfoDto of(String username, String userPassword, String email, String nickname) {
        return new UserInfoDto(username, userPassword, email, nickname, null, null);
    }

    public static UserInfoDto of(String username, String userPassword, String email, String nickname, String pictureUrl, String location) {
        return new UserInfoDto(username, userPassword, email, nickname, pictureUrl, location);
    }

    public static UserInfoDto from(UserAccountDto dto) {
        return new UserInfoDto(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname(),
                dto.pictureUrl(),
                dto.location()
        );
    }

    public UserAccountDto toDto() {
        if (pictureUrl == null) pictureUrl = "https://pic.onlinewebfonts.com/svg/img_312847.png";
        if (location == null) location = "-";
        return UserAccountDto.of(
                userId,
                userPassword,
                email,
                nickname,
                pictureUrl,
                location
        );
    }

}

package com.codestates.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {
    @Id
    @Column(length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Setter @Column(length = 100)
    private String email;

    @Setter @Column(length = 100)
    private String nickname;

    @Setter @Column(length = 100)
    private String location;

    @Setter private String pictureUrl;

    @Setter private Integer reputation = 0;

    private UserAccount(String userId, String userPassword, String email, String nickname, String location, String pictureUrl, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.location = location;
        this.pictureUrl = pictureUrl;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

/*    public static UserAccount of(String userId, String userPassword, String email, String nickname, String location, String pictureUrl) {
        return new UserAccount(userId, userPassword, email, nickname, location, pictureUrl, null);
    }*/

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String pictureUrl, String location) {
        return new UserAccount(userId, userPassword, email, nickname, pictureUrl, location, userId);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String location, String pictureUrl, String createdBy) {
        return new UserAccount(userId, userPassword, email, nickname, location, pictureUrl, createdBy);
    }

    public void addReputation() {
        this.reputation++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return userId != null && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}

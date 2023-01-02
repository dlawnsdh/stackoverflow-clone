package com.codestates.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "content", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Hashtag extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, unique = true, length = 50)
    private String content;

    private Hashtag(String content) {
        this.content = content;
    }

    public static Hashtag of(String content) {
        return new Hashtag(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag hashtag)) return false;
        return id != null && id.equals(hashtag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

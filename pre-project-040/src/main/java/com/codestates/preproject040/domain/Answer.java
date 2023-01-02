package com.codestates.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Answer extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 2000)
    private String content;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Setter @ManyToOne(optional = false)
    private Question question;

    private Answer(String content, UserAccount userAccount, Question question) {
        this.content = content;
        this.userAccount = userAccount;
        this.question = question;
    }

    public static Answer of(String content, UserAccount userAccount, Question question) {
        return new Answer(content, userAccount, question);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

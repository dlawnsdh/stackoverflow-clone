package com.codestates.preproject040.domain;

import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Question extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter @Column(nullable = false)
    private String content1;

    @Setter @Column(nullable = false)
    private String content2;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private final List<Answer> answers = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private final List<QuestionHashtag> questionHashtags = new ArrayList<>();

    private Question(String title, String content1, String content2, UserAccount userAccount) {
        this.title = title;
        this.content1 = content1;
        this.content2 = content2;
        this.userAccount = userAccount;
    }

    public static Question of(String title, String content1, String content2, UserAccount userAccount) {
        return new Question(title, content1, content2, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return id != null && id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

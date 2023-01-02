package com.codestates.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuestionHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "questionId")
    private Question question;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "hashtagId")
    private Hashtag hashtag;

    private QuestionHashtag(Question question, Hashtag hashtag) {
        this.question = question;
        this.hashtag = hashtag;
    }

    public static QuestionHashtag of(Question question, Hashtag hashtag) {
        return new QuestionHashtag(question, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionHashtag that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

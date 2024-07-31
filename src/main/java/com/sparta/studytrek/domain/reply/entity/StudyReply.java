package com.sparta.studytrek.domain.reply.entity;

import com.sparta.studytrek.domain.auth.entity.User;
import com.sparta.studytrek.domain.study.entity.Study;
import com.sparta.studytrek.domain.comment.entity.StudyComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class StudyReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;  // Study 엔티티와의 관계 추가

    @ManyToOne
    @JoinColumn(name = "study_comment_id", nullable = false)
    private StudyComment studyComment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    public StudyReply(Study study, StudyComment studyComment, User user, String content) {
        this.study = study;
        this.studyComment = studyComment;
        this.user = user;
        this.content = content;
    }
}

package com.sparta.studytrek.domain.recruitment.entity;

import com.sparta.studytrek.common.Timestamped;
import com.sparta.studytrek.domain.auth.entity.User;
import com.sparta.studytrek.domain.recruitment.dto.RecruitmentRequestDto;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruitment")
@Getter
@NoArgsConstructor
public class Recruitment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;  // 모집글 제목

    @Column(nullable = false)
    private String process; // 과정 소개

    @Column(nullable = false)
    private String content;  // 모집글 내용

    @Column(nullable = false, length = 150)
    private String place;  // 캠프 장소

    @Column(nullable = false)
    private String cost;  // 캠프 비용

    @Column(nullable = false, length = 100)
    private String trek;  // 캠프 주제

    @Column(nullable = false)
    private String level; // 난이도

    @Column(nullable = false)
    private String classTime;  // 수업시간

    @Column(nullable = false)
    private LocalDate campStart;  // 캠프 시작일

    @Column(nullable = false)
    private LocalDate campEnd;  // 캠프 종료일

    @Column(nullable = false)
    private LocalDate recruitStart; // 모집 시작일

    @Column(nullable = false)
    private LocalDate recruitEnd;  // 모집 종료일

    private String campName; // 캠프 이름

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 유저 정보
    
    public Recruitment(RecruitmentRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.process = requestDto.getProcess();
        this.content = requestDto.getContent();
        this.place = requestDto.getPlace();
        this.cost = requestDto.getCost();
        this.trek = requestDto.getTrek();
        this.level = requestDto.getLevel();
        this.classTime = requestDto.getClassTime();
        this.campStart = requestDto.getCampStart();
        this.campEnd = requestDto.getCampEnd();
        this.recruitStart = requestDto.getRecruitStart();
        this.recruitEnd = requestDto.getRecruitEnd();
        this.user = user;
        this.campName = requestDto.getCampName();
    }

    public void updateRecruitment(RecruitmentRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.process = requestDto.getProcess();
        this.content = requestDto.getContent();
        this.place = requestDto.getPlace();
        this.cost = requestDto.getCost();
        this.trek = requestDto.getTrek();
        this.level = requestDto.getLevel();
        this.classTime = requestDto.getClassTime();
        this.campStart = requestDto.getCampStart();
        this.campEnd = requestDto.getCampEnd();
        this.recruitStart = requestDto.getRecruitStart();
        this.recruitEnd = requestDto.getRecruitEnd();
        this.campName = requestDto.getCampName();
    }
}

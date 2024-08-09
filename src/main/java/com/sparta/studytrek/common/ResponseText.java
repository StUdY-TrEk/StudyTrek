package com.sparta.studytrek.common;

import lombok.Getter;

@Getter
public enum ResponseText {
    // admin
    ADMIN_SIGNUP_SUCCESS("관리자 회원가입 성공"),
    ADMIN_LOGIN_SUCCESS("관리자 로그인 성공"),
    ADMIN_USER_DELETE_SUCCESS("유저 회원탈퇴 처리 성공"),
    ADMIN_PROFILE_APPROVE_SUCCESS("프로필 승인 성공"),
    ADMIN_PROFILE_REJECT_SUCCESS("프로필 거절 성공"),
    ADMIN_GET_ALL_PROFILE_SUCCESS("전체 프로필 조회 성공"),
    ADMIN_GET_ROLE_STATUS_SUCCESS("역할: %s, 상태: %s 상태의 프로필 조회 성공"),
    ADMIN_CREATE_CAMP_SUCCESS("부트 캠프 생성 성공"),

    // auth
    AUTH_SIGNUP_SUCCESS("회원가입 성공"),
    AUTH_LOGIN_SUCCESS("로그인 성공"),
    AUTH_LOGOUT_SUCCESS("로그아웃 성공"),
    AUTH_RESIGN_SUCCESS("회원탈퇴 성공"),
    AUTH_TOKEN_REISSUE_SUCCESS("토큰 재발급 성공"),

    // profile
    PROFILE_CREATE_SUCCESS("프로필 생성 성공"),
    PROFILE_GET_SUCCESS("프로필 조회 성공"),
    PROFILE_UPDATE_SUCCESS("프로필 수정 성공"),
    PROFILE_DELETE_SUCCESS("프로필 삭제 성공"),
    PROFILE_APPLY_SUCCESS("프로필 신청 성공"),
    PROFILE_DETAIL_GET_SUCCESS("프로필 상세 조회 성공"),

    // question
    QUESTION_CREATE_SUCCESS("질문 작성 성공"),
    QUESTION_UPDATE_SUCCESS("질문 수정 성공"),
    QUESTION_DELETE_SUCCESS("질문 삭제 성공"),
    QUESTION_GET_ALL_SUCCESS("질문 전체 조회 성공"),
    QUESTION_GET_SUCCESS("질문 단건 조회 성공"),

    // recruitment
    RECRUITMENT_CREATE_SUCCESS("모집글 작성 성공"),
    RECRUITMENT_UPDATE_SUCCESS("모집글 수정 성공"),
    RECRUITMENT_DELETE_SUCCESS("모집글 삭제 성공"),
    RECRUITMENT_GET_ALL_SUCCESS("모집글 전체 조회 성공"),
    RECRUITMENT_GET_SUCCESS("모집글 단건 조회 성공"),
    GET_RECRUITMENT_PERIOD("부트캠프 모집기간 조회 성공"),
    GET_PARTICIPATE_PERIOD("부트캠프 참여기간 조회 성공"),

    // review
    REVIEW_CREATE_SUCCESS("리뷰 작성 성공"),
    REVIEW_UPDATE_SUCCESS("리뷰 수정 성공"),
    REVIEW_DELETE_SUCCESS("리뷰 삭제 성공"),
    REVIEW_GET_ALL_SUCCESS("리뷰 전체 조회 성공"),
    REVIEW_GET_SUCCESS("리뷰 단건 조회 성공"),

    // study
    STUDY_CREATE_SUCCESS("스터디 모집글 작성 성공"),
    STUDY_UPDATE_SUCCESS("스터디 모집글 수정 성공"),
    STUDY_DELETE_SUCCESS("스터디 모집글 삭제 성공"),
    STUDY_GET_ALL_SUCCESS("스터디 전체 조회 성공"),
    STUDY_GET_SUCCESS("스터디 단건 조회 성공"),

    // rank
    RANK_CREATE_SUCCESS("순위 생성 성공"),
    RANK_GET_LIST_SUCCESS("순위 목록 조회 성공"),


    // comment
    COMMENT_CREATE_SUCCESS("댓글 작성 성공"),
    COMMENT_UPDATE_SUCCESS("댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS("댓글 삭제 성공"),
    COMMENT_GET_ALL_SUCCESS("댓글 전체 조회 성공"),
    COMMENT_GET_SUCCESS("댓글 단건 조회 성공"),

    // answer
    ANSWER_CREATE_SUCCESS("답변 작성 성공"),
    ANSWER_UPDATE_SUCCESS("답변 수정 성공"),
    ANSWER_DELETE_SUCCESS("답변 삭제 성공"),
    ANSWER_GET_ALL_SUCCESS("답변 전체 조회 성공"),
    ANSWER_GET_SUCCESS("답변 단건 조회 성공"),

    // reply
    REPLY_CREATE_SUCCESS("대댓글 작성 성공"),
    REPLY_UPDATE_SUCCESS("대댓글 수정 성공"),
    REPLY_DELETE_SUCCESS("대댓글 삭제 성공"),
    REPLY_GET_ALL_SUCCESS("대댓글 전체 조회 성공"),
    REPLY_GET_SUCCESS("대댓글 단건 조회 성공"),

    // like
    LIKE_RECRUIT_SUCCESS("해당 게시글에 좋아요 성공 : %d"),
    LIKE_CALL_OFF_SUCCESS("해당 게시글에 좋아요 취소 : %d");


    private String msg;

    ResponseText(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String format(Object... args) {
        return String.format(msg, args);
    }
}

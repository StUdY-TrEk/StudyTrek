package com.sparta.studytrek.domain.recruitment.controller;

import com.sparta.studytrek.common.ApiResponse;
import com.sparta.studytrek.domain.recruitment.dto.RecruitmentRequestDto;
import com.sparta.studytrek.domain.recruitment.dto.RecruitmentResponseDto;
import com.sparta.studytrek.domain.recruitment.entity.Recruitment;
import com.sparta.studytrek.domain.recruitment.service.RecruitmentService;
import com.sparta.studytrek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/camps")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    /**
     * 모집글 작성 API
     *
     * @param requestDto 모집글 작성 데이터
     * @param userDetails 인증된 유저 정보
     * @return 모집글 작성 응답 데이터
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createRecruitment(@RequestBody RecruitmentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        RecruitmentResponseDto responseDto = recruitmentService.createRecruitment(requestDto, userDetails.getUser());
        ApiResponse response = ApiResponse.builder()
            .msg("모집글 작성 성공")
            .statuscode(String.valueOf(HttpStatus.CREATED.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 모집글 수정

    // 모집글 삭제

    // 모집글 전체 조회

    // 모집글 단건 조회

}
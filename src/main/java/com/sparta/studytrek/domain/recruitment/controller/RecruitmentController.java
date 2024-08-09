package com.sparta.studytrek.domain.recruitment.controller;

import com.sparta.studytrek.aop.RecruitmentRoleCheck;
import com.sparta.studytrek.common.ApiResponse;
import com.sparta.studytrek.common.ResponseText;
import com.sparta.studytrek.domain.recruitment.dto.RecruitmentRequestDto;
import com.sparta.studytrek.domain.recruitment.dto.RecruitmentResponseDto;
import com.sparta.studytrek.domain.recruitment.service.RecruitmentService;
import com.sparta.studytrek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
     * @param requestDto  모집글 작성 데이터
     * @param userDetails 인증된 유저 정보
     * @return 모집글 작성 응답 데이터
     */
    @PostMapping
    @RecruitmentRoleCheck
    public ResponseEntity<ApiResponse> createRecruitment(
        @RequestBody RecruitmentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        RecruitmentResponseDto responseDto = recruitmentService.createRecruitment(requestDto,
            userDetails.getUser());
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.RECRUITMENT_CREATE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.CREATED.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 모집글 수정 API
     *
     * @param id          모집글 ID
     * @param requestDto  모집글 수정 데이터
     * @param userDetails 인증된 유저 정보
     * @return 모집글 수정 응답 데이터
     */
    @PutMapping("/{id}")
    @RecruitmentRoleCheck
    public ResponseEntity<ApiResponse> updateRecruitment(@PathVariable Long id,
        @RequestBody RecruitmentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        RecruitmentResponseDto responseDto = recruitmentService.updateRecruitment(id, requestDto,
            userDetails.getUser());
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.RECRUITMENT_UPDATE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 모집글 삭제
     *
     * @param id          모집글 ID
     * @param userDetails 요청한 유저의 정보
     */
    @DeleteMapping("/{id}")
    @RecruitmentRoleCheck
    public ResponseEntity<ApiResponse> deleteRecruitment(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        recruitmentService.deleteRecruitment(id, userDetails.getUser());
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.RECRUITMENT_DELETE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.NO_CONTENT.value()))
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 모집글 전체 조회
     *
     * @param pageable 페이지 정보
     * @return 모집글 전체 목록
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getAllRecruitments(Pageable pageable)
    {
        Page<RecruitmentResponseDto> responseDtos = recruitmentService.getAllRecruitments(pageable);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.RECRUITMENT_GET_ALL_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDtos)
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 모집글 단건 조회 API
     *
     * @param id 모집글 ID
     * @return 해당 모집글의 응답 데이터
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRecruitment(@PathVariable Long id)
    {
        RecruitmentResponseDto responseDto = recruitmentService.getRecruitment(id);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.RECRUITMENT_GET_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

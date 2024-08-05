package com.sparta.studytrek.domain.admin.controller;

import com.sparta.studytrek.common.ResponseText;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sparta.studytrek.common.ApiResponse;
import com.sparta.studytrek.domain.admin.dto.AdminRequestDto;
import com.sparta.studytrek.domain.admin.dto.AdminResponseDto;
import com.sparta.studytrek.domain.admin.service.AdminService;
import com.sparta.studytrek.domain.auth.dto.TokenResponseDto;
import com.sparta.studytrek.domain.auth.entity.UserRoleEnum;
import com.sparta.studytrek.domain.camp.dto.CampRequestDto;
import com.sparta.studytrek.domain.camp.dto.CampResponseDto;
import com.sparta.studytrek.domain.camp.service.CampService;
import com.sparta.studytrek.domain.profile.dto.ProfileResponseDto;
import com.sparta.studytrek.domain.profile.entity.ProfileStatus;
import com.sparta.studytrek.domain.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final ProfileService profileService;
    private final CampService campService;

    /**
     * 관리자 회원가입 API
     *
     * @param adminRequestDto 관리자 회원가입 요청 데이터
     * @return 관리자 회원가입 응답 데이터
     */
    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponse> adminSignup(@RequestBody AdminRequestDto adminRequestDto)
    {
        AdminResponseDto adminResponseDto = adminService.adminSignup(adminRequestDto);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_SIGNUP_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.CREATED.value()))
            .data(adminResponseDto)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 관리자 로그인 API
     *
     * @param adminRequestDto 로그인 요청 데이터
     * @return JWT 토큰 응답 데이터
     */
    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse> adminLogin(@RequestBody AdminRequestDto adminRequestDto)
    {
        TokenResponseDto responseDto = adminService.adminLogin(adminRequestDto);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_LOGIN_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 관리자가 일반 유저를 강제로 회원탈퇴 시키는 API
     *
     * @param userId 탈퇴시킬 유저의 ID
     * @return 회원탈퇴 성공 응답 데이터
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> adminDelete(@PathVariable Long userId)
    {
        adminService.adminDelete(userId);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_USER_DELETE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .build();
        return ResponseEntity.ok(response);
    }

    /**
     * 프로필 승인 API (관리자용)
     *
     * @param profileId 승인할 프로필의 ID
     * @return 승인 성공 응답 데이터
     */
    @PostMapping("/profiles/{profileId}/approve")
    public ResponseEntity<ApiResponse> approveProfile(@PathVariable Long profileId)
    {
        profileService.approveProfile(profileId);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_PROFILE_APPROVE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 프로필 거절 API (관리자용)
     *
     * @param profileId 거절할 프로필의 ID
     * @return 거절 성공 응답 데이터
     */
    @PostMapping("/profiles/{profileId}/reject")
    public ResponseEntity<ApiResponse> rejectProfile(@PathVariable Long profileId)
    {
        profileService.rejectProfile(profileId);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_PROFILE_REJECT_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 모든 유저의 프로필 전체 조회 (관리자용)
     *
     * @return 조회 성공 응답 데이터
     */
    @GetMapping("/profiles/role/{role}")
    public ResponseEntity<ApiResponse> getAllProfiles(@PathVariable("role") UserRoleEnum role)
    {
        List<ProfileResponseDto> responseDtos = profileService.getProfilesByRole(role).getBody();
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_GET_ALL_PROFILE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDtos)
            .build();
        return ResponseEntity.ok(response);
    }

    /**
     * 프로필 상태별 조회 (관리자용)
     *
     * @param status 조회할 프로필 상태를 선택
     * @return 조회 성공 응답 데이터
     */
    @GetMapping("/profiles/role/{role}/status/{status}")
    public ResponseEntity<ApiResponse> getProfileByStatus(@PathVariable("role") UserRoleEnum role,
        @PathVariable("status") ProfileStatus status)
    {
        List<ProfileResponseDto> responseDtos = profileService.getProfilesByRoleAndStatus(role,
            status).getBody();
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_GET_ROLE_STATUS_SUCCESS.format(role, status))
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDtos)
            .build();
        return ResponseEntity.ok(response);
    }

    /**
     * 부트 캠프 등록 (관리자용)
     *
     * @param campRequestDto 캠프 등록 요청 데이터
     * @return 캠프 등록 성공 응답 데이터
     */
    @PostMapping("/camps")
    public ResponseEntity<ApiResponse> createCamp(@RequestBody CampRequestDto campRequestDto)
    {
        CampResponseDto campResponseDto = campService.createCamp(campRequestDto);
        ApiResponse response = ApiResponse.builder()
            .msg(ResponseText.ADMIN_CREATE_CAMP_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.CREATED.value()))
            .data(campResponseDto)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

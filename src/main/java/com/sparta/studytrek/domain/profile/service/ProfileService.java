package com.sparta.studytrek.domain.profile.service;

import com.sparta.studytrek.domain.auth.entity.UserRoleEnum;
import com.sparta.studytrek.domain.profile.dto.ProfileRequestDto;
import com.sparta.studytrek.domain.profile.dto.ProfileResponseDto;
import com.sparta.studytrek.domain.profile.entity.ProfileStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileService {
	ResponseEntity<ProfileResponseDto> createProfile(String username, ProfileRequestDto requestDto);
	ResponseEntity<List<ProfileResponseDto>> getProfileByUserId(String username);
	ResponseEntity<ProfileResponseDto> updateProfile(Long profileId, ProfileRequestDto requestDto, UserDetails userDetails);
	ResponseEntity<Void> deleteProfile(Long profileId, UserDetails userDetails);
	ResponseEntity<Void> approveProfile(Long profileId);
	ResponseEntity<Void> rejectProfile(Long profileId);
	ResponseEntity<List<ProfileResponseDto>> getProfilesByRole(UserRoleEnum role);
	ResponseEntity<List<ProfileResponseDto>> getProfilesByRoleAndStatus(UserRoleEnum roleEnum, ProfileStatus status);
	ResponseEntity<Void> applyForProfile(Long profileId, UserDetails userDetails);
}

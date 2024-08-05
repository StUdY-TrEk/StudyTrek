package com.sparta.studytrek.domain.profile.dto;

import java.time.LocalDate;
import java.util.Set;

import com.sparta.studytrek.domain.auth.entity.Role;
import com.sparta.studytrek.domain.profile.entity.Profile;
import com.sparta.studytrek.domain.profile.entity.ProfileStatus;

import lombok.Getter;

@Getter
public class ProfileResponseDto {
	private final Long id;
	private final String bootcampName;
	private final String track;
	private final String generation;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final Set<String> techStack;
	private final String certificate;
	private final ProfileStatus status;
	private final String name;
	private final Role role;

	public ProfileResponseDto(Profile profile) {
		this.id = profile.getId();
		this.bootcampName = profile.getBootcampName();
		this.track = profile.getTrack();
		this.generation = profile.getGeneration();
		this.startDate = profile.getStartDate();
		this.endDate = profile.getEndDate();
		this.techStack = profile.getTechStack();
		this.certificate = profile.getCertificate();
		this.status = profile.getStatus();
		this.name = profile.getUser().getName();
		this.role = profile.getUser().getRole();
	}
}

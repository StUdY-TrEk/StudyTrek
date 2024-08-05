package com.sparta.studytrek.domain.profile.entity;

import java.time.LocalDate;
import java.util.Set;

import com.sparta.studytrek.common.Timestamped;
import com.sparta.studytrek.common.exception.CustomException;
import com.sparta.studytrek.common.exception.ErrorCode;
import com.sparta.studytrek.domain.auth.entity.User;
import com.sparta.studytrek.domain.profile.dto.ProfileRequestDto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Profile extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bootcampName;
	private String track;
	private String generation;
	private LocalDate startDate;
	private LocalDate endDate;

	@ElementCollection
	@CollectionTable(name = "profile_tech_stack", joinColumns = @JoinColumn(name = "profile_id"))
	@Column(name = "tech_stack")
	private Set<String> techStack;

	private String certificate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private ProfileStatus status = ProfileStatus.BASIC;

	public Profile(String bootcampName, String track, String generation, LocalDate startDate, LocalDate endDate,
		String certificate, User user, Set<String> techStack) {
		this.bootcampName = bootcampName;
		this.track = track;
		this.generation = generation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.certificate = certificate;
		this.user = user;
		this.techStack = techStack;
		this.status = ProfileStatus.BASIC;
	}

	public void updateProfile(ProfileRequestDto requestDto) {
		this.bootcampName = requestDto.getBootcampName();
		this.track = requestDto.getTrack();
		this.generation = requestDto.getGeneration();
		this.startDate = requestDto.getStartDate();
		this.endDate = requestDto.getEndDate();
		this.certificate = requestDto.getCertificate();
		this.techStack = requestDto.getTechStack();
	}

	public void approveProfile() {
		this.status = ProfileStatus.APPROVED;
	}
	public void rejectProfile() {
		this.status = ProfileStatus.REJECTED;
	}
	public void apply() {
		if (this.status == ProfileStatus.BASIC) {
			this.status = ProfileStatus.PENDING;
		} else {
			throw new CustomException(ErrorCode.PROFILE_STATUS_NOT_ALLOWED);
		}
	}
}

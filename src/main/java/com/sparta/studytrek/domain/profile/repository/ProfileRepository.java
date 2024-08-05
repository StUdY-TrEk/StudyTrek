package com.sparta.studytrek.domain.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.studytrek.domain.auth.entity.Role;
import com.sparta.studytrek.domain.auth.entity.UserRoleEnum;
import com.sparta.studytrek.domain.profile.entity.Profile;
import com.sparta.studytrek.domain.profile.entity.ProfileStatus;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	List<Profile> findAllByUserId(Long userId);
	List<Profile> findAllByStatus(ProfileStatus status);
	List<Profile> findAllByUser_Role(Role role);
	List<Profile> findAllByUser_RoleAndStatus(Role role, ProfileStatus status);
}

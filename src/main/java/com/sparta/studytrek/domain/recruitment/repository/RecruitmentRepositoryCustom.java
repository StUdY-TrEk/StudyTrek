package com.sparta.studytrek.domain.recruitment.repository;

import com.sparta.studytrek.domain.recruitment.dto.ParticipatePeriodResponseDto;
import com.sparta.studytrek.domain.recruitment.dto.RecruitPeriodResponseDto;
import com.sparta.studytrek.domain.recruitment.entity.Recruitment;
import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<RecruitPeriodResponseDto> findAllRecruitPeriods();
    List<ParticipatePeriodResponseDto> findAllParticipates();
    List<Recruitment> filterRecruitments(List<String> treks, List<String> places, List<String> costs);
}

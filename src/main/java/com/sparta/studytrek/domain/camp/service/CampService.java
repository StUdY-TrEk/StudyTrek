package com.sparta.studytrek.domain.camp.service;

import com.sparta.studytrek.common.exception.CustomException;
import com.sparta.studytrek.common.exception.ErrorCode;
import com.sparta.studytrek.domain.camp.dto.CampRequestDto;
import com.sparta.studytrek.domain.camp.dto.CampResponseDto;
import com.sparta.studytrek.domain.camp.entity.Camp;
import com.sparta.studytrek.domain.camp.repository.CampRepository;
import com.sparta.studytrek.domain.rank.entity.Rank;
import com.sparta.studytrek.domain.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampService {

    private static final int DEFAULT_RANK_INCREMENT = 1;
    private final CampRepository campRepository;
    private final RankRepository rankRepository;

    public Camp findByName(String campName) {
        return campRepository.findByName(campName)
            .orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_CAMP));
    }

    public Camp findById(Long id) {
        return campRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_CAMP));
    }

    public CampResponseDto createCamp(CampRequestDto requestDto) {
        campRepository.findByName(requestDto.name()).ifPresent(
            camp -> {throw new CustomException(ErrorCode.DUPLICATE_CAMP_NAME);}
        );
        Camp camp = new Camp(requestDto.name(), requestDto.description(), requestDto.imageUrl());
        Camp savedCamp = campRepository.save(camp);

        Integer maxRanking = rankRepository.findMaxRanking().orElse(0);
        Integer newRanking = maxRanking + DEFAULT_RANK_INCREMENT;
        Rank rank = new Rank(savedCamp, newRanking);
        rankRepository.save(rank);
        return new CampResponseDto(savedCamp.getId(), savedCamp.getName(), savedCamp.getDescription(), savedCamp.getImageUrl());
    }
}

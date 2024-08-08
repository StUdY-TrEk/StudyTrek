package com.sparta.studytrek.domain.camp.service;

import static com.sparta.studytrek.domain.camp.entity.QCamp.*;

import com.sparta.studytrek.common.exception.CustomException;
import com.sparta.studytrek.common.exception.ErrorCode;
import com.sparta.studytrek.domain.camp.dto.CampRequestDto;
import com.sparta.studytrek.domain.camp.dto.CampResponseDto;
import com.sparta.studytrek.domain.camp.entity.Camp;
import com.sparta.studytrek.domain.camp.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampService {

    private final CampRepository campRepository;

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
        Camp camp = new Camp(requestDto.name(), requestDto.description());
        Camp savedCamp = campRepository.save(camp);
        return new CampResponseDto(savedCamp.getId(), savedCamp.getName(), savedCamp.getDescription());
    }
}

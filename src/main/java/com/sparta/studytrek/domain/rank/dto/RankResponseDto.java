package com.sparta.studytrek.domain.rank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RankResponseDto {

    private Long id;
    private Long campId;
    private String campName;
    private Integer ranking;
    private String campImage;

    public RankResponseDto(Long id, Long campId, String campName, Integer ranking, String campImage) {
        this.id = id;
        this.campId = campId;
        this.campName = campName;
        this.ranking = ranking;
        this.campImage = campImage;
    }
}

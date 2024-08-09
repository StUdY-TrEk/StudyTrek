package com.sparta.studytrek.domain.rank.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RankListResponseDto {
    private List<RankResponseDto> ranks;
    private int totalPages;
    private long totalElements;

    public RankListResponseDto(List<RankResponseDto> ranks, int totalPages, long totalElements) {
        this.ranks = ranks;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
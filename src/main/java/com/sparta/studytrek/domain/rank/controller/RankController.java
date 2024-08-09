package com.sparta.studytrek.domain.rank.controller;

import com.sparta.studytrek.common.ApiResponse;
import com.sparta.studytrek.common.ResponseText;
import com.sparta.studytrek.domain.rank.dto.RankListResponseDto;
import com.sparta.studytrek.domain.rank.dto.RankRequestDto;
import com.sparta.studytrek.domain.rank.dto.RankResponseDto;
import com.sparta.studytrek.domain.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ranks")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    /**
     * 새 순위를 생성하는 API
     *
     * @param requestDto 순위를 생성하기 위한 데이터가 담긴 DTO 객체
     *                  (campId: 캠프 ID, ranking: 순위 값)
     * @return 생성된 순위의 정보를 담고 있는 ApiResponse 객체를 포함한 응답
     */
    @PostMapping
    public ResponseEntity<ApiResponse<RankResponseDto>> createRank(@RequestBody RankRequestDto requestDto) {
        RankResponseDto responseDto = rankService.createRank(requestDto);
        ApiResponse<RankResponseDto> response = ApiResponse.<RankResponseDto>builder()
            .msg(ResponseText.RANK_CREATE_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.CREATED.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 모든 순위를 페이지 단위로 조회하는 API
     *
     * @param pageable 페이징 처리를 위한 Pageable 객체
     *                (page: 페이지 번호, size: 페이지 크기, sort: 정렬 기준)
     * @return 순위 목록을 담고 있는 ApiResponse 객체를 포함한 응답
     */
    @GetMapping
    public ResponseEntity<ApiResponse<RankListResponseDto>> getAllRanks(Pageable pageable) {
        RankListResponseDto responseDto = rankService.getAllRanks(pageable);
        ApiResponse<RankListResponseDto> response = ApiResponse.<RankListResponseDto>builder()
            .msg(ResponseText.RANK_GET_LIST_SUCCESS.getMsg())
            .statuscode(String.valueOf(HttpStatus.OK.value()))
            .data(responseDto)
            .build();
        return ResponseEntity.ok(response);
    }
}
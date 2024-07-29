package com.sparta.studytrek.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRequestDto {

    @NotBlank(message = "제목은 필수 입력입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;

    @NotBlank(message = "별점은 필수 입력입니다.")
    private int scope;

    @NotBlank(message = "카테고리 선택은 필수입니다.")
    private String category;

    @NotBlank(message = "트랙 선택은 필수입니다.")
    private String trek;
}
package com.sparta.studytrek.domain.rank.repository;

import com.sparta.studytrek.domain.rank.entity.Rank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RankRepositoryCustom {
    Page<Rank> findAllOrderByRankingAsc(Pageable pageable);
    Optional<Integer> findMaxRanking();
}
package com.sparta.studytrek.domain.rank.repository;

import com.sparta.studytrek.domain.rank.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long>, RankRepositoryCustom {
    Optional<Integer> findMaxRanking();
}
package com.sparta.studytrek.domain.rank.repository;

import static com.sparta.studytrek.domain.rank.entity.QRank.rank;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.studytrek.domain.rank.entity.Rank;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class RankRepositoryCustomImpl implements RankRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Rank> findAllOrderByRankingAsc(Pageable pageable) {
        List<Rank> ranks = queryFactory
            .selectFrom(rank)
            .orderBy(rank.ranking.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = queryFactory
            .select(rank.count())
            .from(rank)
            .fetchOne();

        return PageableExecutionUtils.getPage(ranks, pageable, () -> totalCount);
    }

    @Override
    public Optional<Integer> findMaxRanking() {
        Integer maxRanking = queryFactory
            .select(rank.ranking.max())
            .from(rank)
            .fetchOne();
        return Optional.ofNullable(maxRanking);
    }
}
package com.sparta.studytrek.domain.recruitment.repository;

import static com.sparta.studytrek.domain.recruitment.entity.QRecruitment.recruitment;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.studytrek.domain.recruitment.dto.ParticipatePeriodResponseDto;
import com.sparta.studytrek.domain.recruitment.dto.RecruitPeriodResponseDto;
import com.sparta.studytrek.domain.recruitment.entity.QRecruitment;
import com.sparta.studytrek.domain.recruitment.entity.Recruitment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecruitPeriodResponseDto> findAllRecruitPeriods() {
        return queryFactory
            .select(Projections.constructor(
                RecruitPeriodResponseDto.class,
                recruitment.id,
                recruitment.title,
                recruitment.recruitStart,
                recruitment.recruitEnd
            ))
            .from(recruitment)
            .fetch();
    }

    @Override
    public List<ParticipatePeriodResponseDto> findAllParticipates() {
        return queryFactory
            .select(Projections.constructor(
                ParticipatePeriodResponseDto.class,
                recruitment.id,
                recruitment.title,
                recruitment.campStart,
                recruitment.campEnd
            ))
            .from(recruitment)
            .fetch();
    }

    @Override
    public List<Recruitment> filterRecruitments(List<String> treks, List<String> places,
        List<String> costs) {
        QRecruitment recruitment = QRecruitment.recruitment;
        BooleanBuilder builder = new BooleanBuilder();

        if (treks != null && !treks.isEmpty()) {
            builder.and(recruitment.trek.in(treks));
        }
        if (places != null && !places.isEmpty()) {
            builder.and(recruitment.place.in(places));
        }
        if (costs != null && !costs.isEmpty()) {
            builder.and(recruitment.cost.in(costs));
        }

        return queryFactory
            .selectFrom(recruitment)
            .where(builder)
            .fetch();
    }
}

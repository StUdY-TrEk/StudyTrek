package com.sparta.studytrek.domain.rank.entity;

import com.sparta.studytrek.domain.camp.entity.Camp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rankings")
@Getter
@NoArgsConstructor
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;

    @Column(nullable = false)
    private Integer ranking;

    public Rank(Camp camp, Integer ranking) {
        this.camp = camp;
        this.ranking = ranking;
    }
}
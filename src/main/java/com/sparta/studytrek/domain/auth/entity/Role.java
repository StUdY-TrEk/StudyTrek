package com.sparta.studytrek.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "roles")
@Getter
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public Role(UserRoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.name();
    }

    @PrePersist
    protected void onCreate() {
        if (this.role == null) {
            this.role = UserRoleEnum.getDefault();
        }
    }
}

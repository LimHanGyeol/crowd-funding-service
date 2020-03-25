package me.hangyeol.crowdfunding.project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    Funding findByProjectId (UUID projectId);
}

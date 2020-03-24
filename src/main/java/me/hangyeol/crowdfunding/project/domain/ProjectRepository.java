package me.hangyeol.crowdfunding.project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Project findByTitle(String title);
}

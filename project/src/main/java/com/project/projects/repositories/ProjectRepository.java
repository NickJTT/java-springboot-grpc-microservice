package com.project.projects.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projects.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  // Project findByName(String name);
}

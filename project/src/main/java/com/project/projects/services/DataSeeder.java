package com.project.projects.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.projects.entities.Project;
import com.project.projects.repositories.ProjectRepository;

@Configuration
public class DataSeeder {
  @Bean
    CommandLineRunner seedDatabase(ProjectRepository projectRepository) {
        return args -> {
            if (projectRepository.count() == 0) { // avoid duplicate seeding
                Project p1 = new Project(null, "Project One", "First project description");
                Project p2 = new Project(null, "Project Two", "Second project description");
                Project p3 = new Project(null, "Project Three", "Third project description");

                projectRepository.save(p1);
                projectRepository.save(p2);
                projectRepository.save(p3);
            }
        };
    }
}

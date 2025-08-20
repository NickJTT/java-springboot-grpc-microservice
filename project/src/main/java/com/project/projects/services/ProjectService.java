package com.project.projects.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.projects.entities.Project;
import com.project.projects.exceptions.ProjectNotFoundException;
import com.project.projects.repositories.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Project> getProjects(int skip, int take) {
      Pageable pageable = PageRequest.of(skip / take, take);
      return projectRepository.findAll(pageable).getContent();
    }

    public Project getProject(Long id) {
      return projectRepository.findById(id).orElse(null);
    }

    public Project updateProject(Long id, Project project)  {
        Project updatedProject = projectRepository.findById(id).orElseThrow(
          () -> new ProjectNotFoundException("A project with id: " + id.toString() + " was not found")
        );

        updatedProject.setName(project.getName());
        updatedProject.setDescription(project.getDescription());

        projectRepository.save(updatedProject);

        return updatedProject;
    }

    public Project updateProjectPartially(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
      Project project = projectRepository.findById(id).orElse(null);
      JsonNode patched = patch.apply(objectMapper.convertValue(project, JsonNode.class));
      Project patchedProject = this.objectMapper.treeToValue(patched, Project.class);
      Project updatedProject = this.updateProject(id, patchedProject);
      return updatedProject;
    }

    public void createProject(Project project) {
      projectRepository.save(project);
    }

    public void deleteProject(Long id) {
      projectRepository.deleteById(id);
    }
}

package com.project.projects.entities;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="projects")
public class Project implements Serializable {
    public Project() {

    }

    public Project(Long id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    @Column(name="name")
    private String name;

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Column(name="description")
    private String description;

    public String getDescription() {
      return this.description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
}

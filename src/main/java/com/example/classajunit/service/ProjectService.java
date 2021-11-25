package com.example.classajunit.service;

import com.example.classajunit.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> all();

    Project findByProjectName(String projectName);

    Project findByTeamLead(String teamLead);


    Project findById(Long id);

    Project create(Project project);

    Project update(Long id, Project project);

    void remove(Long id);

    boolean existsById(Long id);
}

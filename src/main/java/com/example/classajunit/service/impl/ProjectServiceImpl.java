package com.example.classajunit.service.impl;

import com.example.classajunit.model.Project;
import com.example.classajunit.repository.ProjectRepository;
import com.example.classajunit.service.ProjectService;
import com.example.classajunit.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Project> all() {
        return projectRepository.findAll();
    }

    @Override
    public Project findByProjectName(String mobilePhone) {
        return projectRepository.findByProjectName(mobilePhone)
                .orElseThrow(() -> new CustomException("Project with this name not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Project findByTeamLead(String teamlead) {
        return projectRepository.findByTeamLead(teamlead);
    }


    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Project with this id not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Project create(Project project) {
        if (projectRepository.existsByProjectName(project.getProjectName()))
            throw new CustomException("Project Name already taken", HttpStatus.BAD_REQUEST);

        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (!optionalProject.isPresent())
            throw new CustomException("Project with this id not found", HttpStatus.NOT_FOUND);

        if (projectRepository.existsByProjectName(project.getProjectName()) && !optionalProject.get().getTeamLead().equalsIgnoreCase(project.getCountryOfImp()))
            throw new CustomException("Project already registered", HttpStatus.BAD_REQUEST);

        project.setId(id);

        return projectRepository.save(project);
    }

    @Override
    public void remove(Long id) {
        if (!existsById(id))
            throw new CustomException("there is no project with this id", HttpStatus.NOT_FOUND);

        projectRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }
}

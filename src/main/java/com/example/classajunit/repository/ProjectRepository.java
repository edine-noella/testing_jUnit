package com.example.classajunit.repository;

import com.example.classajunit.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectName(String projectName);

    Project findByTeamLead(String teamLead);

    boolean existsByProjectName(String projectName);
}

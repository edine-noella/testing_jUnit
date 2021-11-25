package com.example.classajunit.service;

import com.example.classajunit.model.Project;
import com.example.classajunit.repository.ProjectRepository;
import com.example.classajunit.service.impl.ProjectServiceImpl;
import com.example.classajunit.utils.CustomException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectRepositoryImpl;

    @Test
    public void all_test() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000),
                new Project(2L, "EzZA", "Edine","RWANDA",100000)));

        assertEquals(2, projectRepositoryImpl.all().size());
    }

    @Test
    public void findByProjectName_test() {
        when(projectRepository.findByProjectName("MAPIPO")).thenReturn(Optional.of(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000)));

        assertEquals("Uwera", projectRepository.findByProjectName("MAPIPO").get().getTeamLead());
    }

    @Test
    public void findByProjectName_test_404() {
        when(projectRepository.findByProjectName(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> projectRepositoryImpl.findByProjectName("ULL"));

        assertEquals(exception.getMessage(), "Project with this name not found");
    }

    @Test
    public void findByTeamLead() {
        when(projectRepository.findByTeamLead("Uwera")).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));
        assertEquals("Uwera", projectRepository.findByTeamLead("Uwera").getTeamLead());
    }


    @Test
    public void findById_test() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000)));
        assertEquals("MAPIPO", projectRepositoryImpl.findById(1L).getProjectName());
    }


    @Test
    public void findById_test_404() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> projectRepositoryImpl.findById(7L));

        assertEquals("Project with this id not found", exception.getMessage());
    }

    @Test
    public void create_test() {
        when(projectRepository.save(any(Project.class))).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        assertEquals("MAPIPO", projectRepositoryImpl.create(new Project()).getProjectName());
    }

    @Test
    public void create_test_duplicatePojectNames() {
        when(projectRepository.existsByProjectName(anyString())).thenReturn(true);

        Exception exception = assertThrows(CustomException.class, () -> projectRepositoryImpl.create(new Project(32L, "EzZA", "Edine","RWANDA",100000)));

        assertEquals("Project Name already taken", exception.getMessage());
    }

    @Test
    public void update_test() {
        when(projectRepository.save(any(Project.class))).thenReturn(new Project(1L, "EzZA", "Edine","RWANDA",100000));
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project(1L, "EzZA", "Edine","RWANDA",100000)));
        when(projectRepository.existsByProjectName(anyString())).thenReturn(false);

        Project updated = projectRepositoryImpl.update(1L, new Project(22L, "EzZA", "ty","RWANDA",90000));

        assertEquals("EzZA", updated.getProjectName());
    }

    @Test
    public void update_test_idNotFound() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> projectRepositoryImpl.update(1L, new Project(1L, "EzZA", "Edine","RWANDA",100000)));

        assertEquals("Project with this id not found", exception.getMessage());
    }

    @Test
    public void update_test_nameAlreadyTaken() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project(1L, "EzZA", "Edine","RWANDA",100000)));
        when(projectRepository.existsByProjectName(anyString())).thenReturn(true);

        Exception exception = assertThrows(CustomException.class, () -> projectRepositoryImpl.update(1L, new Project(2L, "MAPIPO", "Uwera","RWANDA",50000)));

        assertEquals("Project already registered", exception.getMessage());
    }
}

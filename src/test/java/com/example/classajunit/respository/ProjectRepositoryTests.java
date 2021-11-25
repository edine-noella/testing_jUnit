package com.example.classajunit.respository;

import com.example.classajunit.model.Project;
import com.example.classajunit.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ProjectRepositoryTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findAll_test() {
        List<Project> projects = projectRepository.findAll();
        assertEquals(projects.size(), 8);
    }

    @Test
    public void findById_test() {
        Optional<Project> project = projectRepository.findById(1L);

        if (!project.isPresent())
            fail("Project with this id not found");

        assertEquals(project.get().getProjectName(), "mapipo");
    }

    @Test
    public void findByProjectName_test() {
        Optional<Project> project = projectRepository.findByProjectName("mapipo");

        if(!project.isPresent())
            fail("project not found");

        assertEquals(project.get().getId(), Long.valueOf(1));
    }



    @Test
    public void remove_test(){
        projectRepository.deleteById(1L);

        List<Project> projects = projectRepository.findAll();

        // it would be 14 but I removed 1 now remaining 13

        assertEquals(projects.size(), 7);
    }

    @Test
    public void existsById_test(){
        boolean res = projectRepository.existsById(2L);

        assertTrue(res);
    }

    @Test
    public void existsById_test_notThere(){
        boolean res = projectRepository.existsById(16L);

        assertFalse(res);
    }
}

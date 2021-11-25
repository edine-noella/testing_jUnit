package com.example.classajunit.controller;

import com.example.classajunit.model.Project;
import com.example.classajunit.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void getAll_test() throws Exception {
        when(projectService.all()).thenReturn(Arrays.asList(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000),
                new Project(2L, "EzZA", "Edine","RWANDA",100000)));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findById_test() throws Exception {
        when(projectService.findById(anyLong())).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findById_NotFound_test() throws Exception {
        when(projectService.findById(1L)).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/90").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isNotFound()).andExpect(content().json("{\"status\":false,\"message\":\"project not found\"}")).andReturn();
    }

    @Test
    public void findByProjectName_test() throws Exception {
        when(projectService.findByProjectName("MAPIPO")).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/by-projectName/MAPIPO").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findByProjectName_NotFound_test() throws Exception {
        when(projectService.findByProjectName("MAPIPO")).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/by-projectName/anyproject").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isNotFound()).andExpect(content().json("{\"status\":false,\"message\":\"Not Found\"}")).andReturn();
    }

    @Test
    public void findByTeamLead_test() throws Exception {
        when(projectService.findByTeamLead("Uwera")).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/by-teamLead/Uwera").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findByTeamLead_Notfound_test() throws Exception {
        when(projectService.findByTeamLead("Uwera")).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",50000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/projects/by-teamLead/anyone").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isNotFound()).andExpect(content().json("{\"status\":false,\"message\":\"Not Found\"}")).andReturn();
    }


    @Test
    public void create_test() throws Exception {
        when(projectService.create(any(Project.class))).thenReturn(new Project(1L, "MAPIPO", "Uwera","RWANDA",9000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"projectName\":\"MAPIPO\",\"teamLead\":\"Uwera\",\"countryOfImp\":\"RWANDA\",\"projectCost\":9000 }");

        mockMvc.perform(request).andExpect(status().isCreated()).andExpect(content().json(" {\"id\":1,\"projectName\":\"MAPIPO\",\"teamLead\":\"Uwera\",\"countryOfImp\":\"RWANDA\",\"projectCost\":9000}")).andReturn();
    }

    @Test
    public void update_test() throws Exception {

        when(projectService.update(anyLong(), any(Project.class))).thenReturn(new Project(1L, "GOODFOOD", "tracy","RWANDA",9000));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"projectName\":\"GOODFOOD\",\"teamLead\":\"tracy\",\"countryOfImp\":\"RWANDA\",\"projectCost\":9000 }");

        mockMvc.perform(request).andExpect(status().isAccepted()).andExpect(content().json(" {\"id\":1,\"projectName\":\"GOODFOOD\",\"teamLead\":\"tracy\",\"countryOfImp\":\"RWANDA\",\"projectCost\":9000}")).andReturn();
    }


    @Test
    public void remote_test() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isAccepted()).andExpect(content().string("Removed")).andReturn();
    }
}

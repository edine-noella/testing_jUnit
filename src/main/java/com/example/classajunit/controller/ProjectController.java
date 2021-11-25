package com.example.classajunit.controller;

import com.example.classajunit.model.Project;
import com.example.classajunit.service.ProjectService;
import com.example.classajunit.utils.APIResponse;
import com.example.classajunit.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public ResponseEntity<?> all() {
        return Formatter.ok(projectService.all());
    }

    @GetMapping("/by-projectName/{projectName}")
    public ResponseEntity<?> findByProjectName(@PathVariable String projectName) {
        Project project= projectService.findByProjectName(projectName);
        if(project!=null){return ResponseEntity.ok(project);}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false,"Not Found"));
    }

    @GetMapping("/by-teamLead/{teamLead}")
    public ResponseEntity<?> findByTeamLead(@PathVariable String teamLead) {
        Project project= projectService.findByTeamLead(teamLead);
        if(project!=null){return ResponseEntity.ok(project);}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false,"Not Found"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
       Project project= projectService.findById(id);
       if(project!=null){return ResponseEntity.ok(project);}
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false,"project not found"));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Project project) {
        return Formatter.send(projectService.create(project), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Project project) {
        return Formatter.send(projectService.update(id, project), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {

        projectService.remove(id);

        return Formatter.send("Removed", HttpStatus.ACCEPTED);
    }
}

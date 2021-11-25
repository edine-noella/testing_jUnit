package com.example.classajunit.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true,name = "project_name", nullable = false)
    private String projectName;
    @Column(name = "team_lead", nullable = false)
    private String teamLead;
    @Column(name = "country_of_imp", nullable = false)
    private String countryOfImp;
    @Column(name = "project_cost", nullable = false)
    private int projectCost;

    public Project() {
    }

    public Project(Long id, String projectName, String teamLead, String countryOfImp, int projectCost) {
        this.id = id;
        this.projectName = projectName;
        this.teamLead = teamLead;
        this.countryOfImp = countryOfImp;
        this.projectCost = projectCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public String getCountryOfImp() {
        return countryOfImp;
    }

    public void setCountryOfImp(String countryOfImp) {
        this.countryOfImp = countryOfImp;
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }
}

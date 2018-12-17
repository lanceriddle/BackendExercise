/*
package com.intuit.cg.backendtechassessment.services;

import com.intuit.cg.backendtechassessment.marketplace.model.Project;
import com.intuit.cg.backendtechassessment.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Integer id, String description, Integer maxBudget, Date deadlineForBids) {
        if(!projectRepository.existsById(id)) {
            return projectRepository.save(new Project(id, description, maxBudget, deadlineForBids));
        }
        return null;
    }

    public Iterable<Project> lookup() {
        return projectRepository.findAll();
    }
}
*/

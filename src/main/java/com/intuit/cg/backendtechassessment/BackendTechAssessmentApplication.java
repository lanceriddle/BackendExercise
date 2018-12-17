package com.intuit.cg.backendtechassessment;

import com.intuit.cg.backendtechassessment.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/*
	Domain Model Description:

		Actor: "Seller"
		Member Variables:
			+id
			+projectIds (List of IDs of Projects this Seller has created).
		Actions:
			1. Post a Project.
			2. Retrieve a Project by ID so it can determine the lowest Bid.

		Actor: "Buyer"
		Member Variables:
			+id
		Actions:
			1. Get a list of available Projects.
			2. Place a Bid on one or multiple Projects.

		Object: "Project" (Instances of these objects will be persisted in the "ProjectRepository".
		Member Variables:
			+id
			+description
			+maximumBudget
			+deadlineForBids
			+lowestBid

		Object: "Bid"
		Member Variables:
			+buyerId (Which buyer to link back to if this Bid is the winner)
			+projectId (Which Project to apply this Bid to)
			+amount

	Technologies:
		- Attempted to use Spring Data JPA (Java Persistent API) in order to take advantage of its built-in data storage and access features.
			- This was my first attempt learning this repository tool.
		- ProjectRepository extends the CrudRepository because that provides the interface for many Save/Find/Delete/etc methods by default.
		- Attempted to use Spring Boot and REST services, but this was also a first attempt in my experience.
 */

// Implement CommandLineRunner so we can pre-populate the data quickly.
@SpringBootApplication
public class BackendTechAssessmentApplication implements CommandLineRunner {

	@Autowired
	private ProjectService projectService;

	public static void main(String[] args) {
		SpringApplication.run(BackendTechAssessmentApplication.class, args);
	}

	// Populate the storage with some false default data for testing.
	// GET localhost:8080/projects
	// GET localhost:8080/projects/3
	@Override
	public void run(String... strings) throws Exception {
		projectService.createProject(1, "Project 1", 10000, new Date());
		projectService.createProject(2, "Project 2", 20000, new Date());
		projectService.createProject(3, "Project 3", 30000, new Date());
		projectService.createProject(4, "Project 4", 40000, new Date());
		projectService.createProject(5, "Project 5", 50000, new Date());
		projectService.lookup().forEach(project -> System.out.println(project));
	}
}

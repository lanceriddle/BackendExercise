package com.intuit.cg.backendtechassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
	Major Classes:

		BackendTechAssessmentApplication.java - Main SpringBootApplication class.
		BackendTechAssessmentController.java - Controller that handles the REST endpoint commands.
		ProjectBoard.java - Maintains a HashMap of projects that have been posted by sellers and bid on by buyers.


	Domain Model Description:

		Actor: "Seller"
		Member Variables:
			+id
			+projectIds (List of IDs of Projects this Seller has created.)
		Actions:
			1. Post a Project.
			2. Retrieve a Project by ID so it can determine the lowest Bid.

		Actor: "Buyer"
		Member Variables:
			+id
		Actions:
			1. Get a list of available Projects.
			2. Place a Bid on one or multiple Projects.

		Object: "Project" (Instances of these objects will be persisted in the "ProjectRepository".)
		Member Variables:
			+id
			+description
			+maxBudget
			+deadlineForBids
			+lowestBid

		Object: "Bid"
		Member Variables:
			+buyerId (Which buyer to link back to if this Bid is the winner)
			+projectId (Which Project to apply this Bid to)
			+amount

		Object: "ProjectBoard" (The in-memory storage of Projects that have been posted by Sellers.)
		Member Variables:
			+instance
            +projectHashMap
            +nextAvailableId
        Actions:
            +getNewUniqueId()
            +getAllProjectsAsList()
            +getAllProjectsAsString()
            +postProject()
            +getProjectById()
            +bidOnProjectById()


	Technologies:
		- Attempted to use Spring Data JPA (Java Persistent API) in order to take advantage of its built-in
				data storage and access features.
			- This was my first attempt learning this repository tool.
			- Based on time constraints, I defaulted back to an in-memory singleton ProjectBoard where jobs are
				held in a HashMap.
		- ProjectRepository was created and extended the CrudRepository because that provides the interface for many
				Save/Find/Delete/etc methods by default.
 */

// Implement CommandLineRunner so we can pre-populate the data quickly in the run() method.
@SpringBootApplication
public class BackendTechAssessmentApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(BackendTechAssessmentApplication.class, args);
	}







	//@Autowired
	//private ProjectService projectService;

	/*
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
	*/
}

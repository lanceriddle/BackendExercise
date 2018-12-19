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
            +postNewProject()
            +getProjectById()
            +bidOnProjectById()
 */

@SpringBootApplication
public class BackendTechAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTechAssessmentApplication.class, args);
	}
}

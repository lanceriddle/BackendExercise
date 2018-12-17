package com.intuit.cg.backendtechassessment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendTechAssessmentApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void addProject() {
		// 1. Seller adds new project.
		// 2. Buyer Retrieves project list and confirms new project is included.
	}

	@Test
	public void bidOnProject() {
		// 1. Seller adds new project.
		// 2. Buyer bids on project.
		// 3. Retrieve project by ID and confirm buyer's bid is shown as lowest bid.
	}

	@Test
	public void lowerBidOnProject() {
		// 1. Seller adds new project.
		// 2. Buyer 1 bids on project.
		// 3. Buyer 2 bids a lower amount on project.
		// 3. Retrieve project by ID and confirm Buyer 2's bid is shown as lowest bid.
	}

	@Test
	public void higherBidOnProject() {
		// 1. Seller adds new project.
		// 2. Buyer 1 bids on project.
		// 3. Buyer 2 bids a higher amount on project.
		// 3. Retrieve project by ID and confirm Buyer 1's bid is shown as lowest bid.
	}

	@Test
	public void bidOverMaxOnProject() {
		// 1. Seller adds new project.
		// 2. Buyer bids on project with an amount greater than the defined maximum budget.
		// 3. Retrieve project by ID and confirm Buyer 1's bid is NOT shown as lowest bid.
	}

}

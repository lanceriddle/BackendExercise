package com.intuit.cg.backendtechassessment;

import com.intuit.cg.backendtechassessment.controller.BackendTechAssessmentController;
import com.intuit.cg.backendtechassessment.marketplace.model.Bid;
import com.intuit.cg.backendtechassessment.marketplace.model.Project;
import com.intuit.cg.backendtechassessment.marketplace.model.ProjectBoard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
@SpringBootTest
public class ProjectBoardTest {

	/**
	 * Reinitialize the ProjectBoard before each test.
	 */
	@Before
	public void resetProjectBoard() {
		ProjectBoard.resetProjectBoardInstance();;
	}

	/**
	 * 1. Seller adds new project.
	 * 2. Buyer Retrieves project list and confirms new project is included.
	 */
	@Test
	public void postNewProject() {

		try {
			// 1. Seller adds new project.
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BackendTechAssessmentController.DATE_PATTERN);
			Project postedProject = new Project(ProjectBoard.getInstance().getNewUniqueId(),
					1, "Project 1", 10000, simpleDateFormat.parse("2019-01-01"));
			ProjectBoard.getInstance().postNewProject(postedProject);

			// 2. Seller Retrieves project list and confirms new project is included.
			Project retrievedProject = ProjectBoard.getInstance().getProjectById(1);

			assertNotNull(retrievedProject);
			assertEquals((Integer)1, retrievedProject.getId());
			assertEquals((Integer)1, retrievedProject.getSellerId());
			assertEquals("Project 1", retrievedProject.getDescription());
			assertEquals((Integer)10000, retrievedProject.getMaxBudget());
			assertEquals(simpleDateFormat.parse("2019-01-01"), retrievedProject.getDeadlineForBids());

		} catch (ParseException e) {
		}
	}

	/**
	 * 1. Seller adds new project.
	 * 2. Buyer bids on project.
	 * 3. Retrieve project by ID and confirm buyer's bid is shown as lowest bid.
	 */
	@Test
	public void bidOnProjectById_SingleBid() {

		try {
			// 1. Seller adds new project.
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BackendTechAssessmentController.DATE_PATTERN);
			Project project = new Project(ProjectBoard.getInstance().getNewUniqueId(),
					1, "Project 1", 10000, simpleDateFormat.parse("2019-01-01"));
			ProjectBoard.getInstance().postNewProject(project);

			// 2. Buyer bids on project.
			ProjectBoard.getInstance().bidOnProjectById(1, new Bid(1,1, 5000));

			// 3. Retrieve project by ID and confirm buyer's bid is shown as lowest bid.
			Project retrievedProject = ProjectBoard.getInstance().getProjectById(1);

			assertNotNull(retrievedProject);
			assertEquals((Integer)1, retrievedProject.getId());
			assertEquals((Integer)1, retrievedProject.getSellerId());
			assertEquals("Project 1", retrievedProject.getDescription());
			assertEquals((Integer)10000, retrievedProject.getMaxBudget());
			assertEquals(simpleDateFormat.parse("2019-01-01"), retrievedProject.getDeadlineForBids());
			assertEquals((Integer)5000, retrievedProject.getLowestBid().getAmount());

		} catch (ParseException e) {
		}
	}

	/**
	 * 1. Seller adds new project.
	 * 2. Buyer 1 bids on project.
	 * 3. Buyer 2 bids a lower amount on project.
	 * 4. Retrieve project by ID and confirm Buyer 2's bid is shown as lowest bid.
	 */
	@Test
	public void bidOnProjectById_CompetingLowerBid() {
	}

	/**
	 * 1. Seller adds new project.
	 * 2. Buyer 1 bids on project.
	 * 3. Buyer 2 bids a higher amount on project.
	 * 4. Retrieve project by ID and confirm Buyer 1's bid is shown as lowest bid.
	 */
	@Test
	public void bidOnProjectById_CompetingHigherBid() {
	}

	/**
	 * 1. Seller adds new project.
	 * 2. Buyer bids on project with an amount greater than the defined maximum budget.
	 * 3. Retrieve project by ID and confirm Buyer 1's bid is NOT shown as lowest bid.
	 */
	@Test
	public void bidOnProjectById_BidOverMaximum() {
	}

}

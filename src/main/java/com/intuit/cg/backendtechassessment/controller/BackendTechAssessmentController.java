package com.intuit.cg.backendtechassessment.controller;

import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;
import com.intuit.cg.backendtechassessment.marketplace.model.Bid;
import com.intuit.cg.backendtechassessment.marketplace.model.Project;
import com.intuit.cg.backendtechassessment.marketplace.model.ProjectBoard;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
final public class BackendTechAssessmentController {
    public static String DATE_PATTERN = "yyyy-MM-dd";


    /**
     * /projects endpoint.
     * Return a list of projects.
     */
    @RequestMapping(value = RequestMappings.PROJECTS, method = RequestMethod.GET) // "/projects"
    public @ResponseBody String projects() {
        return ProjectBoard.getInstance().getAllProjectsAsString();
    }

    /**
     * /sellers endpoint.  Using POST to CREATE a Project
     * Allow a seller to post a project.
     *
     * @param sellerId  The unique ID of the Seller.
     * @param description  The description of the project.
     * @param maxBudget  The maximum budget of the project.
     * @param deadline  The deadline for placing bids.
     * @return JSON-style list of the currently existing projects.
     */
    @RequestMapping(value = RequestMappings.SELLERS, method = RequestMethod.POST) // "/sellers"
    public @ResponseBody String sellers(@RequestParam(value="sellerId") Integer sellerId,
                                        @RequestParam(value="description") String description,
                                        @RequestParam(value="maxBudget") Integer maxBudget,
                                        @RequestParam(value="deadline") String deadline) {
        Integer projectId;
        try {
            // Create a new Project instance and post it to the ProjectBoard.
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            Project project = new Project(ProjectBoard.getInstance().getNewUniqueId(),
                    sellerId, description, maxBudget, simpleDateFormat.parse(deadline));
            ProjectBoard.getInstance().postNewProject(project);
            projectId = project.getId();
        } catch (ParseException e) {
            e.printStackTrace();
            return "SELLERS ParseException";
        }

        // Return a list of the current Projects.
        return ProjectBoard.getInstance().getProjectById(projectId).toString();
    }

    /**
     * /bids endpoint.
     * Place a bid on the specified project.  Using PUT to UPDATE that bids on a Project.
     *
     * @param buyerId  The unique ID of the Buyer (Bidder).
     * @param projectId  The ID of the project to place a bid on.
     * @param bidValue  The value of the bid.
     * @return JSON-style description of the project that was bid on.
     */
    @RequestMapping(value = RequestMappings.BIDS, method = RequestMethod.PUT) // "/bids"
    public @ResponseBody String bids(@RequestParam(value="buyerId") Integer buyerId,
                                     @RequestParam(value="projectId") Integer projectId,
                                     @RequestParam(value="bidValue") Integer bidValue) {
        // Create a new Bid and post it to a Project on the ProjectBoard.
        Bid bid = new Bid(buyerId, projectId, bidValue);
        Project project = ProjectBoard.getInstance().bidOnProjectById(projectId, bid);

        // Return the related Project to verify its current Bid.
        return project.toString();
    }

    /**
     * TODO: /buyers endpoint.
      */
    @RequestMapping(RequestMappings.BUYERS)
    public @ResponseBody String buyers() {
        return "BUYERS";
    }
}

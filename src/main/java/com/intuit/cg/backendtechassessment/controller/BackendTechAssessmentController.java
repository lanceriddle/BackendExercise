package com.intuit.cg.backendtechassessment.controller;

import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;
import com.intuit.cg.backendtechassessment.marketplace.model.Bid;
import com.intuit.cg.backendtechassessment.marketplace.model.Project;
import com.intuit.cg.backendtechassessment.marketplace.model.ProjectBoard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Sample commands:
 *
 * localhost:8080/sellers?sellerId=1&description=Project%201&maxBudget=10000&deadline=2018-12-31
 *
 * localhost:8080/projects
 *
 * localhost:8080/bids?buyerId=1&projectId=1&bidValue=1000
 */


@RestController
final public class BackendTechAssessmentController {
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Return a list of project using the /projects endpoint.
     */
    @RequestMapping(RequestMappings.PROJECTS)
    public @ResponseBody String projects() {
        return "Projects:\n" + ProjectBoard.getInstance().getAllProjectsAsString();
    }

    /**
     * Allow a seller to post a project using the /sellers endpoint.
     */
    @RequestMapping(RequestMappings.SELLERS)
    public @ResponseBody String sellers(@RequestParam(value="sellerId") Integer sellerId,
                                        @RequestParam(value="description") String description,
                                        @RequestParam(value="maxBudget") Integer maxBudget,
                                        @RequestParam(value="deadline") String deadline) {
        try {
            // Create a new Project instance and post it to the ProjectBoard.
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            Project project = new Project(ProjectBoard.getInstance().getNewUniqueId(),
                    sellerId, description, maxBudget, simpleDateFormat.parse(deadline));
            ProjectBoard.getInstance().postProject(project);
        } catch (ParseException e) {
            e.printStackTrace();
            return "SELLERS ParseException";
        }

        // Return a list of the current Projects.
        return "Existing Projects:\n" + ProjectBoard.getInstance().getAllProjectsAsString();
    }

    /**
     * Place a bid on the specified project using the /bids endpoint.
     */
    @RequestMapping(RequestMappings.BIDS)
    public @ResponseBody String bids(@RequestParam(value="buyerId") Integer buyerId,
                                     @RequestParam(value="projectId") Integer projectId,
                                     @RequestParam(value="bidValue") Integer bidValue) {
        // Create a new Bid instance and post it to a Project on the ProjectBoard.
        Bid bid = new Bid(buyerId, projectId, bidValue);
        Project project = ProjectBoard.getInstance().bidOnProjectById(projectId, bid);

        // Return the related Project to verify its current Bid.
        return "Bid Placed: " + project;
    }


    // TODO: /buyers endpoint.
    @RequestMapping(RequestMappings.BUYERS)
    public @ResponseBody String buyers() {
        return "BUYERS";
    }
}

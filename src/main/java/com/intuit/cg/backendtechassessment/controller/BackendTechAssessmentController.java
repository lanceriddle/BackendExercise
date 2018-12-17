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
     * Return a list of project.
     */
    @RequestMapping(RequestMappings.PROJECTS)
    public @ResponseBody String projects() {
        return "Projects:\n" + ProjectBoard.getInstance().getAllProjectsString();
    }

    /**
     * Allow a seller to post a project.
     */
    @RequestMapping(RequestMappings.SELLERS)
    public @ResponseBody String sellers(@RequestParam(value="sellerId") Integer sellerId,
                                        @RequestParam(value="description") String description,
                                        @RequestParam(value="maxBudget") Integer maxBudget,
                                        @RequestParam(value="deadline") String deadline) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            Project project = new Project(ProjectBoard.getInstance().getNextAvailableId(),
                    description, maxBudget, simpleDateFormat.parse(deadline));
            ProjectBoard.getInstance().postProject(project);
        } catch (ParseException e) {
            e.printStackTrace();
            return "SELLERS ParseException";
        }
        return "Existing Projects:\n" + ProjectBoard.getInstance().getAllProjectsString();
    }

    // TODO
    @RequestMapping(RequestMappings.BUYERS)
    public @ResponseBody String buyers() {
        return "BUYERS";
    }

    /**
     * Place a bid on the specified project.
     */
    @RequestMapping(RequestMappings.BIDS)
    public @ResponseBody String bids(@RequestParam(value="buyerId") Integer buyerId,
                                     @RequestParam(value="projectId") Integer projectId,
                                     @RequestParam(value="bidValue") Integer bidValue) {
        Bid bid = new Bid(buyerId, projectId, bidValue);
        ProjectBoard.getInstance().bidOnProjectById(projectId, bid);
        return "Bid Placed: " + bid;
    }
}

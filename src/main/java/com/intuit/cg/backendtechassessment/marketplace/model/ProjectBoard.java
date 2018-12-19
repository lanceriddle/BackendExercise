package com.intuit.cg.backendtechassessment.marketplace.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The collection of currently existing projects that are
 * available for bidding on.
 *
 * NOTE: Model is separated from the Controller to limit coupling, allowing for abstraction
 * where the storage method can be changed without widespread changes.
 */
public class ProjectBoard {
    private static ProjectBoard instance;

    private HashMap<Integer, Project> projectHashMap = new HashMap<>();
    private Integer nextAvailableProjectId = 1;

    /**
     * Retrieve the Singleton ProjectBoard instance.
     */
    public static ProjectBoard getInstance() {
        if (instance == null)
            instance = new ProjectBoard();
        return instance;
    }

    /**
     * Don't allow the creation of instances by other classes.
     */
    private ProjectBoard() {
    }

    /**
     * Get the next available Project ID for a new Project.
     */
    public synchronized Integer getNewUniqueId() {
        return nextAvailableProjectId++;
    }

    /**
     * Get all posted projects as a list.
     */
    public ArrayList<Project> getAllProjectsAsList() {
        return new ArrayList<Project>(projectHashMap.values());
    }

    /**
     * Return a string with a list of all posted projects in JSON-style.
     */
    public String getAllProjectsAsString() {
        StringBuffer sb = new StringBuffer();
        for (Project proj : projectHashMap.values()) {
            sb.append(proj + "\n");
        }

        return sb.toString();
    }

    /**
     * Add a project to the repository.
     *
     * @param project  Project to add to the ProjectBoard.
     */
    public void postNewProject(Project project) {
        projectHashMap.put(project.getId(), project);
    }

    /**
     * Retrieve a project by its ID.
     *
     * @param id  ID of the project to retrieve.
     * @return the requested Project.
     */
    public Project getProjectById(Integer id) {
        if(!projectHashMap.containsKey(id))
            return null;
        return projectHashMap.get(id);
    }

    /**
     * Let a Buyer bid on a project.
     *
     * @param projectId  ID of the project to bid on.
     * @param bid  Instance of the Bid being placed.
     * @return the Project that was bid on.
     */
    public Project bidOnProjectById(Integer projectId, Bid bid) {
        // NOTE: Could set up a "UnknownProjectException", etc.
        if(!projectHashMap.containsKey(projectId))
            return null;

        // Retrieve the corresponding Project and post a Bid to it.
        Project project = projectHashMap.get(projectId);
        project.postBid(bid);

        return project;
    }

    public static void resetProjectBoardInstance() {
        instance = null;
    }
}

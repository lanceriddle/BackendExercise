package com.intuit.cg.backendtechassessment.marketplace.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The collection of currently existing projects that are
 * available for bidding on.
 */
public class ProjectBoard {
    private static ProjectBoard instance;

    private HashMap<Integer, Project> projectHashMap = new HashMap<>();
    private Integer nextAvailableId = 1;

    /**
     * Retrieve a Singleton ProjectBoard instance.
     */
    public static ProjectBoard getInstance() {
        if (instance == null)
            instance = new ProjectBoard();
        return instance;
    }

    /**
     * Don't allow the creation of instances by other classes.
     */
    protected ProjectBoard() {
    }

    /**
     * Get the next available Project ID for a new Project.
     */
    public synchronized Integer getNewUniqueId() {
        return nextAvailableId++;
    }

    /**
     * Get all posted projects as a list.
     */
    public ArrayList<Project> getAllProjectsAsList() {
        return new ArrayList<Project>(projectHashMap.values());
    }

    /**
     * Get all posted projects as a string.
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
     */
    public void postProject(Project project) {
        projectHashMap.put(project.getId(), project);
    }

    /**
     * Retrieve a project by its ID.
     */
    public Project getProjectById(Integer id) {
        if(!projectHashMap.containsKey(id))
            return null;
        return projectHashMap.get(id);
    }

    /**
     * Let a Buyer bid on a project.
     */
    public Project bidOnProjectById(Integer projectId, Bid bid) {
        // TODO: handle the 'null' case to prevent exceptions.
        if(!projectHashMap.containsKey(projectId))
            return null;

        // Retrieve the corresponding Project and post a Bid to it.
        Project project = projectHashMap.get(projectId);
        project.postBid(bid);

        return project;
    }
}

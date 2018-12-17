package com.intuit.cg.backendtechassessment.marketplace.model;

// A Bid can be placed by a Buyer onto a Project.
// See BackendTechAssessmentApplication.java for a description of the domain model.
public class Bid {

    // The ID of the Buyer who placed it.
    private Integer buyerID;

    // The ID of the target Project to apply to.
    private Integer projectID;

    private Integer amount;

    public Bid(Integer buyerID, Integer projectID, Integer amount) {
        this.buyerID = buyerID;
        this.projectID = projectID;
        this.amount = amount;
    }

    protected Bid() {
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public Integer getAmount() {
        return amount;
    }
}

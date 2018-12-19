package com.intuit.cg.backendtechassessment.marketplace.model;

/**
 * A Bid that can be placed by a Buyer onto a Project.
 */
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

    /**
     * JSON-style formatted string of the Bid.
     */
    public String toString() {
        return "\n        \"Bid\": {\n"
                + "            \"buyerID\": \"" + buyerID + "\",\n"
                + "            \"projectID\": \"" + projectID + "\",\n"
                + "            \"amount\": \"" + amount + "\"\n"
                + "        }";
    }
}

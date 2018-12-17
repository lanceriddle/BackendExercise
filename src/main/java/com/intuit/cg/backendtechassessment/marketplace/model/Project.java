package com.intuit.cg.backendtechassessment.marketplace.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// A Project can be Created by a Seller (Employer).
// A Project can be Bid on by a Buyer (Self-Employed).
// See BackendTechAssessmentApplication.java for a description of the domain model.
//@Entity
public class Project {
    public static String DATE_PATTERN = "yyyy-MM-dd";

    //@Id
    //@Column
    private Integer id;

    private Integer sellerId;

    //@Column(length = 2000)
    private String description;

    //@Column
    private Integer maxBudget;

    //@Column
    private Date deadlineForBids;

    private Bid lowestBid;

    public Project(Integer id, Integer sellerId, String description, Integer maxBudget, Date deadlineForBids) {
        this.id = id;
        this.sellerId = sellerId;
        this.description = description;
        this.maxBudget = maxBudget;
        this.deadlineForBids = deadlineForBids;
    }

    protected Project() {
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxBudget() {
        return maxBudget;
    }

    public Date getDeadlineForBids() {
        return deadlineForBids;
    }

    public Bid getLowestBid() {
        return lowestBid;
    }

    public void postBid(Bid bid) {
        if (bid.getAmount() > maxBudget)
            return;

        if(lowestBid == null || lowestBid.getAmount() > bid.getAmount()) {
            lowestBid = bid;
        }
    }

    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return "Project:{"
                + "id=" + id
                + ", description=" + description
                + ", maxBudget=" + maxBudget
                + ", deadlineForBids=" + simpleDateFormat.format(deadlineForBids)
                + (lowestBid!=null ? (", lowestBid=" + lowestBid) : "")
                + "}";
    }
}

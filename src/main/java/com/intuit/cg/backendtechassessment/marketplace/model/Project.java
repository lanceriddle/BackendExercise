package com.intuit.cg.backendtechassessment.marketplace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

// A Project can be Created by a Seller (Employer).
// A Project can be Bid on by a Buyer (Self-Employed).
// See BackendTechAssessmentApplication.java for a description of the domain model.
@Entity
public class Project implements Serializable {
    @Id
    @Column
    private Integer id;

    @Column(length = 2000)
    private String description;

    @Column
    private Integer maxBudget;

    @Column
    private Date deadlineForBids;

    //private Bid lowestBid;

    public Project(Integer id, String description, Integer maxBudget, Date deadlineForBids) {
        this.id = id;
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

    //public Bid getLowestBid() {
    //    return lowestBid;
    //}

    //public void postBid(Bid bid) {
    //    if(lowestBid == null || lowestBid.getAmount() > bid.getAmount()) {
    //        lowestBid = bid;
    //    }
    //}

    @Override
    public String toString() {
        return "Project{"
                + "id=" + id
                + ", description=" + description
                + ", maxBudget=" + maxBudget
                + ", deadlineForBids=" + deadlineForBids
                + "}";
    }
}

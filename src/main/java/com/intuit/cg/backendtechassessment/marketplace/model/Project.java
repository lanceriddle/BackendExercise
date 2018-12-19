package com.intuit.cg.backendtechassessment.marketplace.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Project can be Created by a Seller (Employer).
 * A Project can be Bid on by a Buyer (Self-Employed).
  */
public class Project {
    public static String DATE_PATTERN = "yyyy-MM-dd";

    private Integer id;
    private Integer sellerId;
    private String description;
    private Integer maxBudget;
    private Date deadlineForBids;
    private Bid lowestBid;

    public Project(Integer id, Integer sellerId, String description, Integer maxBudget, Date deadlineForBids) {
        this.id = id;
        this.sellerId = sellerId;
        this.description = description;
        this.maxBudget = maxBudget;
        this.deadlineForBids = deadlineForBids;
    }

    // NOTE: Don't allow creating empty Project instances.
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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * Post a bid to this project.
     * NOTE: Package-private access.
     *
     * @param bid  The Bid to be posted to this Project.
     */
    void postBid(Bid bid) {
        if (bid.getAmount() > maxBudget)
            return;

        if(lowestBid == null || lowestBid.getAmount() > bid.getAmount()) {
            lowestBid = bid;
        }
    }

    /**
     * Return JSON-style formatted string of the Project.
     */
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return "\"Project\": {\n"
                + "    \"id\": \"" + id + "\",\n"
                + "    \"description\": \"" + description + "\",\n"
                + "    \"maxBudget\": \"" + maxBudget + "\",\n"
                + "    \"deadlineForBids\": \"" + simpleDateFormat.format(deadlineForBids) + "\""
                + (lowestBid!=null ? ("\n    \"lowestBid\":" + lowestBid) : "")
                + "\n}";
    }
}

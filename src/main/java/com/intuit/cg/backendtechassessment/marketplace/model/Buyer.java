package com.intuit.cg.backendtechassessment.marketplace.model;

// One of two Actors in the Marketplace.
// The Buyer (Self-Employed) can:
//      1. Get a list of the available Projects.
//      2. Post Bids on any available Projects.
// See BackendTechAssessmentApplication.java for a description of the domain model.
public class Buyer {

    private Integer id;

    public Buyer() {
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

}

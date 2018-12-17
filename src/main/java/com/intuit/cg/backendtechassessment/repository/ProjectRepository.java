package com.intuit.cg.backendtechassessment.repository;

import com.intuit.cg.backendtechassessment.marketplace.model.Project;
import org.springframework.data.repository.CrudRepository;

// Storage of Projects during runtime.
// CRUD repository for Marketplace Projects where the Project IDs are Integers.
public interface ProjectRepository extends CrudRepository<Project, Integer> {

}

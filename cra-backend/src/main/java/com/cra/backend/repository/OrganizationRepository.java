package com.cra.backend.repository;

import com.cra.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrganizationRepository — The data access layer for the Organization entity.
 *
 * WHAT IS A REPOSITORY?
 * The repository is the layer that talks directly to the database.
 * Think of it as your "database assistant" — you give it instructions,
 * it handles all the SQL for you.
 *
 * HOW DOES IT WORK?
 * JpaRepository<Organization, Long> is a Spring Data interface.
 * By extending it, you AUTOMATICALLY get these methods for FREE — no code needed:
 *
 *   save(entity)          → INSERT or UPDATE
 *   findById(id)          → SELECT WHERE id = ?    (returns Optional<Organization>)
 *   findAll()             → SELECT * FROM organizations
 *   deleteById(id)        → DELETE WHERE id = ?
 *   count()               → SELECT COUNT(*)
 *   existsById(id)        → SELECT EXISTS(...)
 *   ... and many more
 *
 * Spring generates the actual implementation at startup using dynamic proxies.
 * You never write the implementation — Spring Data does it for you.
 *
 * TYPE PARAMETERS:
 *   Organization → the entity this repository manages
 *   Long         → the type of the primary key (id field in BaseEntity is Long)
 *
 * @Repository:
 * Marks this interface as a Spring component (Spring will find and manage it).
 * Also enables Spring to translate database exceptions into Spring's DataAccessException.
 * Technically optional when extending JpaRepository (Spring auto-detects it),
 * but good practice to keep for clarity.
 *
 * CUSTOM QUERIES (add them as you need):
 * Spring Data can auto-generate queries from method NAMES alone:
 *   findByName(String name)          → SELECT * WHERE name = ?
 *   findByIndustry(String industry)  → SELECT * WHERE industry = ?
 *   findByStatus(String status)      → SELECT * WHERE status = ?
 *   findByNameAndStatus(...)         → SELECT * WHERE name = ? AND status = ?
 *
 * For complex queries, you can add @Query("SELECT o FROM Organization o WHERE ...")
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // No custom methods needed yet.
    // The methods inherited from JpaRepository are enough for our basic use case.
    // Add custom finder methods here as requirements grow.
}

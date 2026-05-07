package com.cra.backend.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Organization — A JPA Entity representing the "organizations" table in MySQL.
 *
 * WHAT IS AN ENTITY?
 * An entity is a Java class that maps directly to a database table.
 * Each instance of this class = one row in the organizations table.
 * Each field = one column.
 *
 * @Entity      → Tells JPA: "This class represents a database table."
 * @Table       → Specifies the exact table name. Good practice to be explicit.
 *               Without it, JPA would default to the class name ("Organization").
 *
 * LOMBOK ANNOTATIONS:
 * @Data            → Generates: getters, setters, equals(), hashCode(), toString()
 * @NoArgsConstructor → Generates: public Organization() {} (required by JPA)
 * @AllArgsConstructor → Generates: constructor with ALL fields as parameters
 * @Builder         → Generates builder pattern: Organization.builder().name("X").build()
 *
 * NOTE: @Data with JPA entities can cause issues with bidirectional relationships
 * later (infinite loops in toString/equals). For now it's fine. We'll refine later.
 *
 * This class extends BaseEntity which provides: id, createdAt, updatedAt.
 * The final table will have ALL columns: id, name, industry, status, created_at, updated_at.
 */
@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization extends BaseEntity {

    /**
     * @Column defines the database column properties:
     * name       → explicit column name in the DB (snake_case convention)
     * nullable   → whether this column allows NULL values
     * length     → VARCHAR size in the DB
     */
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "industry", nullable = false, length = 100)
    private String industry;

    /**
     * Organization status: ACTIVE, INACTIVE, PENDING, etc.
     * Using a String for now — we can convert to @Enumerated later.
     */
    @Column(name = "status", nullable = false, length = 50)
    private String status;
}

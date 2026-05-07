package com.cra.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * BaseEntity — A reusable parent class for ALL your database entities.
 *
 * WHY USE A BASE ENTITY?
 * Every table in your database will need an ID, a created timestamp, and an
 * updated timestamp. Instead of copy-pasting these 3 fields into every entity,
 * you put them here once and extend this class.
 *
 * HOW JPA INHERITANCE WORKS HERE:
 * @MappedSuperclass tells JPA: "This class is NOT a table by itself.
 * But any @Entity that extends it will INHERIT these columns into its own table."
 *
 * So if Organization extends BaseEntity, the organizations table will have:
 *   id, created_at, updated_at, name, industry, status
 *
 * AUDITING:
 * @EntityListeners(AuditingEntityListener.class) activates Spring Data's
 * auditing feature. It automatically sets createdAt and updatedAt timestamps
 * without you writing any code.
 * For this to work, you need @EnableJpaAuditing in a config class (see AppConfig).
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * PRIMARY KEY — every table needs a unique identifier for each row.
     *
     * @Id           → marks this field as the primary key column
     * @GeneratedValue → tells JPA how to generate the ID automatically
     * IDENTITY strategy → database auto-increments the value (MySQL's AUTO_INCREMENT)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * CREATED TIMESTAMP — set once when the record is first inserted.
     *
     * @CreatedDate     → Spring Data sets this automatically on INSERT
     * updatable = false → once set, this column can NEVER be changed by JPA
     * nullable = false  → this column cannot be NULL in the database
     * columnDefinition  → tells the DB to store with full timestamp precision
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false,
            columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    /**
     * UPDATED TIMESTAMP — updated every time the record changes.
     *
     * @LastModifiedDate → Spring Data updates this automatically on every UPDATE
     * nullable = false   → always present
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime updatedAt;
}

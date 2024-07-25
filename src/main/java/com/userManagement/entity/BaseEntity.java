package com.userManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @UuidGenerator
    @Column(name="id")
    private String id;

    @JsonIgnore
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by_id")
    private String createdById;

    @JsonIgnore
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "updated_by_id")
    private String updatedById;

}

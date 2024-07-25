package com.userManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "organization")
public class OrganizationEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

}

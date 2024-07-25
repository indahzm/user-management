package com.userManagement.service;

import com.userManagement.entity.OrganizationEntity;

import java.util.List;

public interface OrganizationService {
    List<OrganizationEntity> findAll();
    OrganizationEntity findById(String id);
    OrganizationEntity save(OrganizationEntity organization);
    void deleteById(String id);

}

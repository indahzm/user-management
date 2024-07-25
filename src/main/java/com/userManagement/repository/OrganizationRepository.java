package com.userManagement.repository;

import com.userManagement.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {
    Optional<OrganizationEntity> findById(String id);
}

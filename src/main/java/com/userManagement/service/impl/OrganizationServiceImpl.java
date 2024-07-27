package com.userManagement.service.impl;

import com.userManagement.entity.OrganizationEntity;
import com.userManagement.entity.Role;
import com.userManagement.entity.UserEntity;
import com.userManagement.repository.OrganizationRepository;
import com.userManagement.repository.UserRepository;
import com.userManagement.service.BaseService;
import com.userManagement.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrganizationServiceImpl extends BaseService implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<OrganizationEntity> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public OrganizationEntity findById(String id) {
        Optional<OrganizationEntity> organizationOpt = organizationRepository.findById(id);
        return organizationOpt.isPresent() ? organizationOpt.get() : organizationOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization with the id not found"));
    }

    @Override
    public OrganizationEntity save(OrganizationEntity organization) {
        UserEntity userLogin = getUserLogin();
        if(organization.getId() != null) {
            if(!isAllow(organization.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to edit this organization");
            }
            OrganizationEntity oldUser = findById(organization.getId());
            organization.setCreatedAt(oldUser.getCreatedAt());
            organization.setCreatedById(oldUser.getCreatedById());
        } else {
            organization.setId(UUID.randomUUID().toString());
            organization.setCreatedAt(new Date());
            organization.setCreatedById(userLogin.getId());
        }
        organization.setUpdatedAt(new Date());
        organization.setUpdatedById(userLogin.getId());
        return organizationRepository.save(organization);
    }

    @Override
    public void deleteById(String id) {
        if(!isAllow(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to delete this organization");
        }
        organizationRepository.deleteById(id);
    }

    public Boolean isAllow(String id) {
        UserEntity user = getUserLogin();
        if(user.getRole().name().equalsIgnoreCase(Role.SUPER_ADMIN.toString())) {
            return true;
        } else {
            OrganizationEntity organization = findById(id);
            return organization.getCreatedById().equalsIgnoreCase(user.getId());
        }
    }
}

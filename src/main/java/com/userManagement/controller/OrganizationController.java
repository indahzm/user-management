package com.userManagement.controller;

import com.userManagement.entity.OrganizationEntity;
import com.userManagement.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationEntity>> getAllOrganization() {
        List<OrganizationEntity> organizationList = organizationService.findAll();
        return ResponseEntity.ok(organizationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationEntity> getOrganization(@PathVariable(required = true) String id) {
        OrganizationEntity organization = organizationService.findById(id);
        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<OrganizationEntity> createOrganization(@RequestBody OrganizationEntity organization) {
        return ResponseEntity.ok(organizationService.save(organization));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<OrganizationEntity> updateOrganization(@PathVariable(required = true) String id, @RequestBody OrganizationEntity organization) {
        organization.setId(id);
        return ResponseEntity.ok(organizationService.save(organization));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable(required = true) String id) {
        organizationService.deleteById(id);
        return ResponseEntity.ok("Organization is deleted successfully");
    }
}

package com.jobportal.job_portal_backend.model;

import java.util.Set;

public enum Role {

    EMPLOYER(Set.of(Permissions.CREATE_JOBPOST, Permissions.READ_JOBPOST, Permissions.UPDATE_JOBPOST, Permissions.DELETE_JOBPOST, Permissions.VIEW_APPLICATIONS)),
    CANDIDATE(Set.of(Permissions.READ_JOBPOST, Permissions.APPLY_JOB));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions){
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions(){
        return permissions;
    }

}

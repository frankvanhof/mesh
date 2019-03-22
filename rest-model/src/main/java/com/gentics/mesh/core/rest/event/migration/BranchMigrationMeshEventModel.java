package com.gentics.mesh.core.rest.event.migration;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshProjectElementEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;

public class BranchMigrationMeshEventModel implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final MigrationMeshEventModelProperties baseProperties;

	public BranchMigrationMeshEventModel(MigrationMeshEventModelProperties baseProperties) {
		this.baseProperties = baseProperties;
	}

	@Override
	public String getOrigin() {
		return baseProperties.getOrigin();
	}

	@Override
	public MeshEvent getEvent() {
		return baseProperties.getEvent();
	}

	@Override
	public EventCauseInfo getCause() {
		return baseProperties.getCause();
	}

	@Override
	public void setCause(EventCauseInfo cause) {
		baseProperties.setCause(cause);
	}

	@Override
	public String getUuid() {
		return baseProperties.getUuid();
	}

	@Override
	public void setUuid(String uuid) {
		baseProperties.setUuid(uuid);
	}

	@Override
	public String getName() {
		return baseProperties.getName();
	}

	@Override
	public void setName(String name) {
		baseProperties.setName(name);
	}

	@Override
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
	}

	public BranchReference getBranch() {
		return baseProperties.getBranch();
	}

	public void setBranch(BranchReference branch) {
		baseProperties.setBranch(branch);
	}

	public MigrationStatus getStatus() {
		return baseProperties.getStatus();
	}

	public void setStatus(MigrationStatus status) {
		baseProperties.setStatus(status);
	}
}

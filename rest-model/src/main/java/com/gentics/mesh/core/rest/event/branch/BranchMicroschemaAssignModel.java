package com.gentics.mesh.core.rest.event.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.MicroschemaReference;

public class BranchMicroschemaAssignModel implements ProjectEvent {

	@JsonUnwrapped
	private BranchAssignEventProperties<MicroschemaReference> baseProperties;

	public BranchMicroschemaAssignModel(BranchAssignEventProperties<MicroschemaReference> baseProperties) {
		this.baseProperties = baseProperties;
	}

	@Override
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {

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


	@JsonIgnore
	@Override
	public void setCause(EventCauseInfo cause) {
		baseProperties.setCause(cause);
	}

	public BranchReference getBranch() {
		return baseProperties.getBranch();
	}

	public MicroschemaReference getSchema() {
		return baseProperties.getSchema();
	}

	public MigrationStatus getStatus() {
		return baseProperties.getStatus();
	}

	public BranchAssignEventProperties<MicroschemaReference> getBaseProperties() {
		return baseProperties;
	}

	public BranchMicroschemaAssignModel setBaseProperties(BranchAssignEventProperties<MicroschemaReference> baseProperties) {
		this.baseProperties = baseProperties;
		return this;
	}

}

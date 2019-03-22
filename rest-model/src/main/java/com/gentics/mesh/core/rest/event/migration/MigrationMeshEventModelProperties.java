package com.gentics.mesh.core.rest.event.migration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshProjectElementEventModel;
import com.gentics.mesh.core.rest.event.ProjectElementEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;

public class MigrationMeshEventModelProperties implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final ProjectElementEventModel baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Branch to which the migration applies.")
	private BranchReference branch;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Status of the migration at the time when the event was send.")
	private MigrationStatus status;

	public MigrationMeshEventModelProperties(ProjectElementEventModel baseProperties, BranchReference branch, MigrationStatus status) {
		this.baseProperties = baseProperties;
		this.branch = branch;
		this.status = status;
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

	/**
	 * Return the referenced branch.
	 * 
	 * @return
	 */
	public BranchReference getBranch() {
		return branch;
	}

	/**
	 * Set the referenced branch.
	 * 
	 * @param branch
	 */
	public void setBranch(BranchReference branch) {
		this.branch = branch;
	}

	/**
	 * Return the current migration status.
	 * 
	 * @return
	 */
	public MigrationStatus getStatus() {
		return status;
	}

	/**
	 * Set the migration status.
	 * 
	 * @param status
	 */
	public void setStatus(MigrationStatus status) {
		this.status = status;
	}

}

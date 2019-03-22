package com.gentics.mesh.core.rest.event.branch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.common.NameUuidReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectEventModelProperties;
import com.gentics.mesh.core.rest.project.ProjectReference;

public final class BranchAssignEventProperties<T extends NameUuidReference<T>> {

	@JsonUnwrapped
	private ProjectEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the branch.")
	private BranchReference branch;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the schema that was assigned.")
	private T schema;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Status of the migration job that has been created when assigning the schema.")
	private MigrationStatus status;

	public BranchAssignEventProperties() {
	}

	public BranchAssignEventProperties(ProjectEventModelProperties baseProperties, BranchReference branch, T schema, MigrationStatus status) {
		this.baseProperties = baseProperties;
		this.branch = branch;
		this.schema = schema;
		this.status = status;
	}

	public BranchReference getBranch() {
		return branch;
	}

	public T getSchema() {
		return schema;
	}

	public MigrationStatus getStatus() {
		return status;
	}

	public String getOrigin() {
		return baseProperties.getOrigin();
	}

	public MeshEvent getEvent() {
		return baseProperties.getEvent();
	}

	public EventCauseInfo getCause() {
		return baseProperties.getCause();
	}

	public void setCause(EventCauseInfo cause) {
		baseProperties.setCause(cause);
	}

	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	public BranchAssignEventProperties<T> setBaseProperties(ProjectEventModelProperties baseProperties) {
		this.baseProperties = baseProperties;
		return this;
	}

	public BranchAssignEventProperties<T> setBranch(BranchReference branch) {
		this.branch = branch;
		return this;
	}

	public BranchAssignEventProperties<T> setSchema(T schema) {
		this.schema = schema;
		return this;
	}

	public BranchAssignEventProperties<T> setStatus(MigrationStatus status) {
		this.status = status;
		return this;
	}
}

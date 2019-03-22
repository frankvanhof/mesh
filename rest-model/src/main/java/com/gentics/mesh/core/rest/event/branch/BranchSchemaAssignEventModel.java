package com.gentics.mesh.core.rest.event.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.SchemaReference;

import java.util.Objects;

public class BranchSchemaAssignEventModel implements ProjectEvent {

	@JsonUnwrapped
	private BranchAssignEventProperties<SchemaReference> baseProperties;

	public BranchSchemaAssignEventModel() {
	}

	public BranchSchemaAssignEventModel(BranchAssignEventProperties<SchemaReference> baseProperties) {
		setBaseProperties(baseProperties);
	}

	@Override
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@JsonIgnore
	@Override
	public void setProject(ProjectReference project) {
		System.out.println(project);
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

	public BranchReference getBranch() {
		return baseProperties.getBranch();
	}

	public SchemaReference getSchema() {
		return baseProperties.getSchema();
	}

	public MigrationStatus getStatus() {
		return baseProperties.getStatus();
	}

	public BranchSchemaAssignEventModel setBaseProperties(BranchAssignEventProperties<SchemaReference> baseProperties) {
		this.baseProperties = Objects.requireNonNull(baseProperties);
		return this;
	}
}

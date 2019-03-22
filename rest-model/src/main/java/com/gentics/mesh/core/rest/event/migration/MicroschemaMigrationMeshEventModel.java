package com.gentics.mesh.core.rest.event.migration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.admin.migration.MigrationStatus;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshProjectElementEventModel;
import com.gentics.mesh.core.rest.event.ProjectElementEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.MicroschemaReference;
import com.gentics.mesh.core.rest.schema.impl.MicroschemaReferenceImpl;

public class MicroschemaMigrationMeshEventModel implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final MigrationMeshEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the source microschema version.")
	@JsonDeserialize(as = MicroschemaReferenceImpl.class)
	private MicroschemaReference fromVersion;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the target microschema version.")
	@JsonDeserialize(as = MicroschemaReferenceImpl.class)
	private MicroschemaReference toVersion;

	public MicroschemaMigrationMeshEventModel(MigrationMeshEventModelProperties baseProperties, MicroschemaReference fromVersion, MicroschemaReference toVersion) {
		this.baseProperties = baseProperties;
		this.fromVersion = fromVersion;
		this.toVersion = toVersion;
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

	public MicroschemaReference getFromVersion() {
		return fromVersion;
	}

	public void setFromVersion(MicroschemaReference fromVersion) {
		this.fromVersion = fromVersion;
	}

	public MicroschemaReference getToVersion() {
		return toVersion;
	}

	public void setToVersion(MicroschemaReference toVersion) {
		this.toVersion = toVersion;
	}
}

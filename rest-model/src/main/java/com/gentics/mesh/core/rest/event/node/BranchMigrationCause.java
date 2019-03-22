package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.ElementType;
import com.gentics.mesh.core.rest.event.EventCauseAction;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.project.ProjectReference;

public class BranchMigrationCause implements EventCauseInfo {

	@JsonProperty(required = true)
	@JsonPropertyDescription("The uuid of the job that caused the migration.")
	private final String uuid;

	@JsonProperty(required = true)
	@JsonPropertyDescription("The project of the branch.")
	private final ProjectReference project;

	public BranchMigrationCause(String uuid, ProjectReference project) {
		this.uuid = uuid;
		this.project = project;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	public ProjectReference getProject() {
		return project;
	}

	@Override
	public ElementType getType() {
		return ElementType.JOB;
	}

	@Override
	public EventCauseAction getAction() {
		return EventCauseAction.BRANCH_MIGRATION;
	}
}

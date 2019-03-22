package com.gentics.mesh.core.rest.event.branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.event.ProjectEventModelProperties;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.tag.TagReference;

public class BranchTaggedEventModel implements ProjectEvent {

	@JsonUnwrapped
	private final ProjectEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the branch.")
	private final BranchReference branch;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the tag.")
	private final TagReference tag;

	public BranchTaggedEventModel(ProjectEventModelProperties baseProperties, BranchReference branch, TagReference tag) {
		this.baseProperties = baseProperties;
		this.branch = branch;
		this.tag = tag;
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
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
	}

	public BranchReference getBranch() {
		return branch;
	}

	public TagReference getTag() {
		return tag;
	}

}

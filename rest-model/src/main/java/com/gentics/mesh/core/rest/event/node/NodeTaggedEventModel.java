package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.ProjectElementEventModel;
import com.gentics.mesh.core.rest.event.ProjectEvent;
import com.gentics.mesh.core.rest.event.ProjectEventModelProperties;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.tag.TagReference;
import com.gentics.mesh.core.rest.user.NodeReference;

public class NodeTaggedEventModel implements ProjectEvent {

	@JsonUnwrapped
	private final ProjectEventModelProperties baseProperties;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference of the tag.")
	private TagReference tag;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Branch for which the tagging operation was executed.")
	private BranchReference branch;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Reference to the node that was tagged.")
	private NodeReference node;

	public NodeTaggedEventModel(ProjectEventModelProperties baseProperties, TagReference tag, BranchReference branch, NodeReference node) {
		this.baseProperties = baseProperties;
		this.tag = tag;
		this.branch = branch;
		this.node = node;
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

	public TagReference getTag() {
		return tag;
	}

	public void setTag(TagReference tag) {
		this.tag = tag;
	}

	public BranchReference getBranch() {
		return branch;
	}

	public void setBranch(BranchReference branch) {
		this.branch = branch;
	}

	public NodeReference getNode() {
		return node;
	}

	public void setNode(NodeReference node) {
		this.node = node;
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
	}

}

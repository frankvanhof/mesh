package com.gentics.mesh.core.rest.event.tagfamily;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshProjectElementEventModel;
import com.gentics.mesh.core.rest.event.ProjectElementEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;

public class TagFamilyMeshEventModel implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final ProjectElementEventModel baseProperties;

	public TagFamilyMeshEventModel(ProjectElementEventModel baseProperties) {
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
	public String getName() {
		return baseProperties.getName();
	}

	@Override
	public void setName(String name) {
		baseProperties.setName(name);
	}

	@Override
	public void setUuid(String uuid) {
		baseProperties.setUuid(uuid);
	}

	@Override
	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
	}
}

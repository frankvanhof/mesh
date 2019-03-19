package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshElementEventModel;
import com.gentics.mesh.core.rest.event.MeshEventModel;
import com.gentics.mesh.core.rest.event.MeshProjectElementEventModel;
import com.gentics.mesh.core.rest.event.ProjectElementEventModel;
import com.gentics.mesh.core.rest.event.SimpleElementEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.SchemaReference;
import com.gentics.mesh.core.rest.schema.impl.SchemaReferenceImpl;

import java.util.Objects;

public class NodeMeshEventModel implements MeshProjectElementEventModel {

	@JsonUnwrapped
	private final ProjectElementEventModel baseProperties;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Type of the content (e.g. draft/published)")
	private String type;

	@JsonProperty(required = true)
	@JsonPropertyDescription("Branch uuid to which the node belongs.")
	private final String branchUuid;

	@JsonProperty(required = false)
	@JsonPropertyDescription("ISO 639-1 language tag of the node content.")
	private String languageTag;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Schema reference of the node.")
	@JsonDeserialize(as = SchemaReferenceImpl.class)
	private SchemaReference schema;

	public NodeMeshEventModel(ProjectElementEventModel baseProperties, String branchUuid) {
		this.baseProperties = baseProperties;
		this.branchUuid = Objects.requireNonNull(branchUuid);
	}

	/**
	 * Type of the node that has been deleted (e.g. published or draft)
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchUuid() {
		return branchUuid;
	}

	/**
	 * Return the specific language tag that has been deleted from the node.
	 * 
	 * @return
	 */
	public String getLanguageTag() {
		return languageTag;
	}

	public void setLanguageTag(String languageTag) {
		this.languageTag = languageTag;
	}

	public SchemaReference getSchema() {
		return schema;
	}

	public void setSchema(SchemaReference schema) {
		this.schema = schema;
	}

	public ProjectReference getProject() {
		return baseProperties.getProject();
	}

	@Override
	public void setProject(ProjectReference project) {
		baseProperties.setProject(project);
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

}

package com.gentics.mesh.core.rest.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class MeshElementEventProperties {
	@JsonProperty(required = true)
	@JsonPropertyDescription("Uuid of the referenced element.")
	private String uuid;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Name of the referenced element.")
	private String name;

	public MeshElementEventProperties(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}

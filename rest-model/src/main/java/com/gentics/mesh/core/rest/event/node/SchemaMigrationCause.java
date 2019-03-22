package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.ElementType;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseAction;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.migration.SchemaMigrationMeshEventModel;
import com.gentics.mesh.core.rest.project.ProjectReference;
import com.gentics.mesh.core.rest.schema.SchemaReference;

public class SchemaMigrationCause implements EventCauseInfo {

	@JsonUnwrapped
	private final SchemaMigrationMeshEventModel causeEvent;

	public SchemaMigrationCause(SchemaMigrationMeshEventModel causeEvent) {
		this.causeEvent = causeEvent;
	}

	@Override
	public ElementType getType() {
		return ElementType.JOB;
	}

	@Override
	public String getUuid() {
		return causeEvent.getUuid();
	}

	@Override
	public EventCauseAction getAction() {
		return EventCauseAction.SCHEMA_MIGRATION;
	}

	public SchemaMigrationMeshEventModel getCauseEvent() {
		return causeEvent;
	}
}

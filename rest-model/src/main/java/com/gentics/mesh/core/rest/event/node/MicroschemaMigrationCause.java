package com.gentics.mesh.core.rest.event.node;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.ElementType;
import com.gentics.mesh.core.rest.branch.BranchReference;
import com.gentics.mesh.core.rest.event.EventCauseAction;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.migration.MicroschemaMigrationMeshEventModel;
import com.gentics.mesh.core.rest.schema.MicroschemaReference;

public class MicroschemaMigrationCause implements EventCauseInfo {

	@JsonUnwrapped
	private final MicroschemaMigrationMeshEventModel causeEvent;

	public MicroschemaMigrationCause(MicroschemaMigrationMeshEventModel causeEvent) {
		this.causeEvent = causeEvent;
	}

	@Override
	public String getUuid() {
		return causeEvent.getUuid();
	}


	@Override
	public ElementType getType() {
		return ElementType.JOB;
	}

	@Override
	public EventCauseAction getAction() {
		return EventCauseAction.MICROSCHEMA_MIGRATION;
	}

}

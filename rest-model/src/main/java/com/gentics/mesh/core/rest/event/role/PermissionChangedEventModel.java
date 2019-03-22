package com.gentics.mesh.core.rest.event.role;

import com.gentics.mesh.ElementType;
import com.gentics.mesh.core.rest.MeshEvent;
import com.gentics.mesh.core.rest.common.PermissionInfo;
import com.gentics.mesh.core.rest.event.EventCauseInfo;
import com.gentics.mesh.core.rest.event.MeshElementEventModel;
import com.gentics.mesh.core.rest.event.SimpleElementEventModel;

// TODO Add useful information
public class PermissionChangedEventModel implements MeshElementEventModel {

	private final SimpleElementEventModel role;
	private final ElementType type;
	private final MeshElementEventModel element;
	private final PermissionInfo permissions;

	public PermissionChangedEventModel(SimpleElementEventModel role, ElementType type, MeshElementEventModel element, PermissionInfo permissions) {
		this.role = role;
		this.type = type;
		this.element = element;
		this.permissions = permissions;
	}

	@Override
	public String getOrigin() {
		return role.getOrigin();
	}

	@Override
	public MeshEvent getEvent() {
		return role.getEvent();
	}

	@Override
	public EventCauseInfo getCause() {
		return role.getCause();
	}

	@Override
	public void setCause(EventCauseInfo cause) {
		role.setCause(cause);
	}

	@Override
	public String getUuid() {
		return role.getUuid();
	}

	@Override
	public void setUuid(String uuid) {
		role.setUuid(uuid);
	}

	@Override
	public String getName() {
		return role.getName();
	}

	@Override
	public void setName(String name) {
		role.setName(name);
	}

	public SimpleElementEventModel getRole() {
		return role;
	}

	public ElementType getType() {
		return type;
	}

	public MeshElementEventModel getElement() {
		return element;
	}

	public PermissionInfo getPermissions() {
		return permissions;
	}
}

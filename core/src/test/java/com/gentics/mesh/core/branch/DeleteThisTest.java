package com.gentics.mesh.core.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gentics.mesh.core.rest.event.MeshEventModelProperties;
import com.gentics.mesh.core.rest.event.ProjectEventModelProperties;
import com.gentics.mesh.core.rest.event.branch.BranchSchemaAssignEventModel;
import com.gentics.mesh.core.rest.event.migration.SchemaMigrationMeshEventModel;
import com.gentics.mesh.json.JsonUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeleteThisTest {
	static class Unwrapping {
		public String name;
		@JsonUnwrapped
		private Location location;

		public Unwrapping() { }

		public String getName() {
			return name;
		}

		public Unwrapping setName(String name) {
			this.name = name;
			return this;
		}

		public Location getLocation() {
			return location;
		}

		public Unwrapping setLocation(Location location) {
			this.location = location;
			return this;
		}
	}

	final static class Location {
		private int x;
		private int y;

		public Location() { }

		public int getX() {
			return x;
		}

		public Location setX(int x) {
			this.x = x;
			return this;
		}

		public int getY() {
			return y;
		}

		public Location setY(int y) {
			this.y = y;
			return this;
		}
	}

	static class DeepUnwrapping
	{
		@JsonUnwrapped
		private Unwrapping unwrapped;

		@JsonIgnore
		public int getY() {
			return unwrapped.location.y;
		}

		public DeepUnwrapping() { }

	}

	@Test
	public void testDeepUnwrapping() throws Exception
	{
		DeepUnwrapping bean = JsonUtil.readValue("{\"x\":3,\"name\":\"Bob\",\"y\":27}",
			DeepUnwrapping.class);
		Unwrapping uw = bean.unwrapped;
		assertNotNull(uw);
		assertEquals("Bob", uw.name);
		Location loc = uw.location;
		assertNotNull(loc);
		assertEquals(3, loc.x);
		assertEquals(27, loc.y);
	}

	String json = "{\n" +
		"    \"branch\": {\n" +
		"        \"name\": \"dummy\",\n" +
		"        \"uuid\": \"2f6d8619e9d34975ad8619e9d33975a0\"\n" +
		"    },\n" +
		"    \"schema\": {\n" +
		"        \"name\": \"schemaname\",\n" +
		"        \"uuid\": \"daea72bb08b64912aa72bb08b6a9120d\",\n" +
		"        \"version\": \"1.0\"\n" +
		"    },\n" +
		"    \"status\": \"COMPLETED\",\n" +
		"    \"project\": {\n" +
		"        \"name\": \"dummy\",\n" +
		"        \"uuid\": \"ce47fa7a48bb42d887fa7a48bb72d861\"\n" +
		"    },\n" +
		"    \"origin\": \"testNode\"\n" +
		"}";

	@Test
	public void name() {
		BranchSchemaAssignEventModel model = JsonUtil.readValue(json, BranchSchemaAssignEventModel.class);
		System.out.println(model.getProject().getName());
	}

	@Test
	public void name2() {
		MeshEventModelProperties model = JsonUtil.readValue(json, MeshEventModelProperties.class);
		System.out.println(model);

	}

	@Test
	public void name3() {
		Test2Model model = JsonUtil.readValue(json, Test2Model.class);
		System.out.println(model);
	}
}

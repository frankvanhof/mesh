package com.gentics.mesh.core.schema.field;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.gentics.mesh.core.data.node.field.list.NumberGraphFieldList;

public class NumberListFieldMigrationTest extends AbstractFieldMigrationTest {
	private static final long NUMBERVALUE = 4711L;

	private static final long OTHERNUMBERVALUE = 815L;

	private static final long ONE = 1L;

	private static final long ZERO = 0L;

	private static final DataProvider FILLNUMBERS = (container, name) -> {
		NumberGraphFieldList field = container.createNumberList(name);
		field.createNumber(NUMBERVALUE);
		field.createNumber(OTHERNUMBERVALUE);
	};

	private static final DataProvider FILLONEZERO = (container, name) -> {
		NumberGraphFieldList field = container.createNumberList(name);
		field.createNumber(ONE);
		field.createNumber(ZERO);
	};

	private static final FieldFetcher FETCH = (container, name) -> container.getNumberList(name);

	@Override
	@Test
	public void testRemove() throws Exception {
		removeField(CREATENUMBERLIST, FILLNUMBERS, FETCH);
	}

	@Override
	@Test
	public void testRename() throws Exception {
		renameField(CREATENUMBERLIST, FILLNUMBERS, FETCH, (container, name) -> {
			assertThat(container.getNumberList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumberList(name).getValues()).as(NEWFIELDVALUE).containsExactly(NUMBERVALUE, OTHERNUMBERVALUE);
		});
	}

	@Override
	@Test
	public void testChangeToBinary() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEBINARY, (container, name) -> {
			assertThat(container.getBinary(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToBoolean() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEBOOLEAN, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATENUMBERLIST, FILLONEZERO, FETCH, CREATEBOOLEAN, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBoolean(name).getBoolean()).as(NEWFIELDVALUE).isEqualTo(true);
		});
	}

	@Override
	@Test
	public void testChangeToBooleanList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEBOOLEANLIST, (container, name) -> {
			assertThat(container.getBooleanList(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATENUMBERLIST, FILLONEZERO, FETCH, CREATEBOOLEANLIST, (container, name) -> {
			assertThat(container.getBooleanList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBooleanList(name).getValues()).as(NEWFIELDVALUE).containsExactly(true, false);
		});
	}

	@Override
	@Test
	public void testChangeToDate() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEDATE, (container, name) -> {
			assertThat(container.getDate(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getDate(name).getDate()).as(NEWFIELDVALUE).isEqualTo(NUMBERVALUE);
		});
	}

	@Override
	@Test
	public void testChangeToDateList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEDATELIST, (container, name) -> {
			assertThat(container.getDateList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getDateList(name).getValues()).as(NEWFIELDVALUE).containsExactly(NUMBERVALUE, OTHERNUMBERVALUE);
		});
	}

	@Override
	@Test
	public void testChangeToHtml() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEHTML, (container, name) -> {
			assertThat(container.getHtml(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHtml(name).getHTML()).as(NEWFIELDVALUE)
					.isEqualTo(Long.toString(NUMBERVALUE) + "," + Long.toString(OTHERNUMBERVALUE));
		});
	}

	@Override
	@Test
	public void testChangeToHtmlList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEHTMLLIST, (container, name) -> {
			assertThat(container.getHTMLList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHTMLList(name).getValues()).as(NEWFIELD).containsExactly(Long.toString(NUMBERVALUE),
					Long.toString(OTHERNUMBERVALUE));
		});
	}

	@Override
	@Test
	public void testChangeToMicronode() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEMICRONODE, (container, name) -> {
			assertThat(container.getMicronode(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToMicronodeList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATEMICRONODELIST, (container, name) -> {
			assertThat(container.getMicronodeList(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNode() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATENODE, (container, name) -> {
			assertThat(container.getNode(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNodeList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATENODELIST, (container, name) -> {
			assertThat(container.getNodeList(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNumber() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATENUMBER, (container, name) -> {
			assertThat(container.getNumber(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumber(name).getNumber()).as(NEWFIELDVALUE).isEqualTo(NUMBERVALUE);
		});
	}

	@Override
	@Test
	public void testChangeToNumberList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATENUMBERLIST, (container, name) -> {
			assertThat(container.getNumberList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumberList(name).getValues()).as(NEWFIELDVALUE).containsExactly(NUMBERVALUE, OTHERNUMBERVALUE);
		});
	}

	@Override
	@Test
	public void testChangeToString() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATESTRING, (container, name) -> {
			assertThat(container.getString(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getString(name).getString()).as(NEWFIELDVALUE)
					.isEqualTo(Long.toString(NUMBERVALUE) + "," + Long.toString(OTHERNUMBERVALUE));
		});
	}

	@Override
	@Test
	public void testChangeToStringList() throws Exception {
		changeType(CREATENUMBERLIST, FILLNUMBERS, FETCH, CREATESTRINGLIST, (container, name) -> {
			assertThat(container.getStringList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getStringList(name).getValues()).as(NEWFIELDVALUE)
					.containsExactly(Long.toString(NUMBERVALUE), Long.toString(OTHERNUMBERVALUE));
		});
	}

	@Override
	@Test
	public void testCustomMigrationScript() throws Exception {
		customMigrationScript(CREATENUMBERLIST, FILLNUMBERS, FETCH, "function migrate(node, fieldname, convert) {node.fields[fieldname].reverse(); return node;}", (container, name) -> {
			NumberGraphFieldList field = container.getNumberList(name);
			assertThat(field).as(NEWFIELD).isNotNull();
			field.reload();
			assertThat(field.getValues()).as(NEWFIELDVALUE).containsExactly(OTHERNUMBERVALUE, NUMBERVALUE);
		});
	}

	@Override
	@Test(expected=ExecutionException.class)
	public void testInvalidMigrationScript() throws Exception {
		invalidMigrationScript(CREATENUMBERLIST, FILLNUMBERS, INVALIDSCRIPT);
	}

	@Override
	@Test(expected=ExecutionException.class)
	public void testSystemExit() throws Exception {
		invalidMigrationScript(CREATENUMBERLIST, FILLNUMBERS, KILLERSCRIPT);
	}
}
package com.gentics.mesh.search.index.group;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.gentics.mesh.cli.BootstrapInitializer;
import com.gentics.mesh.core.data.Group;
import com.gentics.mesh.core.data.Project;
import com.gentics.mesh.core.data.Release;
import com.gentics.mesh.core.data.root.RootVertex;
import com.gentics.mesh.core.data.search.SearchQueue;
import com.gentics.mesh.core.data.search.UpdateDocumentEntry;
import com.gentics.mesh.graphdb.spi.Database;
import com.gentics.mesh.parameter.VersioningParameters;
import com.gentics.mesh.search.SearchProvider;
import com.gentics.mesh.search.index.entry.AbstractIndexHandler;

/**
 * Handler for the elastic search group index.
 */
@Singleton
public class GroupIndexHandler extends AbstractIndexHandler<Group> {

	@Inject
	GroupTransformator transformator;

	@Inject
	public GroupIndexHandler(SearchProvider searchProvider, Database db, BootstrapInitializer boot, SearchQueue searchQueue) {
		super(searchProvider, db, boot, searchQueue);
	}

	@Override
	public Class<Group> getElementClass() {
		return Group.class;
	}

	@Override
	public GroupTransformator getTransformator() {
		return transformator;
	}

	@Override
	protected String composeIndexTypeFromEntry(UpdateDocumentEntry entry) {
		return Group.composeIndexType();
	}

	@Override
	public Map<String, String> getIndices() {
		return Collections.singletonMap(Group.TYPE, Group.TYPE);
	}

	@Override
	protected String composeDocumentIdFromEntry(UpdateDocumentEntry entry) {
		return entry.getElementUuid();
	}

	@Override
	protected String composeIndexNameFromEntry(UpdateDocumentEntry entry) {
		return Group.composeIndexName();
	}

	@Override
	public Set<String> getSelectedIndices(Project project, Release release, VersioningParameters parameters) {
		return Collections.singleton(Group.TYPE);
	}

	@Override
	protected RootVertex<Group> getRootVertex() {
		return boot.meshRoot().getGroupRoot();
	}

}

package com.gentics.mesh.core.data.search;

import java.util.Map;
import java.util.Set;

import com.gentics.mesh.context.InternalActionContext;
import com.gentics.mesh.core.data.Project;
import com.gentics.mesh.core.data.Release;
import com.gentics.mesh.core.data.relationship.GraphPermission;
import com.gentics.mesh.parameter.VersioningParameters;

import rx.Completable;

/**
 * Index handlers are used to interact with the search provider index on a type specific level. Each domain model in mesh which is indexable needs to implement
 * an index handler in order to interact with search index specific documents in the index (CRUD on search index documents).
 */
public interface IndexHandler {

	/**
	 * Clear the index. This will effectively remove all documents from the index without removing the index itself.
	 * 
	 * @return
	 */
	Completable clearIndex();

	/**
	 * Initialise the search index by creating the index first and setting the mapping afterwards.
	 * 
	 * @return
	 */
	Completable init();

	/**
	 * Handle the search queue update mapping entry.
	 * 
	 * @param entry
	 * @return
	 */
	Completable updateMapping(CreateIndexEntry entry);

	/**
	 * Delete the document with the given UUID and document type from the search index.
	 * 
	 * @param entry
	 *            search queue entry
	 * @return
	 */
	Completable delete(UpdateDocumentEntry entry);

	/**
	 * Load the given element and invoke store(T element) to store it in the index.
	 * 
	 * @param entry
	 *            search queue entry
	 * @return
	 */
	Completable store(UpdateDocumentEntry entry);

	/**
	 * Reindex all documents for the type which the handler is capable of.
	 * 
	 * @return
	 */
	Completable reindexAll();

	/**
	 * Load a map which contains the applicable indices. The key of the map is the index name.
	 * 
	 * @return Map with index information
	 */
	Map<String, String> getIndices();

	/**
	 * Get the names of all selected indices. The project will be used to determine the project scope. The release will define the release scope. If possible
	 * even the version will be used in order to generate the set of indices which are selected.
	 * 
	 * @param project
	 * @param release
	 * @param versioningParameters
	 * @return name of selected indices
	 */
	Set<String> getSelectedIndices(Project project, Release release, VersioningParameters versioningParameters);

	/**
	 * Get the permission required to read the elements found in the index.
	 * 
	 * @param ac
	 *            action context
	 * @return read permission
	 */
	default GraphPermission getReadPermission(InternalActionContext ac) {
		return GraphPermission.READ_PERM;
	}

	/**
	 * Check whether the given element class can be handled by this handler.
	 * 
	 * @param clazzOfElement
	 * @return
	 */
	boolean accepts(Class<?> clazzOfElement);

	/**
	 * Create the index, if it is one of the indices handled by this index handler. If the index name is not handled by this index handler, an error will be
	 * thrown
	 * 
	 * @param entry
	 *            Search queue entry for create index action
	 * @return
	 */
	Completable createIndex(CreateIndexEntry entry);

}

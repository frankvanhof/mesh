package com.gentics.mesh.core.data.root;

import java.util.List;
import java.util.Stack;

import com.gentics.mesh.api.common.PagingInfo;
import com.gentics.mesh.core.Page;
import com.gentics.mesh.core.data.MeshAuthUser;
import com.gentics.mesh.core.data.MeshVertex;
import com.gentics.mesh.core.data.Project;
import com.gentics.mesh.core.data.SchemaContainer;
import com.gentics.mesh.core.data.User;
import com.gentics.mesh.core.data.node.Node;
import com.gentics.mesh.util.InvalidArgumentException;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface NodeRoot extends RootVertex<Node> {

	public static final String TYPE = "nodes";

	Page<? extends Node> findAll(MeshAuthUser requestUser, List<String> languageTags, PagingInfo pagingInfo) throws InvalidArgumentException;

	Node create(User user, SchemaContainer container, Project project);

	void addNode(Node node);

	void removeNode(Node node);

}

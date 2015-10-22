package com.gentics.mesh.graphdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.gentics.mesh.etc.StorageOptions;
import com.gentics.mesh.graphdb.ferma.AbstractDelegatingFramedOrientGraph;
import com.gentics.mesh.graphdb.model.MeshElement;
import com.gentics.mesh.graphdb.spi.AbstractDatabase;
import com.gentics.mesh.graphdb.spi.Database;
import com.gentics.mesh.graphdb.spi.TrxHandler;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.command.OCommandOutputListener;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.tool.ODatabaseExport;
import com.orientechnologies.orient.core.db.tool.ODatabaseImport;
import com.orientechnologies.orient.core.exception.OConcurrentModificationException;
import com.orientechnologies.orient.core.exception.OSchemaException;
import com.orientechnologies.orient.core.index.OCompositeKey;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.syncleus.ferma.FramedGraph;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * OrientDB specific mesh graph database implementation.
 */
public class OrientDBDatabase extends AbstractDatabase {

	private static final Logger log = LoggerFactory.getLogger(OrientDBDatabase.class);

	private OrientGraphFactory factory;
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");
	private int maxRetry = 25;

	@Override
	public void stop() {
		factory.close();
		Orient.instance().shutdown();
		Database.setThreadLocalGraph(null);
	}

	@Override
	public void init(StorageOptions options, Vertx vertx) {
		super.init(options, vertx);
		if (options != null && options.getParameters() != null && options.getParameters().get("maxTransactionRetry") != null) {
			this.maxRetry = options.getParameters().get("maxTransactionRetry").getAsInt();
			log.info("Using {" + this.maxRetry + "} transaction retries before failing");
		}
	}

	@Override
	public void clear() {
		OrientGraphNoTx noTx = factory.getNoTx();
		try {
			noTx.drop();
		} finally {
			noTx.shutdown();
		}
	}

	@Override
	public void start() {
		Orient.instance().startup();
		if (options == null || options.getDirectory() == null) {
			log.info("No graph database settings found. Fallback to in memory mode.");
			factory = new OrientGraphFactory("memory:tinkerpop");
		} else {
			factory = new OrientGraphFactory("plocal:" + options.getDirectory()).setupPool(5, 100);
		}
		configureGraphDB();
		//		createIndices();

	}

	private void configureGraphDB() {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			tx.setUseLightweightEdges(false);
			tx.setUseVertexFieldsForEdgeLabels(false);
		} finally {
			tx.shutdown();
		}
	}

	@Override
	public void addEdgeIndex(String label, String... extraFields) {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			OrientEdgeType e = tx.getEdgeType(label);
			if (e == null) {
				e = tx.createEdgeType(label);
			}
			if (e.getProperty("in") == null) {
				e.createProperty("in", OType.LINK);
			}
			if (e.getProperty("out") == null) {
				e.createProperty("out", OType.LINK);
			}
			String indexName = "e." + label.toLowerCase();
			String[] fields = { "out", "in" };
			if (e.getClassIndex(indexName) == null) {
				e.createIndex(indexName, OClass.INDEX_TYPE.UNIQUE_HASH_INDEX, fields);
			}
		} finally {
			tx.shutdown();
		}
	}

	@Override
	public Iterator<Vertex> getVertices(Class<?> classOfVertex, String[] fieldNames, Object[] fieldValues) {
		FramedGraph graph = Database.getThreadLocalGraph();
		Graph baseGraph = ((AbstractDelegatingFramedOrientGraph) graph).getBaseGraph();
		OrientBaseGraph orientBaseGraph = ((OrientBaseGraph) baseGraph);
		return orientBaseGraph.getVertices(classOfVertex.getSimpleName(), fieldNames, fieldValues).iterator();
	}

	@Override
	public void addEdgeType(String label, String... stringPropertyKeys) {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			OrientEdgeType e = tx.getEdgeType(label);
			if (e == null) {
				e = tx.createEdgeType(label);
			}
			for (String key : stringPropertyKeys) {
				if (e.getProperty(key) == null) {
					e.createProperty(key, OType.STRING);
				}
			}
		} finally {
			tx.shutdown();
		}
	}

	@Override
	public void addVertexType(Class<?> clazzOfVertex) {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			OrientVertexType e = tx.getVertexType(clazzOfVertex.getSimpleName());
			if (e == null) {
				e = tx.createVertexType(clazzOfVertex.getSimpleName(), "V");
			}
		} finally {
			tx.shutdown();
		}
	}

	@Override
	public void addEdgeIndexSource(String label) {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			OrientEdgeType e = tx.getEdgeType(label);
			if (e == null) {
				e = tx.createEdgeType(label);
			}
			if (e.getProperty("out") == null) {
				e.createProperty("out", OType.LINK);
			}
			String indexName = "e." + label.toLowerCase();
			String[] fields = { "out" };
			if (e.getClassIndex(indexName) == null) {
				e.createIndex(indexName, OClass.INDEX_TYPE.NOTUNIQUE_HASH_INDEX, fields);
			}
		} finally {
			tx.shutdown();
		}
	}

	@Override
	public void addVertexIndex(Class<?> clazzOfVertices, String... fields) {
		OrientGraphNoTx tx = factory.getNoTx();
		try {
			String name = clazzOfVertices.getSimpleName();
			OrientVertexType v = tx.getVertexType(name);
			if (v == null) {
				v = tx.createVertexType(name, "V");
			}
			for (String field : fields) {
				if (v.getProperty(field) == null) {
					v.createProperty(field, OType.STRING);
				}
			}
			if (v.getClassIndex(name) == null) {
				v.createIndex(name, OClass.INDEX_TYPE.UNIQUE_HASH_INDEX, fields);
			}
		} finally {
			tx.shutdown();
		}

	}

	@Override
	public void reload(MeshElement element) {
		((OrientVertex) element.getElement()).reload();
	}

	@Override
	public Trx trx() {
		return new OrientDBTrx(factory);
	}

	@Override
	public <T> Database trx(TrxHandler<Future<T>> txHandler, Handler<AsyncResult<T>> resultHandler) {
		/**
		 * OrientDB uses the MVCC pattern which requires a retry of the code that manipulates the graph in cases where for example an
		 * {@link OConcurrentModificationException} is thrown.
		 */
		Future<T> currentTransactionCompleted = null;
		for (int retry = 0; retry < maxRetry; retry++) {
			currentTransactionCompleted = Future.future();
			try (Trx tx = trx()) {
				// TODO FIXME get rid of the countdown latch
				CountDownLatch latch = new CountDownLatch(1);
				currentTransactionCompleted.setHandler(rh -> {
					if (rh.succeeded()) {
						tx.success();
					} else {
						tx.failure();
					}
					latch.countDown();
				});
				txHandler.handle(currentTransactionCompleted);
				latch.await(30, TimeUnit.SECONDS);
				break;
			} catch (OSchemaException e) {
				log.error("OrientDB schema exception detected.");
				// TODO maybe we should invoke a metadata getschema reload?
				// factory.getTx().getRawGraph().getMetadata().getSchema().reload();
				// Database.getThreadLocalGraph().getMetadata().getSchema().reload();
			} catch (OConcurrentModificationException e) {
				if (log.isTraceEnabled()) {
					log.trace("Error while handling transaction. Retrying " + retry, e);
				}
			} catch (Exception e) {
				log.error("Error handling transaction", e);
				resultHandler.handle(Future.failedFuture(e));
				return this;
			}
			if (log.isDebugEnabled()) {
				log.debug("Retrying .. {" + retry + "}");
			}
		}
		if (currentTransactionCompleted != null && currentTransactionCompleted.isComplete()) {
			resultHandler.handle(currentTransactionCompleted);
			return this;
		}
		resultHandler.handle(Future.failedFuture("retry limit for trx exceeded"));
		return this;

	}

	@Override
	public NoTrx noTrx() {
		return new OrientDBNoTrx(factory);
	}

	@Override
	public void backupGraph(String backupDirectory) throws IOException {
		ODatabaseDocumentTx db = factory.getDatabase();
		try {
			OCommandOutputListener listener = new OCommandOutputListener() {
				@Override
				public void onMessage(String iText) {
					System.out.println(iText);
				}
			};
			String dateString = formatter.format(new Date());
			String backupFile = "backup_" + dateString + ".zip";
			OutputStream out = new FileOutputStream(new File(backupDirectory, backupFile).getAbsolutePath());
			db.backup(out, null, null, listener, 9, 2048);
		} finally {
			db.close();
		}
	}

	@Override
	public void restoreGraph(String backupFile) throws IOException {
		ODatabaseDocumentTx db = factory.getDatabase();
		try {
			OCommandOutputListener listener = new OCommandOutputListener() {
				@Override
				public void onMessage(String iText) {
					System.out.println(iText);
				}
			};
			InputStream in = new FileInputStream(backupFile);
			db.restore(in, null, null, listener);
		} finally {
			db.close();
		}

	}

	@Override
	public void exportGraph(String outputDirectory) throws IOException {
		ODatabaseDocumentTx db = factory.getDatabase();
		try {
			OCommandOutputListener listener = new OCommandOutputListener() {
				@Override
				public void onMessage(String iText) {
					System.out.println(iText);
				}
			};

			String dateString = formatter.format(new Date());
			String exportFile = "export_" + dateString;

			ODatabaseExport export = new ODatabaseExport(db, new File(outputDirectory, exportFile).getAbsolutePath(), listener);
			export.exportDatabase();
			export.close();
		} finally {
			db.close();
		}
	}

	@Override
	public void importGraph(String importFile) throws IOException {
		ODatabaseDocumentTx db = factory.getDatabase();
		try {
			OCommandOutputListener listener = new OCommandOutputListener() {
				@Override
				public void onMessage(String iText) {
					System.out.println(iText);
				}
			};
			ODatabaseImport databaseImport = new ODatabaseImport(db, importFile, listener);
			databaseImport.importDatabase();
			databaseImport.close();
		} finally {
			db.close();
		}

	}

	@Override
	public Object createComposedIndexKey(Object... keys) {
		return new OCompositeKey(keys);
	}

	@Override
	public void setVertexType(Element element, Class<?> classOfVertex) {
		((OrientVertex) element).moveToClass(classOfVertex.getSimpleName());
	}

}

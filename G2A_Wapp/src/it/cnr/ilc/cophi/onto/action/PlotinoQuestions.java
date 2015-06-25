/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cophi.onto.action;

import it.cnr.ilc.cophi.action.management.RepositoryBean;
import it.cnr.ilc.cophi.onto.model.OntoResult;
import it.cnr.ilc.cophi.utils.Consts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

/**
 *
 * @author andrea
 */
@ManagedBean
@SessionScoped	
public class PlotinoQuestions {

	private static final Logger logger = LogManager.getLogger("PlotinoQuestions");

	private static List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList(
			"termine", "relazione", "tratto", "classe", "valore",
			"classe_target", "termine_target", "inferita");
	private final static String columnTemplate_1_relazione = "termine relazione termine_target inferita";
	// private String columnTemplate_1_relazione =
	// "relazione termine classe tipo";
	private final static String columnTemplate_1_tratto = "relazione termine_target inferita";
	//	private  final static String columnTemplate_2 = "termine classe inferita";
	// private String columnTemplate_3 = "termine classe tipo";
	private final static String columnTemplate_3 = "termine relazione termine_target inferita";
	//	private final static String columnTemplate_4 = "termine classe inferita";
	private static List<OntoResult> res = new ArrayList<OntoResult>();
	private static List<OntoResult> clearRes = new ArrayList<OntoResult>();

    private OntoResult selectedTableRow;

	static public class ColumnModel {

		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	public List<ColumnModel> getColumns() {
		logger.info("getColumns() columns: " + columns);

		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<ColumnModel> columns) {
		logger.info("setColumns() columns: " + columns);
		this.columns = columns;
	}

	public void createDynamicColumns(String template) {
		logger.info("createDynamicColumns template: " + template);
		String[] columnKeys = template.split(" ");
		columns.clear();
		for (String columnKey : columnKeys) {
			String key = columnKey.trim();
			if (VALID_COLUMN_KEYS.contains(key)) {
				columns.add(new ColumnModel(getColumnTitle(columnKey),
						columnKey));
			}
		}
	}

	private String getColumnTitle(String col) {
		if (col.equals("termine")) {
			return "SOURCE TERM";
		} else if (col.equals("classe")) {
			return "TYPE ONTOLOGIQUE";
		} else if (col.equals("inferita")) {
			return "INFERRED";
		} else if (col.equals("relazione")) {
			return "RELATION";
		} else if (col.equals("termine_target")) {
			return "TARGET TERM";
		} else if (col.equals("classe_target")) {
			return "TYPE ONTOLOGIQUE";
		} else if (col.equals("tratto")) {
			return "PROPERTY";
		} else {
			return "VALUE";
		}
	}


	@ManagedProperty(value="#{repository}")
	private  RepositoryBean repositoryBean;

	/**
	 * @return the repositoryBean
	 */
	public RepositoryBean getRepositoryBean() {
		return repositoryBean;
	}

	/**
	 * @param repositoryBean the repositoryBean to set
	 */
	public void setRepositoryBean(RepositoryBean repositoryBean) {
		this.repositoryBean = repositoryBean;
	}





	/**
	 * @return the objRelationMap
	 */
	public Map<String, String> getObjRelationMap() {
		return repositoryBean.getObjRelationMap();
	}

	/**
	 * @param objRelationMap the objRelationMap to set
	 */
	public void setObjRelationMap(Map<String, String> objRelationMap) {
		repositoryBean.setObjRelationMap(objRelationMap);
	}


	/**
	 * @return the query_1_param_2
	 */
	public String getQuery_1_param_1() {
		return repositoryBean.getQuery_1_param_1();
	}

	/**
	 * @param query_1_param_1 the query_1_param_1 to set
	 */
	public void setQuery_1_param_1(String query_1_param_1) {
		repositoryBean.setQuery_1_param_1(query_1_param_1);
	}
	/**
	 * @return the query_1_param_2
	 */
	public String getQuery_1a_param_1() {
		return repositoryBean.getQuery_1a_param_1();
	}

	/**
	 * @param query_1_param_1 the query_1_param_1 to set
	 */
	public void setQuery_1a_param_1(String query_1a_param_1) {
		repositoryBean.setQuery_1a_param_1(query_1a_param_1);
	}

	/**
	 * @return the query_1_param_2
	 */
	public String getQuery_1b_param_1() {
		return repositoryBean.getQuery_1b_param_1();
	}

	/**
	 * @param query_1_param_1 the query_1_param_1 to set
	 */
	public void setQuery_1b_param_1(String query_1b_param_1) {
		repositoryBean.setQuery_1b_param_1(query_1b_param_1);
	}

	/**
	 * @return the query_2_param_1
	 */
	public String getQuery_2_param_1() {
		return repositoryBean.getQuery_2_param_1();
	}

	/**
	 * @param query_2_param_1 the query_2_param_1 to set
	 */
	public void setQuery_2_param_1(String query_2_param_1) {
		repositoryBean.setQuery_2_param_1(query_2_param_1);
	}

	/**
	 * @return the query_2_param_2
	 */
	public String getQuery_2_param_2() {
		return repositoryBean.getQuery_2_param_2();
	}

	/**
	 * @param query_2_param_2 the query_2_param_2 to set
	 */
	public void setQuery_2_param_2(String query_2_param_2) {
		repositoryBean.setQuery_2_param_2(query_2_param_2);
	}

	/**
	 * @return the query_2_param_3
	 */
	public String getQuery_2_param_3() {
		return repositoryBean.getQuery_2_param_3();
	}

	/**
	 * @param query_2_param_3 the query_2_param_3 to set
	 */
	public void setQuery_2_param_3(String query_2_param_3) {
		repositoryBean.setQuery_2_param_3( query_2_param_3);
	}

	/**
	 * @return the query_3_param_1
	 */
	public String getQuery_3_param_1() {
		return repositoryBean.getQuery_3_param_1();
	}

	/**
	 * @param query_3_param_1 the query_3_param_1 to set
	 */
	public void setQuery_3_param_1(String query_3_param_1) {
		repositoryBean.setQuery_3_param_1(query_3_param_1);
	}

	/**
	 * @return the query_3_param_2
	 */
	public String getQuery_3_param_2() {
		return repositoryBean.getQuery_3_param_2();
	}

	/**
	 * @param query_3_param_2 the query_3_param_2 to set
	 */
	public void setQuery_3_param_2(String query_3_param_2) {
		repositoryBean.setQuery_3_param_2(query_3_param_2);
	}

	public void query_1_propertyChanged(ValueChangeEvent e) {
		repositoryBean.setQuery_1_param_1(e.getNewValue().toString());
	}

	public void query_3_instanceChanged(ValueChangeEvent e) {
		repositoryBean.setQuery_3_param_1(e.getNewValue().toString());
	}



	public void q_1_p_1Changed(AjaxBehaviorEvent event) {
		String queryString = null;
		String inst = null;
		//       repositoryBean.setOntoInstanceMap(new HashMap<String, String>());

		queryString = Consts.NAMESPACES
				+ "SELECT ?termine "
				+ "WHERE { ?termine a owl:Thing . ?termine ?property ?value . ?property rdf:type owl:"
				+ getQuery_1b_param_1() + " . } " + "ORDER BY ?termine";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query,
				repositoryBean.getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			inst = binding.get("termine").toString().split("#")[1];
			repositoryBean.getOntoQueryInstanceMap().put(inst, inst);
		}
		qe.close();
	}

	public void q_3_p_1Changed(AjaxBehaviorEvent event) {
		String queryString = null;
		String elem = null;
		repositoryBean.getObjRelationMap().clear();
		//       repositoryBean.setObjRelationMap(new HashMap<String, String>());
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { "
				+ "{ plotino:"
				+ getQuery_3_param_1()
				+ " ?property ?v1 . ?property rdfs:subPropertyOf plotino:Ontological_Relation . } "
				+ "UNION "
				+ "{ ?v2 ?property plotino:"
				+ getQuery_3_param_1()
				+ " . ?property rdfs:subPropertyOf plotino:Ontological_Relation . } "
				+ "} "
				+ "ORDER BY ?property";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query,
				repositoryBean.getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			elem = binding.get("property").toString().split("#")[1];
			if (!"Ontological_Relation".equals(elem)) {
				repositoryBean.getObjRelationMap().put(elem, elem);
			}
		}
		if (repositoryBean.getObjRelationMap().isEmpty()) {
			repositoryBean.getObjRelationMap().put("None", "None");
		}
		qe.close();
	}

	public Map<String, String> getInstances() {
		return repositoryBean.getOntoInstanceMap();
	}

	public Map<String, String> getObjRelations() {
		return repositoryBean.getObjRelationMap();
	}

	public Map<String, String> getEnInstances() {
		return repositoryBean.getOntoInstanceMapEn();
	}


	// esegue una query_1 dato un modello
	/**
	 * 
	 * @param queryString
	 * @param modelType
	 * @param termScope  source or target
	 * @param relType
	 */
	private void executeQuery_1(String queryString, boolean isEnglishTerm, String modelType, String termScope, String relType) {
		
		logger.info("executeQuery_1(" + queryString + " " + modelType + " " + termScope + " " + relType + ")");
		executeQuery_1_1a(queryString, isEnglishTerm, modelType, termScope, relType, getQuery_1_param_1());

	}
	
	private void executeQuery_1a(String queryString, boolean isEnglishTerm, String modelType, String termScope, String relType) {
		
		logger.info("executeQuery_1a(" + queryString + " " + modelType + " " + termScope + " " + relType + ")");
		executeQuery_1_1a(queryString, isEnglishTerm, modelType, termScope, relType, getQuery_1a_param_1());

	}

	private void executeQuery_1_1a(String queryString, boolean isEnglishTerm, String modelType, String termScope, String relType, String sourceTerm) {
		
		logger.info("executeQuery_1(" + queryString + " " + modelType + " " + termScope + " " + relType + ")");
		
		OntModel model = modelType.equals("model") ? repositoryBean.getOntoModel() : repositoryBean.getOntoExplicitModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);

		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			String prop = binding.get("property").toString().split("#")[1];

			if ((!"Lexical_Relation".equals(prop)) && (!"Ontological_Relation".equals(prop))) {
				String term = binding.get("termine").toString();
				OntoResult ontoRes = new OntoResult();
				ontoRes.setRelazione(prop);

				if (termScope.equals("source")) {
					
					ontoRes.setTermine((isEnglishTerm?binding.get("engTerm").toString().split("#")[1]:sourceTerm));
					ontoRes.setTermine_target(relType.equals("obj") ? ((term.contains("#"))?term.split("#")[1]:term) : term);
				} else {
					ontoRes.setTermine((term.contains("#"))?term.split("#")[1]:term);
					ontoRes.setTermine_target((isEnglishTerm?binding.get("engTerm").toString().split("#")[1]:sourceTerm));
				}

				if (modelType.equals("model")) {
					res.add(ontoRes); 
				} else {
					clearRes.add(ontoRes);
				}
			}
		}
		qe.close();
	}
	
	
	/**
	 * 
	 * @param queryString
	 * @param modelType
	 * @param termScope
	 * @param relType
	 */
	private void executeQuery_1b(String queryString, String modelType, String termScope, String relType) {
		
		logger.info("executeQuery_1b(" + queryString + " " + modelType + " " + termScope + " " + relType + ")");
		OntModel model = modelType.equals("model") ? repositoryBean.getOntoModel() : repositoryBean.getOntoExplicitModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);

		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			String prop = binding.get("property").toString().split("#")[1];

			if (!prop.equals("Ontological_Relation")) {
				String term = binding.get("termine").toString();
				OntoResult ontoRes = new OntoResult();
				ontoRes.setRelazione(prop);

				if (termScope.equals("source")) {
					ontoRes.setTermine(getQuery_1b_param_1());
					ontoRes.setTermine_target(relType.equals("obj") ? ((term.contains("#"))?term.split("#")[1]:term) : term);
				} else {
					ontoRes.setTermine((term.contains("#"))?term.split("#")[1]:term);
					ontoRes.setTermine_target(getQuery_1b_param_1());
				}

				if (modelType.equals("model")) {
					res.add(ontoRes); 
				} else {
					clearRes.add(ontoRes);
				}
			}
		}
		qe.close();
	}

	// esegue una query_2 dato un modello
	private void executeQuery_2(String queryString, String modelType, String src, String trg) {
		logger.info("executeQuery_2(" + queryString + " " + modelType + " " + src + " " + trg + ")");
		OntModel model = modelType.equals("model") ? repositoryBean.getOntoModel() : repositoryBean.getOntoExplicitModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			String prop = binding.get("property").toString().split("#")[1];
			if (!"Ontological_Relation".equals(prop)) {
				OntoResult ontoRes = new OntoResult();
				ontoRes.setTermine(src);
				ontoRes.setTermine_target(trg);
				ontoRes.setRelazione(prop);
				if (modelType.equals("model")) {
					res.add(ontoRes);
				} else {
					clearRes.add(ontoRes);
				}
				//System.out.println("------RESULT: " + prop);
			}
		}
		qe.close();
	}

	// esegue una query_3 dato un modello
	private void executeQuery_3(String queryString, String modelType) {
		logger.info("executeQuery_3(" + queryString + " " + modelType + ")");

		OntModel model = modelType.equals("model") ? repositoryBean.getOntoModel() : repositoryBean.getOntoExplicitModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			String term = binding.get("termine").toString().split("#")[1];
			OntoResult ontoRes = new OntoResult();
			ontoRes.setTermine(getQuery_3_param_1());
			ontoRes.setRelazione(getQuery_3_param_2());
			ontoRes.setTermine_target(term);
			if (modelType.equals("model")) {
				res.add(ontoRes);
			} else {
				clearRes.add(ontoRes);
			}
		}
		qe.close();
	}

	private boolean inferred(OntoResult e, int queryType) {
		logger.info("inferred(" + e + " " + queryType + ")");

		switch (queryType) {
		case 1:
			for (OntoResult or : clearRes) {
				if ((or.getTermine().equals(e.getTermine())) && ((or.getTermine_target().equals(e.getTermine_target())))
						&& ((or.getRelazione().equals(e.getRelazione())))) {
					return false;
				}
			}
			break;
		case 2:
			for (OntoResult or : clearRes) {
				if (or.getRelazione().equals(e.getRelazione())) {
					return false;
				}
			}
			break;
		case 3:
			for (OntoResult or : clearRes) {
				if (or.getTermine_target().equals(e.getTermine_target())) {
					return false;
				}
			}
			break;

		case 4: //1b
			for (OntoResult or : clearRes) {
				if ((or.getRelazione().equals(e.getRelazione())) && ((or.getTermine_target().equals(e.getTermine_target())))) {
					return false;
				}
			}
			break;
			
		default:
			break;
		}	
		
		return true;
	}

	/*
	public void runQuestion_1_old() throws IOException {
		logger.info("runQuestion_1()");
		res.clear();
		clearRes.clear();
		//		columns.clear();
		if (getQuery_1b_param_1().equals("ObjectProperty")) {

			// query in cui l'istanza selezionata è SOURCE delle relazioni

			String queryString = Consts.NAMESPACES
					+ "SELECT DISTINCT ?property ?termine "
					+ "WHERE { ?inst plotino:greekTerm '"
					+ getQuery_1b_param_1()
					+ "' . ?inst ?property ?termine . "
					+ "?property rdf:type owl:"
					+ getQuery_1b_param_1()
					+ " } ORDER BY ?termine ?property ";

			// query al modello 
			executeQuery_1(queryString, "model", "source", "obj");

			// query al modello esplicito
			executeQuery_1(queryString, "explicit", "source", "obj");

			// query in cui l'istanza selezionata è TARGET delle relazioni
			queryString = Consts.NAMESPACES
					+ "SELECT DISTINCT ?property ?termine "
					+ "WHERE { ?inst plotino:greekTerm '"
					+ getQuery_1b_param_1()
					+ "' . ?termine ?property ?inst . ?property rdf:type owl:"
					+ getQuery_1b_param_1()
					+ " . } ORDER BY ?termine ?property ";

			// query al modello 
			executeQuery_1(queryString, "model", "target", "obj");

			// query al modello esplicito
			executeQuery_1(queryString, "explicit", "target", "obj");

			// verifica quali sono le triple inferite
			for (OntoResult e : res) {
				if (inferred(e, 1)) {
					e.setInferita("yes");
				}
			}

			createDynamicColumns(columnTemplate_1_relazione);

		} else {

			String queryString = Consts.NAMESPACES
					+ "SELECT DISTINCT ?property ?termine "
					+ "WHERE { plotino:"
					+ getQuery_1b_param_1()
					+ " ?property ?termine . ?property rdf:type owl:"
					+ getQuery_1b_param_1() + " . }" + "ORDER BY ?property ";

			// query al modello 
			executeQuery_1(queryString, "model", "source", "data");

			// query al modello esplicito
			executeQuery_1(queryString, "explicit", "source", "data");

			// verifica quali sono le triple inferite
			for (OntoResult e : res) {
				if (inferred(e, 1)) {
					e.setInferita("yes");
				}
			}
			createDynamicColumns(columnTemplate_1_tratto);
		}
	}

	*/
	/**
	 *  What are the lexical relations the term [greek]  is related to?
	 * @throws IOException
	 */
	public void runQuestion_1a() throws IOException {
		logger.info("runQuestion_1a() " + getQuery_1a_param_1());
		res.clear();
		clearRes.clear();

		/*	String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { plotino:"+ getQuery_1a_param_1() +" plotino:denotes ?engTermine . ?engTermine ?property ?termine "
				+ " . ?property rdf:type owl:ObjectProperty . ?property rdfs:subPropertyOf plotino:Lexical_Relation  } ORDER BY ?termine ?property ";*/
		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?termine ?property "
				+ "WHERE { plotino:"+ getQuery_1a_param_1() + " ?property ?termine . ?property rdfs:subPropertyOf plotino:Lexical_Relation } ";

		// query al modello 
		executeQuery_1a(queryString, false, "model", "source", "obj");

		// query al modello esplicito
		executeQuery_1a(queryString, false, "explicit", "source", "obj");

		/* COMMENTATO PERCHE' E' RIFLESSIVA (?)
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?termine ?property "
				+ "WHERE { ?termine  ?property  plotino:"+ getQuery_1a_param_1() + " . ?property rdfs:subPropertyOf plotino:Lexical_Relation } ";

		// query al modello 
		executeQuery_1(queryString, "model", "target", "obj");

		// query al modello esplicito
		executeQuery_1(queryString, "explicit", "target", "obj");
		 */
		// verifica quali sono le triple inferite
		for (OntoResult e : res) {
			if (inferred(e, 1)) {
				e.setInferita("yes");
			}
		}

		createDynamicColumns(columnTemplate_1_relazione);

	}

	/**
	 * What are the ontological relations the concept  [english] is related to?
	 * @throws IOException
	 */
	public void runQuestion_1b() throws IOException {
		logger.info("runQuestion_1b() english: " + getQuery_1b_param_1());
		res.clear();
		clearRes.clear();
		//		columns.clear();

		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { ?termine ?property plotino:"
				+ getQuery_1b_param_1()
				+ " . ?property rdf:type owl:ObjectProperty . ?property rdfs:subPropertyOf plotino:Ontological_Relation } ORDER BY ?termine ?property ";

		// query al modello 
		executeQuery_1b(queryString, "model", "target", "obj");

		// query al modello esplicito
		executeQuery_1b(queryString, "explicit", "target", "obj");

		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { plotino:" + getQuery_1b_param_1()
				+ " ?property ?termine . ?property rdf:type owl:ObjectProperty . ?property rdfs:subPropertyOf plotino:Ontological_Relation } ORDER BY ?termine ?property ";

		// query al modello 
		executeQuery_1b(queryString, "model", "source", "obj");

		// query al modello esplicito
		executeQuery_1b(queryString, "explicit", "source", "obj");

		// verifica quali sono le triple inferite
		for (OntoResult e : res) {
			if (inferred(e, 1)) {
				e.setInferita("yes");
			}
		}

		createDynamicColumns(columnTemplate_1_relazione);

	}


	/**
	 * 
	 * @throws IOException
	 */
	public void runQuestion_1() throws IOException {
		logger.info("runQuestion_1() greek term: " + getQuery_1_param_1());
		res.clear();
		clearRes.clear();
		//		columns.clear();

		//1
		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { plotino:" + getQuery_1_param_1()
				+ " ?property ?termine . ?property rdfs:subPropertyOf plotino:Ontological_Relation  . } ORDER BY ?property ";

		executeQuery_1(queryString, false, "model", "source", "obj"); 		// query al modello 
		executeQuery_1(queryString, false, "explicit", "source", "obj");		// query al modello esplicito


		//2
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { plotino:" + getQuery_1_param_1()
				+ " ?property ?termine . ?property rdfs:subPropertyOf plotino:Lexical_Relation  . } ORDER BY ?property ";

		executeQuery_1(queryString, false, "model", "source", "obj");		// query al modello 
		executeQuery_1(queryString, false, "explicit", "source", "obj");		// query al modello esplicito

		//3
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine ?engTerm "
				+ "WHERE { plotino:" + getQuery_1_param_1()
				+ " ?denotes ?engTerm . ?engTerm ?property ?termine . ?property rdfs:subPropertyOf plotino:Ontological_Relation  . } ORDER BY ?property ";

		executeQuery_1(queryString, true, "model", "source", "obj");		// query al modello 
		executeQuery_1(queryString, true, "explicit", "source", "obj");		// query al modello esplicito

		//4
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { ?termine " 
				+ " ?property plotino:" + getQuery_1_param_1() + ". ?property rdfs:subPropertyOf plotino:Ontological_Relation  . } ORDER BY ?property ";

		executeQuery_1(queryString, false, "model", "target", "obj");		// query al modello 
		executeQuery_1(queryString, false, "explicit", "target", "obj");		// query al modello esplicito
		
		//5
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine ?engTerm " 
				+ "WHERE { plotino:" + getQuery_1_param_1()  
				+ " ?denotes ?engTerm . ?termine ?property ?engTerm . ?property rdfs:subPropertyOf plotino:Ontological_Relation  . } ORDER BY ?property ";

		executeQuery_1(queryString, true, "model", "target", "obj");		// query al modello 
		executeQuery_1(queryString, true, "explicit", "target", "obj");		// query al modello esplicito

		//6
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine ?engTerm "
				+ "WHERE { plotino:" + getQuery_1_param_1()  
				+ " ?denotes ?engTerm . ?engTerm ?property ?termine . ?property rdf:type owl:DatatypeProperty . } ORDER BY ?property ";

		executeQuery_1(queryString, true, "model", "source", "data");		// query al modello 
		executeQuery_1(queryString, true, "explicit", "source", "data");		// query al modello esplicito

		//7
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property ?termine "
				+ "WHERE { plotino:" + getQuery_1_param_1()  
				+ " ?property ?termine . ?property rdf:type owl:DatatypeProperty . } ORDER BY ?property ";

		executeQuery_1(queryString, false, "model", "source", "data");		// query al modello 
		executeQuery_1(queryString, false, "explicit", "source", "data");		// query al modello esplicito

		// verifica quali sono le triple inferite
		for (OntoResult e : res) {
			if (inferred(e, 1)) {
				e.setInferita("yes");
			}
		}

		createDynamicColumns(columnTemplate_1_relazione);

		/*
			String queryString = Consts.NAMESPACES
					+ "SELECT DISTINCT ?property ?termine "
					+ "WHERE { plotino:"
					+ getQuery_1_param_1()
					+ " ?property ?termine . ?property rdf:type owl:"
					+ getQuery_1_param_1() + " . }" + "ORDER BY ?property ";

			// query al modello 
			executeQuery_1(queryString, "model", "source", "data");

			// query al modello esplicito
			executeQuery_1(queryString, "explicit", "source", "data");

			// verifica quali sono le triple inferite
			for (OntoResult e : res) {
				if (inferred(e, 1)) {
					e.setInferita("yes");
				}
			}
			createDynamicColumns(columnTemplate_1_tratto);
		 */
	}

	public void runQuestion_2() throws IOException {
		logger.info("runQuestion_2() "+ getQuery_2_param_1());

		res.clear();
		clearRes.clear();
		//		columns.clear();

		// query_2_param_1 PROP query_2_param_2
		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_1() + " ?property plotino:" + getQuery_2_param_2()
				+ " . } ORDER BY ?property ";

		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_1(), getQuery_2_param_2());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_1(), getQuery_2_param_2());

		// getQuery_2_param_2() PROP getQuery_2_param_1()
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_2() + " ?property plotino:" + getQuery_2_param_1()
				+ " . } ORDER BY ?property ";


		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_2(), getQuery_2_param_1());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_2(), getQuery_2_param_1());

		// getQuery_2_param_1() PROP query_2_param_3
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_1() + " ?property plotino:" + getQuery_2_param_3()
				+ " . } ORDER BY ?property ";

		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_1(), getQuery_2_param_3());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_1(), getQuery_2_param_3());

		// getQuery_2_param_3() PROP getQuery_2_param_1()
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_3() + " ?property plotino:" + getQuery_2_param_1()
				+ " . } ORDER BY ?property ";

		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_3(), getQuery_2_param_1());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_3(), getQuery_2_param_1());

		// getQuery_2_param_2() PROP getQuery_2_param_3()
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_2() + " ?property plotino:" + getQuery_2_param_3()
				+ " . } ORDER BY ?property ";

		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_2(), getQuery_2_param_3());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_2(), getQuery_2_param_3());

		// getQuery_2_param_3() PROP getQuery_2_param_2()
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { plotino:" + getQuery_2_param_3() + " ?property plotino:" + getQuery_2_param_2()
				+ " . } ORDER BY ?property ";

		// query al modello 
		executeQuery_2(queryString, "model", getQuery_2_param_3(), getQuery_2_param_2());

		// query al modello esplicito
		executeQuery_2(queryString, "explicit", getQuery_2_param_3(), getQuery_2_param_2());

		// verifica quali sono le triple inferite
		for (OntoResult e : res) {
			if (inferred(e, 2)) {
				e.setInferita("yes");
			}
		}

		createDynamicColumns(columnTemplate_1_relazione);

	}

	public void runQuestion_3() throws IOException {
		logger.info("runQuestion_3()");

		res.clear();
		clearRes.clear();
		//		columns.clear();
		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?termine "
				+ "WHERE { plotino:"
				+ getQuery_3_param_1()
				+ " plotino:"
				+ getQuery_3_param_2()
				+ " ?termine . } " + "ORDER BY ?termine ";

		// query al modello 
		executeQuery_3(queryString, "model");

		// query al modello esplicito
		executeQuery_3(queryString, "explicit");

		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?termine "
				+ "WHERE { ?termine plotino:"
				+ getQuery_3_param_2()
				+ " plotino:"
				+ getQuery_3_param_1() + ".  } " + "ORDER BY ?termine ";

		// query al modello 
		executeQuery_3(queryString, "model");

		// query al modello esplicito
		executeQuery_3(queryString, "explicit");

		// setta le triple inferite
		for (OntoResult e : res) {
			if (inferred(e, 3)) {
				e.setInferita("yes");
			}
		}
		createDynamicColumns(columnTemplate_3);

	}

	public List<OntoResult> getResults() {
		logger.info("getResults() " + res);

		return res;
	}

	/**
	 * @return the selectedTableRow
	 */
	public OntoResult getSelectedTableRow() {
		return selectedTableRow;
	}

	/**
	 * @param selectedTableRow the selectedTableRow to set
	 */
	public void setSelectedTableRow(OntoResult selectedTableRow) {
		this.selectedTableRow = selectedTableRow;
	}
}

package it.cnr.ilc.cophi.utils;

import it.cnr.ilc.cophi.onto.model.OntoResult;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class OntoUtils {


	public static String runQuery(String selectedLemmaGR, String selectedRelType, String selectedLemmaEN, OntModel ontoModel, OntModel ontoExplicitModel) {
		String value, prop;
		String nodes = selectedLemmaGR + "*", edges = "";
		List<OntoResult> ontoRes = new ArrayList<OntoResult>();
		List<OntoResult> ontoExplicitRes = new ArrayList<OntoResult>();

		String result = "";

		//		String queryString = Consts.NAMESPACES
		//				+ "SELECT DISTINCT ?prop ?value "
		//				+ "WHERE { plotino:" + selectedLemma + " ?prop ?value . }";
		String queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?prop ?value "
				+ "WHERE { ?inst plotino:greekTerm '" + selectedLemmaGR + "' . ?inst ?prop ?value . }";

		Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query,
				ontoModel);

		QueryExecution qe2 = QueryExecutionFactory.create(query,
				ontoExplicitModel);
		// verifica delle triple inferite tramite interrogazione al modello semplice

		for (ResultSet rs2 = qe2.execSelect(); rs2.hasNext();) {
			QuerySolution binding = rs2.nextSolution();
			prop = rename(binding.get("prop").toString().split("#")[1]);
			value = binding.get("value").toString().contains("#") ? binding.get("value").toString().split("#")[1] : binding.get("value").toString();

			if (allowedTriple(prop, value)) {
				OntoResult cleanOntoRes = new OntoResult();
				cleanOntoRes.setTermine(selectedLemmaGR);
				cleanOntoRes.setRelazione(prop);
				if (value.equals(selectedLemmaGR)) {
					cleanOntoRes.setTermine_target(selectedLemmaEN);
				} else {
					cleanOntoRes.setTermine_target(value);
				}
				ontoExplicitRes.add(cleanOntoRes);
				if ((selectedRelType.equals("clean")) || (selectedRelType.equals("all"))) {
					nodes = nodes + (value.equals(selectedLemmaGR)?selectedLemmaEN:value) + "*";
					edges = edges + prop + "*";
				}
			}
		}
		if (selectedRelType.equals("clean")) {
			nodes = nodes.substring(0, nodes.lastIndexOf("*"));
			edges = edges.substring(0, edges.lastIndexOf("*"));
			result = nodes + "-" + edges;
		}
		qe2.close();
		System.out.println("CLEAN SIZE: " + ontoExplicitRes.size());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			prop = rename(binding.get("prop").toString().split("#")[1]);
			value = binding.get("value").toString().contains("#") ? binding.get("value").toString().split("#")[1] : binding.get("value").toString();

			if (allowedTriple(prop, value)) {
				OntoResult inferredOntoRes = new OntoResult();
				inferredOntoRes.setTermine(selectedLemmaGR);
				inferredOntoRes.setRelazione(rename(prop));
				if (value.equals(selectedLemmaGR)) {
					inferredOntoRes.setTermine_target(selectedLemmaEN);
				} else {
					inferredOntoRes.setTermine_target(value);
				}
				ontoRes.add(inferredOntoRes);
			}
		}
		qe.close();
		// setta le triple inferite ed il loro ID
		int id = 1;
		for (OntoResult e : ontoRes) {
			e.setID(id++);
			if (inferred(e, ontoExplicitRes)) {
				e.setInferita("si");
				if ((selectedRelType.equals("inf")) || (selectedRelType.equals("all"))) {
					nodes = nodes + e.getTermine_target() + "*";
					edges = edges + e.getRelazione() + "*";
				}
			}
		}
		if ((selectedRelType.equals("inf")) || (selectedRelType.equals("all"))) {
			nodes = nodes.substring(0, nodes.lastIndexOf("*"));
			edges = edges.substring(0, edges.lastIndexOf("*")==-1?0:edges.lastIndexOf("*"));
			result = nodes + "-" + edges;
		}

		return result;
	}

	private static String rename (String s) {
		String ret = null;

		if (null != s) {
			ret = s.replace("greekTerm", "englishTerm");
		}

		return ret;

	}

	private static boolean inferred(OntoResult e, List<OntoResult> lor) {
		for (OntoResult or : lor) {
			if (or.getRelazione().equals(e.getRelazione()) && (or.getTermine_target().equals(e.getTermine_target()))) {
				return false;
			}
		}
		return true;
	}

	private static boolean allowedTriple(String prop, String value) {
		if ((prop.equals("Ontological_Relation")) || (prop.contains("comment")) || (prop.contains("differentFrom"))
				|| (value.equals("NamedIndividual")) || (value.equals("Resource")) || (value.equals("Thing"))) {
			return false;
		} else {
			return true;
		}
	}


}

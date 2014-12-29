package it.cnr.ilc.cophi.utils;

import it.cnr.ilc.cophi.action.userbean.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuckwalterAnalysisParser {

	private static Pattern parser = Pattern.compile("([^\\+]+)");

	public static List<Pair<String, String>> parse (String analysis) {

		ArrayList<Pair<String, String>> results = new ArrayList<Pair<String,String>>();
		Matcher m = parser.matcher(analysis);

		while (m.find()) {
			//System.err.println(m.group());
			String[] submatch = m.group().split("=");
			Pair<String,String> subanalysis = new Pair<String, String>(submatch[0], submatch[1].replaceAll("\\*", "\n")); 
			results.add(subanalysis);
		}

		return results;
	}

	public static void main(String[] args) {
		
		BuckwalterAnalysisParser.parse("ta=IV2MS+Eolam=VERB_IMPERFECT+a=IVSUFF_MOOD:SJ");
	}

}

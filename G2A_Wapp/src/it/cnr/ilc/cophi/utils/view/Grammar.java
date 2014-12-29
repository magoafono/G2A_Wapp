package it.cnr.ilc.cophi.utils.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grammar implements GrammarInterface {
	private Pattern pattern;
	private String textGrammar = "";
	private List<String> tok = new ArrayList<String>();
	StringBuffer sb = new StringBuffer();
	List<Integer> groupIndex = new ArrayList<Integer>();

	@Override
	public String process(String html) {
		if (pattern == null) {
			pattern = Pattern.compile(textGrammar);
			Integer productionRef = 0;
			int level = 0;
			char prevChar = ' ';
			for (int j = 0; j < textGrammar.length(); j++) {
				if (prevChar != '\\')
					switch (textGrammar.charAt(j)) {
					case '|':
						if (level == 0)
							productionRef++;
						break;
					case '(':
						groupIndex.add(productionRef);
						level++;
						break;
					case ')':
						level--;
						break;
					}
				prevChar = textGrammar.charAt(j);
			}
		}
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			// System.out.println("*** MATCH TROVATO: -->" + matcher.group()
			// +
			// "<--");
			for (int i = 0; i < groupIndex.size(); i++) {
				// System.out.println("entrato");
				if (matcher.group(i + 1) != null) {
					Integer j = groupIndex.get(i);
					matcher.appendReplacement(sb, tok.get(j));
					break;
				}
			}
		}
		matcher.appendTail(sb);
		String fieldValue = sb.toString();
		sb.setLength(0);
		return fieldValue.trim();
	}

	@Override
	public void willReplace(String production, String out) {
		textGrammar += (textGrammar.length() == 0 ? "(" : "|(") + production + ")";
		// textGrammar += production;
		tok.add(out);
		pattern = null;
	}

	@Override
	public void willReplaceFirst(String production, String out) {
		if (textGrammar.length() == 0)
			textGrammar = "(" + production + ")";
		else
			textGrammar = "(" + production + ")|" + textGrammar;
		tok.add(0, out);
		pattern = null;
	}
}

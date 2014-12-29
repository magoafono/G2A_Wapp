package it.cnr.ilc.cophi.utils.view;

public interface GrammarInterface {
	public void willReplace(String production, String out);

	public void willReplaceFirst(String production, String out);

	public String process(String html);
}

package it.cnr.ilc.cophi.utils.view;

import java.util.List;

public interface BaseEditorServletInterface {
	public String getSpecialChars();

	public List<EditorMenuItem> getNotes();

	public List<EditorMenuItem> getReferences();

	public List<List<EditorMenuItem>> getQuotes();
}

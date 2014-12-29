package it.cnr.ilc.cophi.utils.view;

public abstract class EditorMenuItemImpl implements EditorMenuItem {
	protected String titleHtml;
	protected String descriptionHtml;
	protected String color;
	protected String styleClassName;
	protected boolean hidden;

	@Override
	public String getTitleHtml() {
		return titleHtml;
	}

	@Override
	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

	@Override
	public String getStyleClassName() {
		return styleClassName;
	}

}

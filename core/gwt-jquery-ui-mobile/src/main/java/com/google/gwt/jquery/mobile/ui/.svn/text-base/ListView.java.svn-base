package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.base.AbstractWidgetContainer;
import com.google.gwt.jquery.mobile.ui.face.HasInset;

public class ListView extends AbstractWidgetContainer implements HasInset {

	private boolean inset;

	public ListView() {
		this("ul");
	}

	protected ListView(String nodeName) {
		super(nodeName, "ui-listview", "listview", "listbox");
		setOrientation(ORIENTATION_VERTICAL);
	}

	public String getDefaultThemedStyleName() {
		return "ui-listview";
	}

	public void setInset(boolean inset) {
		this.inset = inset;
		onStaticStyleNameChange(false);
	}

	public boolean isInset() {
		return inset;
	}

	public String getInsetPrefix() {
		return "ui-listview";
	}
}
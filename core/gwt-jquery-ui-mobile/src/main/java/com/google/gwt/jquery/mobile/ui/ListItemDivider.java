package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.base.AbstractFocusWidget;

public class ListItemDivider extends AbstractFocusWidget {

	public ListItemDivider() {
		super("li", "ui-btn ui-li ui-li-divider");
	}
	
	public ListItemDivider(String text) {
		this();
		setDataTheme("b");
		getElement().setInnerText(text);
	}

	@Override
	public boolean isInteractive() {
		return false;
	}

	@Override
	public String getThemedStyleName() {
		return "ui-bar";
	}

	public String getDefaultThemedStyleName() {
		return "ui-bar";
	}
}
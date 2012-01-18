package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.base.AbstractFlowPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;


public class Content extends AbstractFlowPanel {

	private Element formElement;

	public Content() {
		super("ui-content");
		getElement().appendChild(this.formElement = DOM.createForm());
	}

	public String getDefaultThemedStyleName() {
		return null;
	}

	@Override
	protected void add(Widget child, Element container) {
	    super.add(child, formElement);
	}
}
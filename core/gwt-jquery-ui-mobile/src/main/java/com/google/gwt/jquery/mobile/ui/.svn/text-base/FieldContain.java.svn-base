package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;

public class FieldContain extends HTMLPanel {

	private boolean validated = false;

	public FieldContain(String tag, String html) {
		super(tag, html);
		addStyleName("ui-field-contain ui-body ui-br");
	}

	public FieldContain(String html) {
		super(html);
		addStyleName("ui-field-contain ui-body ui-br");
	}

	@Override
	protected void onAttach() {
		// we'll assume the first thing is the label and the second is the value
		if (getWidgetCount() >= 2) {
			if (getWidget(1) instanceof HasText) {
				getWidget(0).addStyleName("ui-input-text");
			}
			String id = getWidget(1).getElement().getId();
			if (null == id) {
				id = DOM.createUniqueId();
				getWidget(1).getElement().setId(id);
				getWidget(0).getElement().setAttribute("for", id);
			}
		}
		super.onAttach();
	}
}
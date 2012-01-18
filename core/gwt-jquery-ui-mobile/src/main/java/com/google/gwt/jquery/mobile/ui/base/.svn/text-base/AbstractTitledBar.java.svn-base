package com.google.gwt.jquery.mobile.ui.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public abstract class AbstractTitledBar extends AbstractBar {

	private Element textElement;

	public AbstractTitledBar(String text, String cssClass, String dataRole, String role) {
		super(cssClass, dataRole, role);
		if (null != text) setText(text);
	}

	public void setText(String text) {
		if (null == textElement) {
			textElement = DOM.createElement(getTextNodeName());
			getElement().insertFirst(textElement);
			textElement.setAttribute("class", "ui-title");
		}
		textElement.setInnerText(text);
	}
	
	protected abstract String getTextNodeName();
}

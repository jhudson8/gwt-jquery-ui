package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.dom.client.Element;


public class TelTextBox extends TextBox {

	public TelTextBox() {
		super();
		getElement().setAttribute("type", "tel");
	}

	protected TelTextBox(Element element) {
		super(element);
		element.setAttribute("type", "tel");
	}	
}
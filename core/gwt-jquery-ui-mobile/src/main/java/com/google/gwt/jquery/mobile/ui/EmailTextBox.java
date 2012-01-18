package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.dom.client.Element;


public class EmailTextBox extends TextBox {

	public EmailTextBox() {
		super();
		getElement().setAttribute("type", "email");
	}

	protected EmailTextBox(Element element) {
		super(element);
		element.setAttribute("type", "email");
	}	
}
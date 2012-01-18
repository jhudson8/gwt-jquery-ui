package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;


public class RadioButton extends AbstractCheckableOption {

	public RadioButton() {
		super(null, false);
	}

	public RadioButton(String value, boolean checked) {
		super(value, checked);
	}
	
	@Override
	protected String getElementClassName() {
		return "ui-radio";
	}
	
	@Override
	protected String getUiDomain() {
		return "radio";
	}

	@Override
	protected Element createCheckableElement(String optionName) {
		return DOM.createInputRadio(optionName);
	}
}
package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;


public class CheckButton extends AbstractCheckableOption {

	public CheckButton() {
		super(null, false);
	}

	public CheckButton(String value, boolean checked) {
		super(value, checked);
	}
	
	@Override
	protected String getElementClassName() {
		return "ui-checkbox";
	}
	
	@Override
	protected String getUiDomain() {
		return "checkbox";
	}

	@Override
	protected Element createCheckableElement(String optionName) {
		Element rtn = DOM.createInputCheck();
		if (null != optionName)
			rtn.setAttribute("name", optionName);
		return rtn;
	}
}
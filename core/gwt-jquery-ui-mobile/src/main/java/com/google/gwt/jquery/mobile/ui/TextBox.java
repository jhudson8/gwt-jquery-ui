package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

public class TextBox extends com.google.gwt.user.client.ui.TextBox implements HasDataTheme {

	private String dataTheme;

	public TextBox() {
		super();
		init();
		sinkEvents(Event.ONFOCUS | Event.ONBLUR);
	}

	protected TextBox(Element element) {
		super(element);
		init();
	}

	protected void init() {
		setStyleName("ui-input-text ui-corner-all ui-shadow-inset");
	}

	public void setDataTheme(String dataTheme) {
		if (null != this.dataTheme)
			removeStyleName("ui-body-" + dataTheme);
		this.dataTheme = dataTheme;
		if (null != this.dataTheme)
			addStyleName("ui-body-" + dataTheme);
	}

	public String getDataTheme() {
		return dataTheme;
	}

	public String getDefaultThemedStyleName() {
		return null;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		JQueryMobile.initWidget(this);
	}

	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);
		switch (DOM.eventGetType(event)) {
		case Event.ONFOCUS:
			addStyleName("ui-focus");
			break;
		case Event.ONBLUR:
			removeStyleName("ui-focus");
			break;
		}
	}
}
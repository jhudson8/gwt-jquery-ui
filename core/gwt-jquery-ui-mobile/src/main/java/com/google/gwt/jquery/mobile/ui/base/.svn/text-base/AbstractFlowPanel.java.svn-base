package com.google.gwt.jquery.mobile.ui.base;

import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.ui.FlowPanel;

public abstract class AbstractFlowPanel extends FlowPanel implements HasDataTheme {

	private boolean initialized = false;
	private String dataTheme = null;
	private String defaultClass = null;

	public AbstractFlowPanel(String cssClass) {
		this.defaultClass = cssClass;
		onStaticStyleNameChange(false);
	}

	protected String getDefaultStyleName() {
		return defaultClass;
	}

	@Override
	public void addStyleName(String style) {
		super.addStyleName(style);
		defaultClass = CSSUtils.addClassName(defaultClass, style);
	}

	@Override
	public void removeStyleName(String style) {
		super.removeStyleName(style);
		defaultClass = CSSUtils.removeClassName(defaultClass, style);
	}

	protected void onStaticStyleNameChange(boolean force) {
		if (isAttached() || force) {
			CSSUtils.setStyleName(getDefaultStyleName(), this);
		}
	}

	protected String getThemedStyle() {
		return null;
	}

	public void setDataTheme(String dataTheme) {
		this.dataTheme = dataTheme;
		onStaticStyleNameChange(false);
	}

	public String getDataTheme() {
		return dataTheme;
	}

	@Override
	protected void onAttach() {
		if (!initialized) {
			JQueryMobile.initWidget(this);
			initialized = true;
		}
		onStaticStyleNameChange(true);
		super.onAttach();
	}
}
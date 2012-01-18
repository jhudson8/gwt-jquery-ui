package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class Grid extends FlowPanel {

	private static int max = 5;
	private String className;
	private String additionalClassName;

	@Override
	public void add(Widget w) {
		super.add(w);
		updateItems(false);
	}

	@Override
	public boolean remove(int index) {
		boolean rtn = super.remove(index);
		updateItems(false);
		return rtn;
	}

	@Override
	public boolean remove(Widget w) {
		boolean rtn = super.remove(w);
		updateItems(false);
		return rtn;
	}

	@Override
	public void addStyleName(String style) {
		super.addStyleName(style);
		additionalClassName =  CSSUtils.addClassName(additionalClassName, style);
	}

	@Override
	public void removeStyleName(String style) {
		super.removeStyleName(style);
		additionalClassName =  CSSUtils.removeClassName(additionalClassName, style);
	}

	@Override
	protected void onAttach() {
		updateItems(true);
		super.onAttach();
	}

	private void updateItems(boolean force) {
		if (force || isAttached()) {
			int count = getWidgetCount();
			className = "ui-grid-" + toVal(Math.max(0, count-2));
			StringBuilder sb = new StringBuilder();
			sb.append(className);
			if (null != additionalClassName && additionalClassName.length() > 0)
				sb.append(' ').append(additionalClassName);
			getElement().setAttribute("class", sb.toString());

			int _max = Math.min(count, max);
			for (int i=0; i<count; i++) {
				Widget widget = getWidget(i);
				int x = i % _max;
				widget.getElement().setAttribute("class", "ui-block-" + toVal(x));
			}
		}
	}

	private String toVal(int val) {
		if (val == 0) return "a";
		else if (val == 1) return "b";
		else if (val == 2) return "c";
		else return "d";
	}
}
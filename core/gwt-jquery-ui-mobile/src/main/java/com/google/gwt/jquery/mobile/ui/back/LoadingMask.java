package com.google.gwt.jquery.mobile.ui.back;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ScrollStopEvent;
import com.google.gwt.event.logical.shared.ScrollStopHandler;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoadingMask extends Widget implements ScrollHandler, ScrollStopHandler {

	private static LoadingMask INSTANCE = null;
	private static final int Z_INDEX = 100;
	
	private boolean shown = false;
	private boolean scrolling = false;
	private int top = 100;
	
	private Element textEl;

	public static LoadingMask get() {
		if (null == INSTANCE) {
			INSTANCE = new LoadingMask();
		}
		return INSTANCE;
	}

	private LoadingMask () {
		Element element = DOM.createDiv();
		element.setClassName("ui-loader ui-body-a ui-corner-all");
		setElement(element);
		element = DOM.createSpan();
		element.setClassName("ui-icon ui-icon-loading spin");
		getElement().appendChild(element);
		textEl = DOM.createElement("h1");
		getElement().appendChild(textEl);
		RootPanel.get().add(this);
		Window.addWindowScrollHandler(this);
		JQueryMobile.addScrollStopHandler(this);
		adjust();
	}

	private boolean adjust() {
		int height = getElement().getClientHeight();
		int documentHeight = Window.getClientHeight();
		int _top = Math.max((documentHeight - height) / 2, 100);
		if (_top != top) {
			this.top = _top;
			return true;
		}
		return false;
	}

	public void show(boolean adjust, String text) {
		textEl.setInnerText(text);
		_show(adjust);
		shown = true;
	}

	private void _show(boolean adjust) {
		if (!scrolling) {
			getElement().setAttribute("style", "top: " + (getScrollTop() + top) + "px; display: block; z-index: " + Z_INDEX);
			if (adjust) {
				if (adjust()) _show(false);
			}
		}
	}

	public void hide() {
		getElement().setAttribute("style", "display: none;");
		shown = false;
	}

	public void onWindowScroll(ScrollEvent event) {
		if (shown && !scrolling) {
			getElement().setAttribute("style", "display: none;");
		}
		scrolling = true;
	}

	public void onScrollStop(ScrollStopEvent event) {
		scrolling = false;
		if (shown)
			_show(true);
	}

	private int getScrollTop() {
		int scrollTop = RootPanel.getBodyElement().getScrollTop();
		if (scrollTop == 0) scrollTop = Window.getScrollTop();
		return scrollTop;
	}
}
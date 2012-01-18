package com.google.gwt.jquery.mobile.ui.util;

import java.util.Date;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ScrollStopEvent;
import com.google.gwt.event.logical.shared.ScrollStopHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.back.LoadingMask;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class JQueryMobileImpl implements ResizeHandler, ScrollHandler {

	private String PORTRAIT = "portrait";
	private String LANDSCAPE = "landscape";

	private boolean initialized = false;
	private String layoutType;
	private boolean scrolling = false;
	private Timer timer;
	private long lastKnownScroll = -1;
	
	private HandlerManager handlerManager = new HandlerManager(this);

	public void init() {
		if (!initialized) {
			Document
					.get()
					.getDocumentElement()
					.addClassName(
							"ui-mobile min-width-320px min-width-480px min-width-768px min-width-1024px");
			Window.addResizeHandler(this);
			Window.addWindowScrollHandler(this);
			initialized = true;
		}
	}

	public void showLoadingMask() {
		this.showLoadingMask("Loading...");
	}

	public void showLoadingMask(String text) {
		LoadingMask.get().show(true, text);
	}

	public void hideLoadingMask() {
		LoadingMask.get().hide();
	}

	public void onResize(ResizeEvent event) {
		adjust(event.getWidth(), event.getHeight());
	}

	private void adjust(int width, int height) {
		if (height > width) {
			if (null == layoutType || layoutType.equals(LANDSCAPE)) {
				Document.get().getDocumentElement().removeClassName(
						LANDSCAPE);
				Document.get().getDocumentElement().addClassName(PORTRAIT);
			}
		} else {
			if (null == layoutType || layoutType.equals(PORTRAIT)) {
				Document.get().getDocumentElement().removeClassName(
						PORTRAIT);
				Document.get().getDocumentElement().addClassName(LANDSCAPE);
			}
		}
	}

	public void onWindowScroll(ScrollEvent event) {
		lastKnownScroll = new Date().getTime();
		scrolling = true;
		if (null == timer) {
			timer = new Timer() {
				
				@Override
				public void run() {
					maybeStopTimer();
				}
			};
			timer.schedule(250);
		}
		else {
			timer.schedule(250);
		}
	}

	private void maybeStopTimer() {
		if (new Date().getTime() - lastKnownScroll >= 250) {
			scrolling = false;
			timer = null;
			lastKnownScroll = -1;
			handlerManager.fireEvent(new ScrollStopEvent());
		}
	}

	public void initWidget(Widget w) {
		if (w instanceof HasDataTheme) {
			String theme = ((HasDataTheme) w).getDataTheme();
			if (null == theme) {
				if (w instanceof HasText) {
					((HasDataTheme) w).setDataTheme("c");
				}
				else {
					// check the parents
					Widget parent = w.getParent();
					while (null != parent && null == theme) {
						if (parent instanceof HasDataTheme)
							theme = ((HasDataTheme) parent).getDataTheme();
						parent = parent.getParent();
					}
					if (null == theme)
						theme = "c";
					((HasDataTheme) w).setDataTheme(theme);
				}
			}
		}
	}

	public HandlerRegistration addScrollStopHandler(ScrollStopHandler handler) {
		return handlerManager.addHandler(ScrollStopEvent.getType(), handler);
	}

	public native boolean supportsMobileEvents() /*-{
	if (!$wnd._jqm_mobile) {
		var ua = navigator.userAgent.toLowerCase();
		if (ua.indexOf("webkit") != -1) {
			$wnd._jqm_mobile = "yes";
		}
		else {
			$wnd._jqm_mobile = "no";
		}
	}
	return ($wnd._jqm_mobile == "yes");
}-*/;
}

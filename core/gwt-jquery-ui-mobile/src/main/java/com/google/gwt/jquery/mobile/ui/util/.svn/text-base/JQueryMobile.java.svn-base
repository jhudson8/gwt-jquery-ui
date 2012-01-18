package com.google.gwt.jquery.mobile.ui.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ScrollStopHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class JQueryMobile {

	private static JQueryMobileImpl impl = GWT.create(JQueryMobileImpl.class);

	public static void init() {
		impl.init();
	}

	public static void initWidget(Widget w) {
		impl.initWidget(w);
	}

	public static void showLoadingMask() {
		impl.showLoadingMask();
	}

	public static void showLoadingMask(String loadingText) {
		impl.showLoadingMask(loadingText);
	}

	public static void hideLoadingMask() {
		impl.hideLoadingMask();
	}

	public static boolean supportsMobileEvents() {
		return impl.supportsMobileEvents();
	}

	public static HandlerRegistration addScrollStopHandler(ScrollStopHandler handler) {
		return impl.addScrollStopHandler(handler);
	}
}
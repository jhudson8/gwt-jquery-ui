package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.event.logical.shared.ScrollStopEvent;
import com.google.gwt.event.logical.shared.ScrollStopHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.base.AbstractTitledBar;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class FooterBar extends AbstractTitledBar implements ScrollHandler, ScrollStopHandler {


	public FooterBar () {
		this(null);
	}
	
	public FooterBar(String text) {
		super(text, "ui-footer", "footer", "banner");
	}

	@Override
	protected String getTextNodeName() {
		return "h4";
	}

	private boolean scrolling;
	private boolean isFixed;
	private HandlerRegistration scrollReg;
	private HandlerRegistration scrollStopReg;

	

	@Override
	protected void onAttach() {
		super.onAttach();
		if (isFixed && null == scrollReg) {
			scrollReg = Window.addWindowScrollHandler(this);
			scrollStopReg = JQueryMobile.addScrollStopHandler(this);
			onScrollStop(null);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		if (null != scrollReg) {
			scrollReg.removeHandler();
			scrollReg = null;
			scrollStopReg.removeHandler();
			scrollStopReg = null;
		}
	}
	
	public void setFixed(boolean fixed) {
		this.isFixed = fixed;
		if (fixed) {
			addStyleName("ui-footer-fixed fade ui-fixed-overlay");
			if (null == scrollReg) {
				scrollReg = Window.addWindowScrollHandler(this);
				scrollStopReg = JQueryMobile.addScrollStopHandler(this);
			}
		}
		else {
			removeStyleName("ui-footer-fixed fade ui-fixed-overlay");
			getElement().removeAttribute("style");
			if (null != scrollReg) {
				scrollReg.removeHandler();
				scrollReg = null;
				scrollStopReg.removeHandler();
				scrollStopReg = null;
			}
		}
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void onWindowScroll(ScrollEvent event) {
		if (!scrolling) {
			getElement().setAttribute("style", "display: block;");
			scrolling = true;
		}
	}

	public void onScrollStop(ScrollStopEvent event) {
		getElement().setAttribute("style", "top: " + getScrollTop() + "px");
		scrolling = false;
	}

	private int getScrollTop() {
		int scrollTop = RootPanel.getBodyElement().getScrollTop();
		if (scrollTop == 0) scrollTop = Window.getScrollTop();
		return (scrollTop + Window.getClientHeight()) - getElement().getParentElement().getClientHeight();
	}
 }

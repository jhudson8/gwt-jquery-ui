package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.Widget;

public class ListItemLink extends FocusWidget {

	private FocusWidget inner;
	private Anchor anchor;
	
	public ListItemLink() {
//		super("div", "ui-btn-inner", null, null);
//		add(inner = new AbstractSimpleWidget("div", "ui-btn-text", null, null) {
//		});
		InlineLabel lbl = new InlineLabel();
		lbl.setStyleName("ui-icon ui-icon-arrow-r");
//		add(lbl);
//		inner.add(this.anchor = new Anchor());
		anchor.setStyleName("ui-link-inherit");
	}
	
	public void setText(String text) {
		this.anchor.setText(text);
	}
	
	public void setHref(String href) {
		this.anchor.setHref(href);
	}
	
	public Anchor getAnchor() {
		return this.anchor;
	}
	
	
	
	@Override
	public void addMouseListener(MouseListener listener) {
//		return inner.addMouseListener(listener);
	}

	@Override
	public void removeMouseListener(MouseListener listener) {
		inner.removeMouseListener(listener);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return inner.addBlurHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return inner.addMouseDownHandler(handler);
	}

	@Override
	public void addClickListener(ClickListener listener) {
//		return inner.addClickListener(listener);
	}

	@Override
	public void removeClickListener(ClickListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addKeyboardListener(KeyboardListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeKeyboardListener(KeyboardListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFocusListener(FocusListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTabIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAccessKey(char key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus(boolean focused) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTabIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}



	private class Inner extends FocusWidget {
		
	}
}
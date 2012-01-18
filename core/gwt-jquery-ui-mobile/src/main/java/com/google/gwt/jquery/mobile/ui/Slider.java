package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.NativeTouchEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.TouchEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.base.AbstractWidget;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class Slider extends AbstractWidget implements HasDataTheme,
		HasValueChangeHandlers<Integer>, ResizeHandler {

	private int leftPadding = -1;
	private int width = -1;
	private int handleWidth = -1;
	private boolean mouseDown = false;

	private int value = 0;
	private int minValue = -1;
	private int maxValue = -1;

	private Element wrapper;
	private Element handle;
	private Element buttonInner;

	private HandlerRegistration reg;

	public Slider() {
		this(1, 100, 50);
	}

	public Slider(int min, int max, int value) {
		super("div", "ui-slider ui-btn-corner-all");

		wrapper = DOM.createDiv();
		getElement().appendChild(wrapper);
		
		handle = DOM.createAnchor();
		handle.setClassName("ui-slider-handle ui-btn ui-btn-corner-all ui-shadow");
		wrapper.appendChild(handle);

		buttonInner = DOM.createSpan();
		buttonInner.setClassName("ui-btn-inner ui-btn-corner-all");
		handle.appendChild(buttonInner);
		Element buttonInnerText = DOM.createSpan();
		buttonInnerText.setClassName("ui-btn-text");
		buttonInner.appendChild(buttonInnerText);

		if (JQueryMobile.supportsMobileEvents()) {
			sinkEvents(Event.MOUSEEVENTS | TouchEvent.ONTOUCHSTART | TouchEvent.ONTOUCHMOVE);
		}
		else {
			sinkEvents(Event.MOUSEEVENTS);
		}

		if (min >= 0)
			setMin(min);
		if (max > 0)
			setMax(max);
		if (value > 0)
		setValue(value, false);
	}

	public void setMin(int min) {
		this.minValue = min;
		setValue(value, false);
	}

	public void setMax(int max) {
		this.maxValue = max;
		setValue(value, false);
	}

	public void setValue(int value) {
		setValue(value, true);
	}

	public void setValue(int value, boolean fireEvent) {
		float pct = ((float) (value - minValue)) / (float) (maxValue - minValue);
		int _pct = (int) (pct * 100f);
		setValue(value, _pct, fireEvent);
	}

	private void setValue(int value, int pct, boolean fireEvent) {
		if (pct < 0) pct = 0;
		else if (pct > 100) pct = 100;
		if (fireEvent) {
			if (value < minValue) value = minValue;
			else if (value > maxValue) value = maxValue;
		}
		this.value = value;
		handle.setAttribute("style", "left: " + pct + "%");
		if (fireEvent)
			ValueChangeEvent.fire(this, value);
	}

	@Override
	public void setDataTheme(String dataTheme) {
		if (null != getDataTheme()) {
			handle.removeClassName("ui-btn-up-" + getDataTheme());
		}
		super.setDataTheme(dataTheme);
		handle.addClassName("ui-btn-up-" + dataTheme);
	}

	public String getDefaultThemedStyleName() {
		return "ui-btn-down";
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (!event.getCurrentEventTarget().cast().equals(getElement())) {
			super.onBrowserEvent(event);
			return;
		}
		if (width == -1) {
			determineSizes();
		}

		boolean mouseEvent = false;
		int type = DOM.eventGetType(event);
		switch (type) {
			case Event.ONMOUSEDOWN:
				mouseDown = true;
			case Event.ONMOUSEMOVE:
				mouseEvent = true;
			case TouchEvent.ONTOUCHSTART:
			case TouchEvent.ONTOUCHMOVE:
				if (!mouseEvent || mouseDown) {
					int xPos = xPos(event, type);
					handleDrag(xPos);
				}
				break;
			case Event.ONMOUSEUP:
				mouseDown = false;
		}
		event.stopPropagation();
	}

	private void determineSizes() {
		width = wrapper.getClientWidth();
		handleWidth = handle.getClientWidth();
		leftPadding = wrapper.getAbsoluteLeft();
	}

	private int xPos(Event event, int type) {
		if (type == TouchEvent.ONTOUCHCANCEL || type == TouchEvent.ONTOUCHEND
				|| type == TouchEvent.ONTOUCHMOVE
				|| type == TouchEvent.ONTOUCHSTART) {
			int x = ((NativeTouchEvent) event.cast()).getSingleTouch().pageX();
			return x - leftPadding;
		}
		else {
			int x = event.getClientX();
			return x - leftPadding;
		}
	}

	private void handleDrag(int x) {
		float pct = ((float) x) / ((float) width);
		int value = ((int) (pct * (maxValue - minValue))) + minValue;
		int _pct = (int) (pct * 100);
		setValue(value, _pct, true);
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Integer> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (null == reg)
			reg = Window.addResizeHandler(this);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		if (null != reg) {
			reg.removeHandler();
			this.reg = null;
		}
	}

	public void onResize(ResizeEvent event) {
		width = -1;
	}
}
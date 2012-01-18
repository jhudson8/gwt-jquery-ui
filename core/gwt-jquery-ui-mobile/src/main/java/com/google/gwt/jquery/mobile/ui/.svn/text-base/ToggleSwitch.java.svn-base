package com.google.gwt.jquery.mobile.ui;

import java.util.Date;

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

public class ToggleSwitch extends AbstractWidget implements HasDataTheme,
		HasValueChangeHandlers<Boolean>, ResizeHandler {

	private static int startDragPx = 3;
	// keep from accidental double-click
	private static int stopDelay = 500;

	private long lastSwitch = -1;
	private String switchMode;
	private int width = -1;
	private float handleWidth = -1;
	private int mouseDownPos = -1;
	private int dragPos = -1;
	private boolean value = false;
	private boolean dragging = false;
	private int currentPct = 0;

	private Element handle;
	private Element buttonInner;
	private Element off;
	private Element on;
	private Element labelRight;

	private HandlerRegistration reg;

	public ToggleSwitch() {
		this("On", "Off", false);
	}

	public ToggleSwitch(String onLabel, String offLabel, boolean value) {
		super("div", "ui-slider ui-slider-switch ui-btn-corner-all");
		getElement().setId("sliderSwitch");
		Element labelLeft = DOM.createDiv();
		labelLeft
				.setClassName("ui-slider-labelbg ui-slider-labelbg-a ui-btn-active ui-btn-corner-left");
		getElement().appendChild(labelLeft);
		labelRight = DOM.createDiv();
		labelRight
				.setClassName("ui-slider-labelbg ui-slider-labelbg-b ui-btn-corner-right");
		getElement().appendChild(labelRight);
		Element inner = DOM.createDiv();
		inner.setClassName("ui-slider-inneroffset");
		getElement().appendChild(inner);
		handle = DOM.createAnchor();
		handle
				.setClassName("ui-slider-handle ui-btn ui-btn-corner-all ui-shadow");
		inner.appendChild(handle);
		on = DOM.createSpan();
		on
				.setClassName("ui-slider-label ui-slider-label-a ui-btn-active ui-btn-corner-left");
		on.setInnerText(onLabel);
		handle.appendChild(on);
		off = DOM.createSpan();
		off
				.setClassName("ui-slider-label ui-slider-label-b ui-btn-corner-right");
		off.setInnerText(offLabel);
		handle.appendChild(off);
		buttonInner = DOM.createSpan();
		buttonInner.setClassName("ui-btn-inner ui-btn-corner-all");
		handle.appendChild(buttonInner);
		Element buttonInnerText = DOM.createSpan();
		buttonInnerText.setClassName("ui-btn-text");
		buttonInner.appendChild(buttonInnerText);

		if (JQueryMobile.supportsMobileEvents()) {
			sinkEvents(Event.MOUSEEVENTS | TouchEvent.TOUCHEVENTS);
		}
		else {
			sinkEvents(Event.MOUSEEVENTS);
		}

		if (value)
			turnOn(false);
		else
			turnOff(false);
	}

	public void setLabels(String on, String off) {
		if (null != on)
			this.on.setInnerText(on);
		if (null != off)
			this.off.setInnerText(off);
		this.width = -1;
		this.handleWidth = -1;
	}

	@Override
	public void setDataTheme(String dataTheme) {
		if (null != getDataTheme()) {
			handle.removeClassName("ui-btn-up-" + getDataTheme());
			off.removeClassName("ui-btn-down-" + getDataTheme());
			labelRight.removeClassName("ui-btn-down-" + getDataTheme());
			removeStyleName("ui-btn-down-" + getDataTheme());
		}
		super.setDataTheme(dataTheme);
		handle.addClassName("ui-btn-up-" + dataTheme);
		off.addClassName("ui-btn-down-" + dataTheme);
		labelRight.addClassName("ui-btn-down-" + dataTheme);
		addStyleName("ui-btn-down-" + dataTheme);
	}

	public void setValue(boolean value) {
		setValue(value, true);
	}

	public void setValue(boolean value, boolean fireEvent) {
		if (value)
			turnOn(fireEvent);
		else
			turnOff(fireEvent);
	}

	private void turnOn(boolean fireEvent) {
		if (!value) {
			value = true;
			if (fireEvent)
				ValueChangeEvent.fire(this, value);
		}
		removeStyleName("ui-slider-switch-a");
		addStyleName("ui-slider-switch-b");
		handle.setAttribute("style", "left: 100%");
		currentPct = 100;
		mouseDownPos = -1;
		dragPos = -1;
		dragging = false;
		switchMode = "b";
		lastSwitch = new Date().getTime();
	}

	private void turnOff(boolean fireEvent) {
		if (value) {
			value = false;
			if (fireEvent)
				ValueChangeEvent.fire(this, value);
		}
		removeStyleName("ui-slider-switch-b");
		addStyleName("ui-slider-switch-a");
		handle.setAttribute("style", "left: 0%");
		value = false;
		currentPct = 0;
		mouseDownPos = -1;
		dragPos = -1;
		dragging = false;
		switchMode = "a";
		lastSwitch = new Date().getTime();
	}

	private void resetDragState() {
		
	}

	public void toggle(boolean fireEvent) {
		if (value)
			turnOff(fireEvent);
		else
			turnOn(fireEvent);
	}

	public String getDefaultThemedStyleName() {
		return null;
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (!event.getCurrentEventTarget().equals(getElement()))
			return;
		if (width == -1) {
			width = getElement().getClientWidth();
		}
		if (handleWidth == -1) {
			handleWidth = handle.getClientWidth();
		}
		int type = DOM.eventGetType(event);

		switch (type) {
		case TouchEvent.ONTOUCHSTART:
		case Event.ONMOUSEDOWN:
			if (event.getEventTarget().equals(buttonInner)) {
				mouseDownPos = xPos(event, type);
			}
			event.stopPropagation();
			break;
		case TouchEvent.ONTOUCHEND:
		case Event.ONMOUSEUP:
			if (mouseDownPos > 0) {
				handleMouseUp(dragPos);
			} else {
				toggle(true);
			}
			event.stopPropagation();
			break;
		case TouchEvent.ONTOUCHMOVE:
		case Event.ONMOUSEMOVE:
			if (dragging) {
				// we need to get the position from the base
				handleDrag(xPos(event, type));
			} else if (mouseDownPos != -1) {
				handleMouseMove(xPos(event, type));
			}
			event.stopPropagation();
			break;
		case Event.ONMOUSEOUT:
			if (dragging) {
				handleMouseUp(event.getClientX());
				event.stopPropagation();
			}
			break;
		}
		super.onBrowserEvent(event);
	}

	private int xPos(Event event, int type) {
		if (type == TouchEvent.ONTOUCHCANCEL || type == TouchEvent.ONTOUCHEND
				|| type == TouchEvent.ONTOUCHMOVE
				|| type == TouchEvent.ONTOUCHSTART)
			return ((NativeTouchEvent) event.cast()).getSingleTouch().pageX();
		else
			return event.getClientX();
	}

	private void handleMouseUp(int x) {
		float diff = Math.abs(x - mouseDownPos);
		if (diff < startDragPx) {
			mouseDownPos = -1;
			dragging = false;
			toggle(true);
		} else {
			if (!allowSwitch()) {
				resetState();
				return;
			}
			int pct = getPct(x);
			if (pct > 50) {
				turnOn(true);
			} else {
				turnOff(true);
			}
		}
	}

	private boolean allowSwitch() {
		if (lastSwitch <= 0) return true;
		else return (new Date().getTime() - lastSwitch > stopDelay);
	}

	private void resetState() {
		long lastSwitchTime = lastSwitch;
		if (!value) turnOff(false);
		else turnOn(false);
		this.lastSwitch = lastSwitchTime;
	}

	private void handleDrag(int xPos) {
		dragPos = xPos;
		int currentPct = getPct(xPos);
		if (currentPct > 100)
			currentPct = 100;
		if (currentPct < 0)
			currentPct = 0;
		handle.setAttribute("style", "left: " + currentPct + "%");
		this.currentPct = currentPct;
		if (currentPct > 50) {
			if (switchMode.equals("a")) {
				removeStyleName("ui-slider-switch-a");
				addStyleName("ui-slider-switch-b");
				switchMode = "b";
			}
		} else {
			if (switchMode.equals("b")) {
				removeStyleName("ui-slider-switch-b");
				addStyleName("ui-slider-switch-a");
				switchMode = "a";
			}
		}
	}

	private int getPct(int clientX) {
		float diff = clientX - mouseDownPos;
		if (diff > 0) {
			if (value)
				return 100;
			// moving to the right (off to on)
			float pct = diff / handleWidth;
			return (int) (pct * 100);
		} else if (diff < 0) {
			if (!value)
				return 0;
			// moving to left (on to off)
			float pct = ((float) Math.abs(diff)) / handleWidth;
			return 100 - (int) (pct * 100);
		} else {
			return currentPct;
		}
	}

	private void handleMouseMove(int xPos) {
		if (Math.abs(mouseDownPos - xPos) > startDragPx) {
			dragging = true;
			handleDrag(xPos);
		}
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
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
		handleWidth = -1;
		width = -1;
	}
}
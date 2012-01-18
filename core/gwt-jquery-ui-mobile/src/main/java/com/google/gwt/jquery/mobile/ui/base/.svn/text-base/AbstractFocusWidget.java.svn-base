package com.google.gwt.jquery.mobile.ui.base;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasInset;
import com.google.gwt.jquery.mobile.ui.face.HasMouseMovement;
import com.google.gwt.jquery.mobile.ui.face.HasOrientation;
import com.google.gwt.jquery.mobile.ui.face.HasShadow;
import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.ListenerWrapper;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseWheelListener;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.impl.FocusImpl;

public abstract class AbstractFocusWidget extends SimplePanel implements
		HasCorners, HasShadow, HasMouseMovement, HasOrientation, HasInset,
		SourcesClickEvents, HasClickHandlers, HasFocus, HasAllFocusHandlers,
		HasAllKeyHandlers, HasAllMouseHandlers, SourcesMouseEvents {

	private static final FocusImpl impl = FocusImpl.getFocusImplForWidget();

	/**
	 * Gets the FocusImpl instance.
	 * 
	 * @return impl
	 */
	protected static FocusImpl getFocusImpl() {
		return impl;
	}

	private boolean initialized = false;
	private String dataTheme = null;
	private boolean mouseDown = false;
	private boolean mouseOver = false;
	private boolean shadow = false;
	private boolean inset = false;
	private String roundingType;
	private String orientation;
	private String defaultClass = null;
	private String staticClass = null;

	public AbstractFocusWidget(String nodeName, String cssClass) {
		super(DOM.createElement(nodeName));
		this.defaultClass = cssClass;
		onStaticStyleNameChange(false);
		sinkEvents(Event.MOUSEEVENTS);
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
			this.staticClass = CSSUtils.generateStaticStyleName(this,
					getDefaultStyleName());
			CSSUtils.setStyleName(staticClass, this);
		}
	}

	protected String getThemedStyle() {
		return null;
	}

	public boolean isInteractive() {
		return true;
	}

	public void setDataTheme(String dataTheme) {
		this.dataTheme = dataTheme;
		if (isAttached())
			CSSUtils.setStyleName(staticClass, this);
	}

	public String getDataTheme() {
		return dataTheme;
	}

	public String getThemedStyleName() {
		return null;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMouseDown() {
		return mouseDown;
	}

	public void setRoundingType(String roundingType) {
		this.roundingType = roundingType;
		onStaticStyleNameChange(false);
	}

	public void setRounded(boolean rounded) {
		if (rounded) {
			setRoundingType(ROUND_TYPE_ALL);
		}
	}

	public String getRoundingType() {
		return roundingType;
	}

	public String getRoundingTypePrefix() {
		return "ui-corner";
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
		onStaticStyleNameChange(false);
	}

	public String getOrientationPrefix() {
		return null;
	}

	public void setShadow(boolean shadow) {
		this.shadow = shadow;
		onStaticStyleNameChange(false);
	}

	public boolean hasShadow() {
		return shadow;
	}

	public void setInset(boolean inset) {
		this.inset = inset;
		onStaticStyleNameChange(false);
	}

	public boolean isInset() {
		return inset;
	}

	public String getInsetPrefix() {
		return null;
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

	@Override
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEDOWN:
			if (!mouseDown) {
				mouseDown = true;
				CSSUtils.setStyleName(staticClass, this);
			}
			break;
		case Event.ONMOUSEUP:
			if (mouseDown) {
				mouseDown = false;
				CSSUtils.setStyleName(staticClass, this);
			}
			break;
		case Event.ONMOUSEOVER:
			if (!mouseOver) {
				mouseOver = true;
				CSSUtils.setStyleName(staticClass, this);
			}
			break;
		case Event.ONMOUSEOUT:
			if (mouseDown || mouseOver) {
				mouseDown = false;
				mouseOver = false;
				CSSUtils.setStyleName(staticClass, this);
			}
		}
		super.onBrowserEvent(event);
	}

	/** FOCUS FUNCTIONALITY **/
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return addDomHandler(handler, BlurEvent.getType());
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	/**
	 * @deprecated Use {@link #addClickHandler} instead
	 */
	@Deprecated
	public void addClickListener(ClickListener listener) {
		ListenerWrapper.WrappedClickListener.add(this, listener);
	}

	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return addDomHandler(handler, FocusEvent.getType());
	}

	/**
	 * @deprecated Use {@link #addFocusHandler} instead
	 */
	@Deprecated
	public void addFocusListener(FocusListener listener) {
		ListenerWrapper.WrappedFocusListener.add(this, listener);
	}

	/**
	 * @deprecated Use {@link #addKeyDownHandler}, {@link #addKeyUpHandler} and
	 *             {@link #addKeyPressHandler} instead
	 */
	@Deprecated
	public void addKeyboardListener(KeyboardListener listener) {
		ListenerWrapper.WrappedKeyboardListener.add(this, listener);
	}

	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return addDomHandler(handler, KeyDownEvent.getType());
	}

	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}

	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}

	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	/**
	 * @deprecated Use {@link #addMouseOverHandler} {@link #addMouseMoveHandler}
	 *             , {@link #addMouseDownHandler}, {@link #addMouseUpHandler}
	 *             and {@link #addMouseOutHandler} instead
	 */
	@Deprecated
	public void addMouseListener(MouseListener listener) {
		ListenerWrapper.WrappedMouseListener.add(this, listener);
	}

	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}

	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler, MouseUpEvent.getType());
	}

	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return addDomHandler(handler, MouseWheelEvent.getType());
	}

	/**
	 * @deprecated Use {@link #addMouseWheelHandler} instead
	 */
	@Deprecated
	public void addMouseWheelListener(MouseWheelListener listener) {
		ListenerWrapper.WrappedMouseWheelListener.add(this, listener);
	}

	/**
	 * Gets the tab index.
	 * 
	 * @return the tab index
	 */
	public int getTabIndex() {
		return impl.getTabIndex(getElement());
	}

	/**
	 * Gets whether this widget is enabled.
	 * 
	 * @return <code>true</code> if the widget is enabled
	 */
	public boolean isEnabled() {
		return !DOM.getElementPropertyBoolean(getElement(), "disabled");
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler} method on
	 *             the object returned by {@link #addClickHandler} instead
	 */
	@Deprecated
	public void removeClickListener(ClickListener listener) {
		ListenerWrapper.WrappedClickListener.remove(this, listener);
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler} method on
	 *             the object returned by {@link #addFocusHandler} instead
	 */
	@Deprecated
	public void removeFocusListener(FocusListener listener) {
		ListenerWrapper.WrappedFocusListener.remove(this, listener);
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler} method on
	 *             the object returned by an add*Handler method instead
	 */
	@Deprecated
	public void removeKeyboardListener(KeyboardListener listener) {
		ListenerWrapper.WrappedKeyboardListener.remove(this, listener);
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler} method on
	 *             the object returned by an add*Handler method instead
	 */
	@Deprecated
	public void removeMouseListener(MouseListener listener) {
		ListenerWrapper.WrappedMouseListener.remove(this, listener);
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler} method on
	 *             the object returned by {@link #addMouseWheelHandler} instead
	 */
	@Deprecated
	public void removeMouseWheelListener(MouseWheelListener listener) {
		ListenerWrapper.WrappedMouseWheelListener.remove(this, listener);
	}

	public void setAccessKey(char key) {
		DOM.setElementProperty(getElement(), "accessKey", "" + key);
	}

	/**
	 * Sets whether this widget is enabled.
	 * 
	 * @param enabled
	 *            <code>true</code> to enable the widget, <code>false</code> to
	 *            disable it
	 */
	public void setEnabled(boolean enabled) {
		DOM.setElementPropertyBoolean(getElement(), "disabled", !enabled);
	}

	public void setFocus(boolean focused) {
		if (focused) {
			impl.focus(getElement());
		} else {
			impl.blur(getElement());
		}
	}

	public void setTabIndex(int index) {
		impl.setTabIndex(getElement(), index);
	}
}
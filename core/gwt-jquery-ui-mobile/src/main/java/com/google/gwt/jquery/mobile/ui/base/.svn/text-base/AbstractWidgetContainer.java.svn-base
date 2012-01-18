package com.google.gwt.jquery.mobile.ui.base;

import com.google.gwt.dom.client.Node;
import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasMouseMovement;
import com.google.gwt.jquery.mobile.ui.face.HasOrientation;
import com.google.gwt.jquery.mobile.ui.face.HasShadow;
import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractWidgetContainer extends ComplexPanel implements
		InsertPanel, HasCorners, HasShadow, HasMouseMovement, HasOrientation {

	private String dataTheme = null;
	private boolean mouseDown = false;
	private boolean mouseOver = false;
	private boolean shadow = false;
	private String roundingType;
	private String orientation;
	private String defaultClass = null;
	private String staticClass = null;
	private boolean initialized = false;

	public AbstractWidgetContainer(String nodeName, String cssClass,
			String dataRole, String role) {
		Element element = DOM.createElement(nodeName);
		setElement(element);
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
			this.staticClass = CSSUtils.generateStaticStyleName(this, getDefaultStyleName());
			CSSUtils.setStyleName(staticClass, this);
		}
	}

	protected String getThemedStyle() {
		return null;
	}
	
	public boolean isInteractive() {
		return false;
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

	/**
	 * Adds a new child widget to the panel.
	 * 
	 * @param w
	 *            the widget to be added
	 */
	@Override
	public void add(Widget w) {
		add(w, getElement());
		if (isAttached())
			CSSUtils.refreshRoundingChildren(this);
	}

	@Override
	protected void onAttach() {
		if (!initialized) {
			JQueryMobile.initWidget(this);
			initialized = true;
		}
		onStaticStyleNameChange(true);
		CSSUtils.refreshRoundingChildren(this);
		super.onAttach();
	}

	@Override
	public void clear() {
		try {
			doLogicalClear();
		} finally {
			// Remove all existing child nodes.
			Node child = getElement().getFirstChild();
			while (child != null) {
				getElement().removeChild(child);
				child = getElement().getFirstChild();
			}
		}
	}

	/**
	 * Inserts a widget before the specified index.
	 * 
	 * @param w
	 *            the widget to be inserted
	 * @param beforeIndex
	 *            the index before which it will be inserted
	 * @throws IndexOutOfBoundsException
	 *             if <code>beforeIndex</code> is out of range
	 */
	public void insert(Widget w, int beforeIndex) {
		insert(w, getElement(), beforeIndex, true);
	}

	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);
		if (null != getThemedStyle()) {
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
		}
	}
}
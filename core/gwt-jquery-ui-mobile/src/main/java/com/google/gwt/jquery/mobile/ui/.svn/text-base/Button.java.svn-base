package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.base.AbstractFocusWidget;
import com.google.gwt.jquery.mobile.ui.face.HasActiveStatus;
import com.google.gwt.jquery.mobile.ui.face.HasInline;
import com.google.gwt.jquery.mobile.ui.face.HasMutableIcon;
import com.google.gwt.jquery.mobile.ui.face.HasPerspective;
import com.google.gwt.jquery.mobile.ui.face.HasPosition;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

public class Button extends AbstractFocusWidget implements HasPosition, HasMutableIcon, HasActiveStatus, HasPerspective,HasInline , ClickHandler {

	public static final String ICON_ARROW_LEFT = "arrow-l";
	public static final String ICON_ARROW_RIGHT = "arrow-r";
	public static final String ICON_ARROW_UP = "arrow-u";
	public static final String ICON_ARROW_DOWN = "arrow-d";
	public static final String ICON_DELETE = "delete";
	public static final String ICON_PLUS = "plus";
	public static final String ICON_MINUS = "minus";
	public static final String ICON_CHECK = "check";
	public static final String ICON_GEAR = "gear";
	public static final String ICON_REFRESH = "refresh";
	public static final String ICON_FORWARD = "forward";
	public static final String ICON_BACK = "back";
	public static final String ICON_GRID = "grid";
	public static final String ICON_STAR = "star";
	public static final String ICON_ALERT = "alert";
	public static final String ICON_INFO = "info";
	public static final String ICON_HOME =  "home";
	
	private static final String ICON_POS_DEFAULT = "left";
	
	protected Element wrapper;
	protected Element textElement;
	private Element iconElement;

	private String iconPosition;
	private String icon;
	private String position;
	private boolean active = false;
	private boolean inline = false;

	private String href;
	private String hrefTarget;
	private HandlerRegistration hrefReg;
	
	public Button() {
		this(null, null, null);
	}
	
	public Button(String text) {
		this(text, null, null);
	}
	
	public Button(String text, String icon) {
		this(text, icon, null);
	}

	public Button(String text, String icon, ClickHandler clickHandler) {
		this("a", "ui-btn", text, icon, clickHandler);
	}

	public Button(String nodeName, String className, String text, String icon, ClickHandler clickHandler) {
		super(nodeName, className);
		wrapper = DOM.createElement(getButtonInnerNodeName());
		wrapper.setAttribute("class", "ui-btn-inner");
		getElement().appendChild(wrapper);
		
		textElement = DOM.createElement(getButtonTextNodeName());
		wrapper.appendChild(textElement);
		textElement.setAttribute("class", "ui-btn-text");
		if (null != text) setText(text);
		if (null != icon) setIcon(icon);
		
		if (clickHandler != null) hrefReg = addClickHandler(clickHandler);
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		if (null == href) {
			if (null != hrefReg) hrefReg.removeHandler();
		}
		else {
			if (null == hrefReg) hrefReg = addClickHandler(this);
		}
		this.href = href;
	}

	public void setTarget(String target) {
		this.hrefTarget = target;
	}

	protected String getButtonInnerNodeName() {
		return "span";
	}

	protected String getButtonTextNodeName() {
		return "span";
	}

	@Override
	protected com.google.gwt.user.client.Element getContainerElement() {
		return textElement;
	}

	protected com.google.gwt.user.client.Element getButtonTextElement() {
		return textElement;
	}

	public void setInline(boolean inline) {
		this.inline = inline;
		onStaticStyleNameChange(false);
	}

	public boolean isInline() {
		return inline;
	}

	@Override
	public String getInsetPrefix() {
		return "ui-btn";
	}

	public String getInlinePrefix() {
		return "ui-btn";
	}

	public void setText(String text) {
		textElement.setInnerText(text);
	}
	
	public void setIcon(String icon) {
		if (null == iconElement) {
			iconElement = DOM.createSpan();
			wrapper.appendChild(iconElement);
		}
		iconElement.setAttribute("class", "ui-icon ui-icon-shadow ui-icon-" + icon);
		this.icon = icon;
		onStaticStyleNameChange(false);
	}

	public String getIcon() {
		return icon;
	}

	public String getDefaultIconPos() {
		return POSITION_LEFT;
	}
	
	public void setIconPos(String iconPos) {
		this.iconPosition = iconPos;
		onStaticStyleNameChange(false);
	}

	public String getIconPos() {
		return iconPosition;
	}

	public void setActive(boolean active) {
		this.active = active;
		onStaticStyleNameChange(false);
	}
	
	public boolean isActive() {
		return active;
	}

	@Override
	public String getThemedStyleName() {
		return "ui-btn";
	}

	public String getDefaultThemedStyleName() {
		return "ui-btn";
	}

	@Override
	protected String getDefaultStyleName() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getDefaultStyleName());
		if (null != iconPosition)
			sb.append(' ').append("ui-btn-icon").append("-").append(iconPosition);
		else if (null != icon && null == iconPosition)
			sb.append(' ').append("ui-btn-icon").append("-").append(getDefaultIconPos());
		if (active)
			sb.append(' ').append("ui-btn-active");
		return sb.toString();
	}

	public void setRoundingType(String roundingType) {
		super.setRoundingType(roundingType);
		wrapper.addClassName(getRoundingTypePrefix() + "-" + roundingType);
	}

	@Override
	public String getRoundingTypePrefix() {
		return "ui-btn-corner";
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
		onStaticStyleNameChange(false);
	}

	public String getPositionPrefix() {
		return "ui-btn";
	}

	public String getPerspective() {
		return "ui-btn";
	}

	public void onClick(ClickEvent event) {
		if (null != href) {
			if (null != hrefTarget) {
				Window.open(href, hrefTarget, null);
			}
			else {
				Window.Location.assign(href);
			}
		}
	}
}
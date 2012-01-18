package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class ListButton extends Button {

	private Element thumbElement;
	private String thumb;

	private Element detailElement;
	private String detail;

	private Element bubbleElement;
	private String bubble;

	public ListButton() {
		this(null, null, null);
	}
	
	public ListButton(String text) {
		this(text, null, null);
	}
	
	public ListButton(String text, String icon) {
		this(text, icon, null);
	}

	public ListButton(String text, String icon, ClickHandler clickHandler) {
		super("li", "ui-btn ui-li", text, icon, clickHandler);
	}

	@Override
	public String getRoundingTypePrefix() {
		return "ui-corner";
	}

	@Override
	public String getDefaultIconPos() {
		return ICON_POS_RIGHT;
	}

	@Override
	protected String getButtonInnerNodeName() {
		return "div";
	}

	@Override
	protected String getButtonTextNodeName() {
		return "div";
	}

	public void setHasAlt(boolean hasAlt) {
		if (hasAlt) {
			addStyleName("ui-li-has-alt");
			if (null == getIconPos())
				setIconPos(ICON_POS_RIGHT);
		}
		else
			removeStyleName("ui-li-has-alt");
	}

	public void setHasThumb(boolean hasThumb) {
		if (hasThumb)
			addStyleName("ui-li-has-thumb");
		else
			removeStyleName("ui-li-has-thumb");
	}

	@Override
	public String getPerspective() {
		return "ui-li";
	}

	public void setThumb(String thumb) 
	{
		if (thumb == null) return;
		
		if (null == thumbElement) {
			thumbElement = DOM.createImg();
			thumbElement.addClassName("ui-li-thumb");
			wrapper.appendChild(thumbElement);
		}
		thumbElement.setAttribute("src", thumb);
		this.thumb = thumb;
		setHasThumb(true);
//		onStaticStyleNameChange(false);
	}

	public String getThumb() {
		return thumb;
	}

	public void setBubble(String bubble) 
	{
		if (bubble == null) return;
		
		if (null == bubbleElement) {
			bubbleElement = DOM.createSpan();
			bubbleElement.addClassName("ui-li-count");
			bubbleElement.addClassName("ui-btn-up-c");
			bubbleElement.addClassName("ui-btn-corner-all");
			wrapper.appendChild(bubbleElement);
		}
		bubbleElement.setInnerText(bubble);
		this.bubble = bubble;
	}

	public String getBubble() {
		return bubble;
	}

	public void setDetailText(String detail) 
	{
		if (detail == null) return;
		
		if (null == detailElement) 
		{
			detailElement = DOM.createElement("P");
			detailElement.addClassName("ui-li-desc");
			wrapper.appendChild(detailElement);
		}
		detailElement.setInnerText(detail);
		this.detail = detail;
		
		if (textElement != null && textElement.getInnerText() != null)
		{
			Element element = DOM.createElement("H3");
			element.addClassName("ui-li-heading");
			element.setInnerText(textElement.getInnerText());
			textElement.setInnerText("");
			textElement.appendChild(element);
		}
	}
}
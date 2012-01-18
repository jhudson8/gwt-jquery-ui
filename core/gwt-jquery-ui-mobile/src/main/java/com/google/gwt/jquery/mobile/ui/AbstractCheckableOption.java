package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.TouchEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.face.HasIcon;
import com.google.gwt.jquery.mobile.ui.face.HasMutableIcon;
import com.google.gwt.jquery.mobile.ui.face.IsRefreshable;
import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractCheckableOption extends Widget implements
		HasValueChangeHandlers<Boolean>, HasCorners, HasName, HasDataTheme,
		HasIcon, IsRefreshable {

	private boolean initialized;
	private boolean showIcon;
	private String iconPosition = HasMutableIcon.ICON_POS_LEFT;
	private Element checkableEl;
	private Element labelEl;
	private Element innerSpan;
	private Element btnTextSpan;
	private Element iconSpan;

	private String optionName;
	private boolean checked = false;
	private String value;
	private String label;

	// cache
	private String theme;
	private String rounding;
	private boolean hovering;

	public AbstractCheckableOption() {
		this(null, false);
	}

	public AbstractCheckableOption(String value, boolean checked) {
		setElement(DOM.createDiv());
		getElement().setClassName(getElementClassName());
		this.checked = checked;
		this.value = value;

		sinkEvents(Event.ONCHANGE | Event.ONCLICK | Event.MOUSEEVENTS
				| TouchEvent.ONTOUCHSTART | TouchEvent.ONTOUCHEND
				| TouchEvent.ONTOUCHMOVE);
	}

	protected abstract String getElementClassName();

	protected abstract String getUiDomain();


	@Override
	protected void onLoad() {
		super.onLoad();
		if (null == checkableEl) {
			initialize();
		}
	}

	public void setName(String name) {
		this.optionName = name;
		initialize();
	}

	public String getName() {
		return checkableEl.getAttribute("name");
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private void initialize() {
		checkableEl = createCheckableElement(optionName);
		getElement().appendChild(checkableEl);
		String id = DOM.createUniqueId();
		checkableEl.setId(id);
		if (null != value)
			checkableEl.setAttribute("value", value);
		labelEl = DOM.createLabel();
		labelEl.setAttribute("for", id);
		labelEl.setClassName("ui-btn");
		getElement().appendChild(labelEl);
		innerSpan = DOM.createSpan();
		innerSpan.setClassName("ui-btn-inner");
		labelEl.appendChild(innerSpan);
		btnTextSpan = DOM.createSpan();
		innerSpan.appendChild(btnTextSpan);
		btnTextSpan.setClassName("ui-btn-text");
		if (null != label) {
			btnTextSpan.setInnerText(label);
		}
		iconSpan = DOM.createSpan();
		innerSpan.appendChild(iconSpan);
		iconSpan.setClassName("ui-icon");
		if (null != this.rounding) {
			String _roundingType = this.rounding;
			this.rounding = null;
			setRoundingType(_roundingType);
		}
		if (null != this.theme) {
			String _dataTheme = this.theme;
			this.theme = null;
			setDataTheme(_dataTheme);
		}
		setShowIcon(showIcon);
		this.label = null;
		this.value = null;
		this.optionName = null;
		setChecked(this.checked, false);
		refreshState();
	}

	protected abstract Element createCheckableElement(String optionName);

	public void setShowIcon(boolean icon) {
		setShowIcon(icon, iconPosition);
	}

	public void setShowIcon(boolean icon, String iconPos) {
		if (icon) {
			if (null != labelEl) {
				if (null != this.iconPosition)
					CSSUtils.removeClassName(labelEl, "ui-btn-icon-" + iconPosition);
				CSSUtils.addClassName(labelEl, "ui-btn-icon-" + iconPosition);
			}
			if (null != iconSpan)
				iconSpan.removeAttribute("style");
		} else {
			if (null != labelEl) 
				CSSUtils.removeClassName(labelEl, "ui-btn-icon-" + iconPosition);
			if (null != iconSpan)
				iconSpan.setAttribute("style", "display: none");
		}
		this.showIcon = icon;
		this.iconPosition = iconPos;
		refreshState(isChecked());
	}

	public boolean isShowIcon() {
		return this.showIcon;
	}

	public void setIconPos(String iconPosition) {
		this.iconPosition = iconPosition;
		setShowIcon(this.showIcon);
	}

	public String getIconPos() {
		return iconPosition;
	}

	public void setText(String text) {
		if (null == checkableEl)
			this.label = text;
		else
			this.labelEl.setInnerText(text);
	}

	public void setRoundingType(String roundingType) {
		if (null != checkableEl) {
			CSSUtils.replaceClassName(labelEl, "ui-corner-", this.rounding,
					roundingType);
			CSSUtils.replaceClassName(innerSpan, "ui-corner-", this.rounding,
					roundingType);
		}
		this.rounding = roundingType;
	}

	public String getRoundingType() {
		return this.rounding;
	}

	public String getRoundingTypePrefix() {
		return null;
	}

	public void setDataTheme(String dataTheme) {
		if (null != checkableEl) {
			if (null != this.theme) {
				CSSUtils.removeClassName(labelEl, "ui-btn-hover-" + theme);
				CSSUtils.removeClassName(labelEl, "ui-btn-up-" + theme);
				CSSUtils.removeClassName(labelEl, "ui-btn-down-" + theme);
			}
			if (null != dataTheme)
				CSSUtils.addClassName(labelEl, "ui-btn-up-" + dataTheme);
		}
		this.theme = dataTheme;
	}

	public String getDataTheme() {
		return this.theme;
	}

	public String getDefaultThemedStyleName() {
		return null;
	}

	@Override
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEMOVE:
			onMouseHover();
			break;
		case Event.ONMOUSEDOWN:
		case TouchEvent.ONTOUCHSTART:
		case TouchEvent.ONTOUCHMOVE:
			onMouseDown();
			break;
		case Event.ONMOUSEUP:
		case TouchEvent.ONTOUCHEND:
			onMouseUp();
			break;
		case Event.ONCHANGE:
			onSelected();
			break;
		case Event.ONMOUSEOUT:
			resetHoverState();
			break;
		}
		event.stopPropagation();
	}

	@Override
	protected void onAttach() {
		if (!initialized) {
			if (null != theme)
				resetHoverState();
			if (null != rounding)
				setRoundingType(rounding);
			JQueryMobile.initWidget(this);
			initialized = true;
		}
		super.onAttach();
	}

	private void onSelected() {
		boolean isChecked = isChecked();
		refreshState(isChecked);
		ValueChangeEvent.fire(this, isChecked);
	}

	private void onMouseHover() {
		if (!hovering && null != theme) {
			CSSUtils.removeClassName(labelEl, "ui-btn-down-" + theme);
			CSSUtils.removeClassName(labelEl, "ui-btn-up-" + theme);
			CSSUtils.addClassName(labelEl, "ui-btn-hover-" + theme);
			hovering = true;
		}
	}

	private void onMouseDown() {
		if (null != theme) {
			CSSUtils.removeClassName(labelEl, "ui-btn-hover-" + theme);
			CSSUtils.removeClassName(labelEl, "ui-btn-up-" + theme);
			CSSUtils.addClassName(labelEl, "ui-btn-down-" + theme);
			hovering = false;
		}
	}

	private void onMouseUp() {
		if (null != theme) {
			CSSUtils.addClassName(labelEl, "ui-btn-hover-" + theme);
			CSSUtils.removeClassName(labelEl, "ui-btn-up-" + theme);
			CSSUtils.removeClassName(labelEl, "ui-btn-down-" + theme);
			hovering = false;
		}
	}

	private void resetHoverState() {
		if (null != theme) {
			CSSUtils.removeClassName(labelEl, "ui-btn-hover-" + theme);
			CSSUtils.removeClassName(labelEl, "ui-btn-down-" + theme);
			CSSUtils.addClassName(labelEl, "ui-btn-up-" + theme);
			hovering = false;
		}
	}

	public void toggle() {
		setChecked(!isChecked());
	}

	public boolean isChecked() {
		if (null == checkableEl)
			return checked;
		else
			return isChecked((com.google.gwt.dom.client.Element) checkableEl.cast());
	}

	public void setChecked(boolean checked) {
		setChecked(checked, true);
	}

	protected void setChecked(boolean checked, boolean fireEvent) {
		if (isChecked() != checked) {
			if (fireEvent)
				ValueChangeEvent.fireIfNotEqual(this, checked, isChecked());
			if (null == checkableEl)
				this.checked = checked;
			else {
				if (checked)
					doCheck(checkableEl.cast());
				else
					removeCheck(checkableEl.cast());
				refreshState(checked);
			}
		}
	}

	public void refreshState() {
		refreshState(isChecked());
	}

	private void refreshState(boolean checked) {
		if (null != labelEl) {
			if (checked) {
				CSSUtils.addClassName(labelEl, "ui-btn-active");
				CSSUtils
						.replaceClassName(iconSpan,
								"ui-icon-ui-icon-" + getUiDomain() + "-off",
								"ui-icon-ui-icon-" + getUiDomain() + "-on");
				CSSUtils.replaceClassName(iconSpan, "ui-icon-" + getUiDomain() + "-off",
						"ui-icon-" + getUiDomain() + "-on");
			} else {
				CSSUtils.removeClassName(labelEl, "ui-btn-active");
				CSSUtils.replaceClassName(iconSpan, "ui-icon-ui-icon-" + getUiDomain() + "-on",
						"ui-icon-ui-icon-" + getUiDomain() + "-off");
				CSSUtils.replaceClassName(iconSpan, "ui-icon-" + getUiDomain() + "-on",
						"ui-icon-" + getUiDomain() + "-off");
			}
			resetHoverState();
		}
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return super.addHandler(handler, ValueChangeEvent.getType());
	}

	private final native boolean isChecked(JavaScriptObject element) /*-{
		return element.checked;
	}-*/;

	private final native void doCheck(JavaScriptObject element) /*-{
		element.checked = true;
	}-*/;

	private final native void removeCheck(JavaScriptObject element) /*-{
		element.checked = false;
	}-*/;
}
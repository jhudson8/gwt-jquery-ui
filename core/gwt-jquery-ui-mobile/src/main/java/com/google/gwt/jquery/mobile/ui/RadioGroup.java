package com.google.gwt.jquery.mobile.ui;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.face.HasIcon;
import com.google.gwt.jquery.mobile.ui.face.IsRefreshable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Widget;

public class RadioGroup extends ControlGroup implements ValueChangeHandler<Boolean> {

	private boolean showIcons = true;
	private String iconPos = HasIcon.ICON_POS_LEFT;
	private HashMap<Widget, HandlerRegistration> handlers = new HashMap<Widget, HandlerRegistration>();
	
	public RadioGroup() {
		super();
		getElement().setId(DOM.createUniqueId());
	}

	@Override
	public void add(Widget w) {
		if (w instanceof HasValueChangeHandlers)
			handlers.put(w, ((HasValueChangeHandlers<Boolean>) w).addValueChangeHandler(this));
		reloadChild(w);
		if (w instanceof HasName) {
			((HasName) w).setName(getElement().getId() + "-rdo");
		}
		super.add(w);
	}

	@Override
	public boolean remove(Widget w) {
		HandlerRegistration reg = handlers.remove(w);
		if (null != reg) reg.removeHandler();
		return super.remove(w);
	}

	protected void reloadChildren() {
		for (Iterator<Widget> iter = getChildren().iterator(); iter.hasNext(); ) {
			reloadChild(iter.next());
		}
	}

	protected void reloadChild(Widget w) {
		if (w instanceof HasIcon) {
			((HasIcon) w).setShowIcon(showIcons, iconPos);
		}
	}

	public void setIconPos(String iconPos) {
		this.iconPos = iconPos;
		reloadChildren();
	}

	public String getIconPos() {
		return this.iconPos;
	}

	public boolean isShowIcons() {
		return this.showIcons;
	}

	public void setShowIcons(boolean showIcon) {
		this.showIcons = showIcon;
		reloadChildren();
	}

	public void onValueChange(ValueChangeEvent<Boolean> event) {
		for (Iterator<Widget> iter = getChildren().iterator(); iter.hasNext(); ) {
			Widget w = iter.next();
			if (!w.equals(event.getSource()) && w instanceof IsRefreshable) {
				((IsRefreshable) w).refreshState();
			}
		}
	} 
}
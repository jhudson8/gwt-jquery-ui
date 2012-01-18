package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.base.AbstractWidgetContainer;
import com.google.gwt.jquery.mobile.ui.face.HasIcon;
import com.google.gwt.jquery.mobile.ui.face.HasMutableIcon;
import com.google.gwt.user.client.ui.Widget;


public class ControlGroup extends AbstractWidgetContainer {
	
	private boolean needsRefresh = false;
	private String iconPos = null;
	private Boolean showIcons = null;
	
	public ControlGroup() {
		this("ui-controlgroup");
	}

	protected ControlGroup(String css) {
		super("div", css, "controlgroup", null);
		setOrientation(getDefaultOrientation());
	}

	protected String getDefaultOrientation() {
		return ORIENTATION_HORIZONTAL;
	}

	public String getDefaultThemedStyleName() {
		return null;
	}

	@Override
	public String getOrientationPrefix() {
		return "ui-controlgroup";
	}

	@Override
	public void add(Widget w) {
		super.add(w);
		if (null != iconPos) {
			if (w instanceof HasMutableIcon && null == ((HasMutableIcon) w).getIconPos())
				((HasMutableIcon) w).setIconPos(iconPos);
		}
		needsRefresh = true;
		refreshChildren(false);
	}

	public void setIconPos(String iconPos) {
		this.iconPos = iconPos;
		int count = getWidgetCount();
		for (int i=0; i<count; i++) {
			Widget w = getWidget(i);
			if (w instanceof HasMutableIcon) {
				if (null == ((HasMutableIcon) w).getIconPos())
					((HasMutableIcon) w).setIconPos(iconPos);
			}
			else if (w instanceof HasIcon)
				if (null == ((HasIcon) w).getIconPos())
					((HasIcon) w).setIconPos(iconPos);
		}
	}

	public void setShowIcons (boolean showIcons) {
		this.showIcons = new Boolean(showIcons);
		int count = getWidgetCount();
		for (int i=0; i<count; i++) {
			Widget w = getWidget(i);
			if (w instanceof HasIcon) {
				((HasIcon) w).setShowIcon(showIcons);
			}
		}
	}
	
	@Override
	protected void onAttach() {
		refreshChildren(true);
		super.onAttach();
	}

	private void refreshChildren(boolean force) {
		if ((isAttached() || force) && needsRefresh) {
			int count = getWidgetCount();
			for (int i=0; i<count; i++) {
				Widget w = getWidget(i);
				if (i == count-1) {
					w.addStyleName("ui-controlgroup-last");
				}
				else {
					w.removeStyleName("ui-controlgroup-last");
				}
			}
			needsRefresh = false;
		}
	}
}

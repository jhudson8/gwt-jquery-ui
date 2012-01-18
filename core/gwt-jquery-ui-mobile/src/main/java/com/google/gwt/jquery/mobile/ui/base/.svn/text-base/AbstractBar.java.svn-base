package com.google.gwt.jquery.mobile.ui.base;

import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasPosition;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractBar extends AbstractWidgetContainer implements HasCorners {

	public AbstractBar(String cssClass, String dataRole, String role) {
		super("div", cssClass, dataRole, role);
	}

	@Override
	public String getThemedStyleName() {
		return "ui-bar";
	}

	public String getDefaultThemedStyleName() {
		return "ui-bar";
	}

	@Override
	public boolean isInteractive() {
		return false;
	}
	
	@Override
	protected void add(Widget child,
			com.google.gwt.user.client.Element container) {
		super.add(child, container);
		if (child instanceof HasPosition) {
			String position = ((HasPosition) child).getPosition();
			if (null == position)
				((HasPosition) child).setPosition(HasPosition.POSITION_LEFT);
		}
	}
}
package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.face.HasPerspective;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Heading extends HTMLPanel {

	private boolean initialized = false;
	
	public Heading(String html) {
		super("h3", html);
	}

	@Override
	protected void onAttach() {
		if (!initialized) {
			HasPerspective provider = null;
			Widget parent = getParent();
			while (null != parent) {
				if (parent instanceof HasPerspective) {
					provider = (HasPerspective) parent;
					break;
				}
				else {
					parent = parent.getParent();
				}
			}
			if (null != provider) {
				addStyleName(provider.getPerspective() + "-heading");
			}
			else {
				addStyleName("ui-heading");
			}
			initialized = true;
		}
		super.onAttach();
	}
}

package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.face.HasPerspective;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Description extends HTMLPanel {

	private boolean initialized = false;
	
	public Description(String html) {
		super("p", html);
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
				addStyleName(provider.getPerspective() + "-desc");
			}
			else {
				addStyleName("ui-desc");
			}
			initialized = true;
		}
		super.onAttach();
	}
}

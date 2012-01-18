package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.face.HasMouseMovement;
import com.google.gwt.jquery.mobile.ui.util.CSSUtils;
import com.google.gwt.jquery.mobile.ui.util.JQueryMobile;
import com.google.gwt.user.client.ui.HTMLPanel;

public class StaticListItem extends HTMLPanel implements HasDataTheme, HasCorners, HasMouseMovement {

	private boolean initialized;
	private String dataTheme;
	private String roundingType;
	private String staticClass;
	private String defaultClass = "ui-li ui-li-static";
	
	public StaticListItem(String html) {
		super("li", html);
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

	public void setDataTheme(String dataTheme) {
		this.dataTheme = dataTheme;
		if (isAttached())
			CSSUtils.setStyleName(staticClass, this);
	}

	public String getDataTheme() {
		return dataTheme;
	}

	@Override
	protected void onAttach() {
		if (!initialized) {
			initialized = true;
			JQueryMobile.initWidget(this);
		}
		onStaticStyleNameChange(true);
		super.onAttach();
	}

	protected void onStaticStyleNameChange(boolean force) {
		if (isAttached() || force) {
			this.staticClass = CSSUtils.generateStaticStyleName(this,
					defaultClass);
			CSSUtils.setStyleName(staticClass, this);
		}
	}

	public String getDefaultThemedStyleName() {
		return "ui-btn-up";
	}

	public boolean isMouseDown() {
		return false;
	}

	public boolean isMouseOver() {
		return false;
	}

	public boolean isInteractive() {
		return false;
	}

	public String getThemedStyleName() {
		return "ui-btn";
	}
}
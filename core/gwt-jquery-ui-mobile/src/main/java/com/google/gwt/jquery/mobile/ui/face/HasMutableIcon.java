package com.google.gwt.jquery.mobile.ui.face;

public interface HasMutableIcon {

	public static final String ICON_POS_TOP = "top";
	public static final String ICON_POS_LEFT = "left";
	public static final String ICON_POS_RIGHT = "right";
	public static final String ICON_POS_BOTTOM = "bottom";
	public static final String ICON_POS_NO_TEXT = "notext";

	public void setIcon(String icon);

	public String getIcon();

	public String getIconPos();
	
	public void setIconPos(String iconPos);

	public String getDefaultIconPos();
}

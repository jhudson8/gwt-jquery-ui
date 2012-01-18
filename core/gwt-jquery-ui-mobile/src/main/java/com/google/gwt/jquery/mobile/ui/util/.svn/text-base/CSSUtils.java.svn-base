package com.google.gwt.jquery.mobile.ui.util;

import com.google.gwt.dom.client.Element;
import com.google.gwt.jquery.mobile.ui.face.HasCorners;
import com.google.gwt.jquery.mobile.ui.face.HasDataTheme;
import com.google.gwt.jquery.mobile.ui.face.HasInline;
import com.google.gwt.jquery.mobile.ui.face.HasInset;
import com.google.gwt.jquery.mobile.ui.face.HasMouseMovement;
import com.google.gwt.jquery.mobile.ui.face.HasOrientation;
import com.google.gwt.jquery.mobile.ui.face.HasPosition;
import com.google.gwt.jquery.mobile.ui.face.HasShadow;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.Widget;

public class CSSUtils {

	public static String generateStaticStyleName(Object widget,
			String baseStyleName) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseStyleName);
		if (widget instanceof HasShadow)
			if (((HasShadow) widget).hasShadow())
				sb.append(' ').append("ui-shadow");
		if (widget instanceof HasInset) {
			if (((HasInset) widget).isInset()) {
				String prefix = ((HasInset) widget).getInsetPrefix();
				if (null != prefix)
					sb.append(' ').append(prefix).append('-').append("inset");
			}
		}
		if (widget instanceof HasInline) {
			if (((HasInline) widget).isInline()) {
				String prefix = ((HasInline) widget).getInlinePrefix();
				if (null != prefix)
					sb.append(' ').append(prefix).append('-').append("inline");
			}
		}
		if (widget instanceof HasPosition) {
			String position = ((HasPosition) widget).getPosition();
			String prefix = ((HasPosition) widget).getPositionPrefix();
			if (null != position && null != prefix) {
				sb.append(' ').append(prefix).append('-').append(position);
			}
		}
		if (widget instanceof HasOrientation) {
			String orientation = ((HasOrientation) widget).getOrientation();
			String prefix = ((HasOrientation) widget).getOrientationPrefix();
			if (null != orientation && null != prefix) {
				sb.append(' ').append(prefix).append('-').append(orientation);
			}
		}
		if (widget instanceof HasCorners) {
			String roundingType = ((HasCorners) widget).getRoundingType();
			String prefix = ((HasCorners) widget).getRoundingTypePrefix();
			if (null != roundingType) {
				sb.append(' ').append(prefix).append('-').append(roundingType);
			}
		}
		return sb.toString();
	}

	public static void setStyleName(String staticStyleName,
			HasMouseMovement widget) {
		String dataTheme = widget.getDataTheme();
		String styleName = widget.getThemedStyleName();
		if (null != dataTheme && null != styleName) {
			StringBuilder sb = new StringBuilder();
			sb.append(staticStyleName);
			if (widget.isInteractive()) {
				if (widget.isMouseDown())
					sb.append(' ').append(styleName + "-down-" + dataTheme);
				if (widget.isMouseOver())
					sb.append(' ').append(styleName + "-hover-" + dataTheme)
							.append(' ');
				if (!widget.isMouseDown() && !widget.isMouseOver())
					sb.append(' ').append(styleName + "-up-" + dataTheme);
			} else {
				String defaultStyleName = widget.getDefaultThemedStyleName();
				if (null != defaultStyleName)
					sb.append(' ').append(defaultStyleName + "-" + dataTheme);
			}
			((Widget) widget).getElement().setAttribute("class", sb.toString());
			return;
		}
		((Widget) widget).getElement().setAttribute("class", staticStyleName);
	}

	public static void setStyleName(String staticStyleName, HasDataTheme widget) {
		String dataTheme = widget.getDataTheme();
		if (null != dataTheme) {
			StringBuilder sb = new StringBuilder();
			sb.append(staticStyleName);
			String defaultStyleName = widget.getDefaultThemedStyleName();
			if (null != defaultStyleName) {
				sb.append(' ').append(defaultStyleName + "-" + dataTheme);
			}
			((Widget) widget).getElement().setAttribute("class", sb.toString());
			return;
		}
		((Widget) widget).getElement().setAttribute("class", staticStyleName);
	}

	public static boolean hasClassWithPrefix(Element element, String prefix) {
		String cls = element.getAttribute("class");
		if (null == cls || cls.length() == 0)
			return false;
		String[] classes = cls.split(" ");
		for (String _cls : classes) {
			if (_cls.startsWith(prefix))
				return true;
		}
		return false;
	}

	public static boolean hasClassName(Element element, String prefix) {
		String cls = element.getAttribute("class");
		if (null == cls || cls.length() == 0)
			return false;
		String[] classes = cls.split(" ");
		for (String _cls : classes) {
			if (_cls.equals(prefix))
				return true;
		}
		return false;
	}

	public static void removeClassName(Element el, String remove) {
		String cls = el.getClassName();
		cls = removeClassName(cls, remove);
		el.setClassName(cls);
	}

	public static void addClassName(Element el, String add) {
		String cls = el.getClassName();
		cls = addClassName(cls, add);
		el.setClassName(cls);
	}

	public static String removeClassName(String cls, String add) {
		StringBuilder sb = new StringBuilder();
		if (null == cls || cls.length() == 0)
			return cls;
		String[] classes = cls.split(" ");
		for (String _cls : classes) {
			if (_cls.length() > 0 && !_cls.equals(add)) {
				if (sb.length() > 0)
					sb.append(' ');
				sb.append(_cls);
			}
		}
		return sb.toString();
	}

	public static void replaceClassName(Element element, String prefix, String removeClass, String addClass) {
		String cls = element.getClassName();
		if (null != removeClass)
			cls = removeClassName(cls, prefix + removeClass);
		if (null != addClass)
			cls = addClassName(cls, prefix + addClass);
		element.setClassName(cls);
	}

	public static void replaceClassName(Element element, String removeClass, String addClass) {
		String cls = element.getClassName();
		if (null != removeClass)
			cls = removeClassName(cls, removeClass);
		if (null != addClass)
			cls = addClassName(cls, addClass);
		element.setClassName(cls);
	}

	public static String addClassName(String cls, String add) {
		StringBuilder sb = new StringBuilder();
		if (null == cls || cls.length() == 0)
			return add;
		String[] classes = cls.split(" ");
		for (String _cls : classes) {
			if (_cls.length() > 0 && !_cls.equals(add)) {
				if (sb.length() > 0)
					sb.append(' ');
				sb.append(_cls);
			}
		}
		sb.append(' ').append(add);
		return sb.toString();
	}

	public static void refreshRoundingChildren(IndexedPanel widget) {
		String roundType = ((HasCorners) widget).getRoundingType();
		if (null != roundType) {
			if (widget.getWidgetCount() == 1) {
				Widget w = widget.getWidget(0);
				HasCorners c = (w instanceof HasCorners) ? (HasCorners) w
						: null;
				if (null != c) {
					c.setRoundingType(roundType);
				}
			} else if (widget.getWidgetCount() > 0 && roundType != null) {
				String orientation = ((HasOrientation) widget).getOrientation();
				Widget w = widget.getWidget(0);
				HasCorners c = (w instanceof HasCorners) ? (HasCorners) w
						: null;
				if (null != c) {
					if (null == orientation
							|| orientation
									.equals(HasOrientation.ORIENTATION_HORIZONTAL)) {
						if (roundType == HasCorners.ROUND_TYPE_ALL
								|| roundType == HasCorners.ROUND_TYPE_LEFT)
							c.setRoundingType("left");
						else if (roundType == HasCorners.ROUND_TYPE_TOP)
							c.setRoundingType("tl");
						else if (roundType == HasCorners.ROUND_TYPE_BOTTOM)
							c.setRoundingType("bl");
					} else {
						if (roundType == HasCorners.ROUND_TYPE_ALL
								|| roundType == HasCorners.ROUND_TYPE_TOP)
							c.setRoundingType("top");
						else if (roundType == HasCorners.ROUND_TYPE_LEFT)
							c.setRoundingType("tl");
						else if (roundType == HasCorners.ROUND_TYPE_RIGHT)
							c.setRoundingType("tr");
					}
				}
				w = widget.getWidget(widget.getWidgetCount() - 1);
				c = (w instanceof HasCorners) ? (HasCorners) w : null;
				if (null != c) {
					if (null == orientation
							|| orientation
									.equals(HasOrientation.ORIENTATION_HORIZONTAL)) {
						if (roundType == HasCorners.ROUND_TYPE_ALL
								|| roundType == HasCorners.ROUND_TYPE_RIGHT)
							c.setRoundingType("right");
						else if (roundType == HasCorners.ROUND_TYPE_TOP)
							c.setRoundingType("tr");
						else if (roundType == HasCorners.ROUND_TYPE_BOTTOM)
							c.setRoundingType("br");
					} else {
						if (roundType == HasCorners.ROUND_TYPE_ALL
								|| roundType == HasCorners.ROUND_TYPE_BOTTOM)
							c.setRoundingType("bottom");
						else if (roundType == HasCorners.ROUND_TYPE_LEFT)
							c.setRoundingType("bl");
						else if (roundType == HasCorners.ROUND_TYPE_RIGHT)
							c.setRoundingType("br");
					}
				}
			}
		}
	}
}
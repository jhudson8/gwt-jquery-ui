package com.google.gwt.jquery.mobile.ui;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.jquery.mobile.ui.base.AbstractBar;
import com.google.gwt.jquery.mobile.ui.face.HasActiveStatus;
import com.google.gwt.jquery.mobile.ui.face.HasMutableIcon;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;


public class NavBar extends AbstractBar implements ClickHandler {

	private UlPanel wrapper;
	private int max = 5;
	private boolean hasIcons = false;
	private boolean autoActivate = true;
	private HashMap<Widget, HandlerRegistration> registrations = new HashMap<Widget, HandlerRegistration>();
	
	public NavBar() {
		super("ui-navbar", "navbar", "navigation");
		wrapper = new UlPanel();
		super.add(wrapper);
	}

	public void setAutoActivate(boolean autoActivate) {
		this.autoActivate = autoActivate;
		if (autoActivate) {
			int count = getWidgetCount();
			for (int i=0; i<count; i++) {
				Widget w = getWidget(i);
				if (w instanceof HasClickHandlers && null == registrations.get(w)) {
					registrations.put(w, ((HasClickHandlers) w).addClickHandler(this));
				}
			}
		}
		else {
			for (HandlerRegistration registration : registrations.values())
				registration.removeHandler();
			registrations.clear();
		}
	}

	@Override
	public void add(Widget w) {
		LiPanel li = new LiPanel();
		li.add(w);
		wrapper.add(li);
		updateItems(false);
		if (autoActivate && w instanceof HasClickHandlers)
			registrations.put(w, ((HasClickHandlers) w).addClickHandler(this));
	}

	@Override
	public boolean remove(int index) {
		if (autoActivate) {
			Widget w = ((LiPanel) getWidget(index)).getWidget();
			HandlerRegistration registration = registrations.get(w);
			if (null != registration)
				registration.removeHandler();
		}
		return super.remove(index);
	}

	@Override
	public boolean remove(Widget w) {
		Widget toRemove = null;
		int count = getWidgetCount();
		for (int i=0; i<count; i++) {
			Widget _w = realWidget(i);
			if (_w.equals(w)) {
				toRemove = getWidget(i);
			}
		}
		if (null == toRemove) return false;
		
		if (autoActivate) {
			HandlerRegistration registration = registrations.get(w);
			if (null != registration)
				registration.removeHandler();
		}
		return super.remove(toRemove);
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	protected void onAttach() {
		updateItems(true);
		super.onAttach();
	}

	private void updateItems(boolean force) {
		if (force || isAttached()) {
			hasIcons = false;
			int count = wrapper.getWidgetCount();
			wrapper.getElement().setAttribute("class", "ui-grid-" + toVal(Math.max(0, count-2)));
			int _max = Math.min(count, max);
			for (int i=0; i<count; i++) {
				Widget widget = wrapper.getWidget(i);
				int x = i % _max;
				widget.getElement().setAttribute("class", "ui-block-" + toVal(x));

				Widget w = ((SimplePanel) widget).getWidget();
				if (w instanceof HasMutableIcon) {
					boolean hasIcon = (null != ((HasMutableIcon) w).getIcon());
					if (hasIcon) {
						hasIcons = true;
						if (null == ((HasMutableIcon) w).getIconPos())
							((HasMutableIcon) w).setIconPos(HasMutableIcon.ICON_POS_TOP);
					}
				}
			}
		}
	}

	private String toVal(int val) {
		if (val == 0) return "a";
		else if (val == 1) return "b";
		else if (val == 2) return "c";
		else if (val == 3) return "d";
		else return "e";
	}

	private static class LiPanel extends SimplePanel {
		private LiPanel() {
			super(DOM.createElement("li"));
		}
	}

	@Override
	protected String getDefaultStyleName() {
		if (hasIcons)
			return super.getDefaultStyleName();
		else {
			return super.getDefaultStyleName() + " ui-navbar-noicons";
		}
	}

	private Widget realWidget(int index) {
		return ((LiPanel) wrapper.getWidget(index)).getWidget();
	}

	private int realIndex(Widget w) {
		int count = wrapper.getWidgetCount();
		for (int i=0; i<count; i++) {
			Widget _w = realWidget(i);
			if (_w.equals(w))
				return i;
		}
		return -1;
	}

	private void removeActiveItem() {
		int count = wrapper.getWidgetCount();
		for (int i=0; i<count; i++) {
			Widget w = realWidget(i);
			if (w instanceof HasActiveStatus)
				((HasActiveStatus) w).setActive(false);
		}
	}
	
	public boolean activate(int index) {
		removeActiveItem();
		Widget w = realWidget(index);
		if (null != w && w instanceof HasActiveStatus) {
			((HasActiveStatus) w).setActive(true);
			return true;
		}
		return false;
	}

	public boolean activate(Widget w) {
		removeActiveItem();
		int index = realIndex(w);
		if (w instanceof HasActiveStatus && index >= 0) {
			((HasActiveStatus) w).setActive(true);
			return true;
		}
		return false;
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() instanceof Widget)
			activate((Widget) event.getSource());
	}
}
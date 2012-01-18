package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.GwtEvent;

public class ScrollStopEvent extends GwtEvent<ScrollStopHandler> {

	/**
	 * Handler type.
	 */
	private static Type<ScrollStopHandler> TYPE;

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ScrollStopHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<ScrollStopHandler>();
		}
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollStopHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(ScrollStopHandler handler) {
		handler.onScrollStop(this);
	}
}
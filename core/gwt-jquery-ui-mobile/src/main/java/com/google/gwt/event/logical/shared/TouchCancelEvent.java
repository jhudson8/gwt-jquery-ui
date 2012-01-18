package com.google.gwt.event.logical.shared;

public class TouchCancelEvent extends AbstractTouchEvent<TouchCancelHandler> {

	private static final Type<TouchCancelHandler> TYPE = new Type<TouchCancelHandler>(
			"touchcancel", new TouchCancelEvent());

	public static Type<TouchCancelHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<TouchCancelHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TouchCancelHandler handler) {
		handler.onTouchCancel(this);
	}
}

package com.google.gwt.event.logical.shared;

public class TouchEndEvent extends AbstractTouchEvent<TouchEndHandler> {

	private static final Type<TouchEndHandler> TYPE = new Type<TouchEndHandler>(
			"touchend", new TouchEndEvent());

	public static Type<TouchEndHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<TouchEndHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TouchEndHandler handler) {
		handler.onTouchEnd(this);
	}
}

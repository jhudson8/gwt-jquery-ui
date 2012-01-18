package com.google.gwt.event.logical.shared;

public class TouchMoveEvent extends AbstractTouchEvent<TouchMoveHandler> {

	private static final Type<TouchMoveHandler> TYPE = new Type<TouchMoveHandler>(
			"touchmove", new TouchMoveEvent());

	public static Type<TouchMoveHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<TouchMoveHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TouchMoveHandler handler) {
		handler.onTouchMove(this);
	}
}

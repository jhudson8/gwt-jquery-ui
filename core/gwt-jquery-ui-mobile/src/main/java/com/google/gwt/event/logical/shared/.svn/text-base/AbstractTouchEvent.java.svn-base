package com.google.gwt.event.logical.shared;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

abstract class AbstractTouchEvent<H extends EventHandler> extends DomEvent<H> {

    public JsArray<Touch> getTouches() {
        return touches(getNativeEvent());
    }

    public Touch getSingleTouch() {
    	return getTouches().get(0);
    }

    private native JsArray<Touch> touches(NativeEvent nativeEvent) /*-{
      return nativeEvent.touches;
    }-*/;
}
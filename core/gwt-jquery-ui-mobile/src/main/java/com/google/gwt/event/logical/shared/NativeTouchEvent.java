package com.google.gwt.event.logical.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class NativeTouchEvent extends JavaScriptObject {

	protected NativeTouchEvent() {}
	
    public final Touch getSingleTouch() {
    	return getTouches().get(0);
    }

    private final native JsArray<Touch> getTouches() /*-{
      return this.touches;
    }-*/;
}
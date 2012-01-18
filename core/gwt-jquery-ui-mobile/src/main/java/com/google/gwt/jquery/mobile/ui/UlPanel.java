package com.google.gwt.jquery.mobile.ui;

import com.google.gwt.dom.client.Node;
import com.google.gwt.jquery.mobile.ui.base.ComplexPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class UlPanel extends ComplexPanel {
	  /**
	   * Creates an empty flow panel.
	   */
	  public UlPanel() {
	    setElement(DOM.createElement("ul"));
	  }

	  /**
	   * Adds a new child widget to the panel.
	   * 
	   * @param w the widget to be added
	   */
	  @Override
	  public void add(Widget w) {
	    add(w, getElement());
	  }

	  @Override
	  public void clear() {
	    try {
	      doLogicalClear();
	    } finally {
	      // Remove all existing child nodes.
	      Node child = getElement().getFirstChild();
	      while (child != null) {
	        getElement().removeChild(child);
	        child = getElement().getFirstChild();
	      }
	    }
	  }

	  /**
	   * Inserts a widget before the specified index.
	   * 
	   * @param w the widget to be inserted
	   * @param beforeIndex the index before which it will be inserted
	   * @throws IndexOutOfBoundsException if <code>beforeIndex</code> is out of
	   *           range
	   */
	  public void insert(Widget w, int beforeIndex) {
	    insert(w, getElement(), beforeIndex, true);
	  }
	}
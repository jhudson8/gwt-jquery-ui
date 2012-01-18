package com.google.gwt.event.logical.shared;

public class TouchEvent {

	public static final int ONTOUCHSTART = 0x100000;
	public static final int ONTOUCHMOVE = 0x200000;
	public static final int ONTOUCHCANCEL = 0x400000;	
	public static final int ONTOUCHEND = 0x800000;
	public static final int TOUCHEVENTS = ONTOUCHSTART | ONTOUCHMOVE | ONTOUCHCANCEL | ONTOUCHEND;
}

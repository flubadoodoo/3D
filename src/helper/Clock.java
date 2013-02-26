package helper;

import org.lwjgl.Sys;

public class Clock {
	
	/**
	 * Static method used to get the current system time
	 * @return the current system time in milliseconds
	 * */
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}

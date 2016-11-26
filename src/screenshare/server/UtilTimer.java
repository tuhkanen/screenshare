package screenshare.server;

import java.util.Hashtable;

public class UtilTimer {
	static Hashtable<String, Long> timers;
	
	private static void init() {
		if( UtilTimer.timers == null ) {
			UtilTimer.timers = new Hashtable<String, Long>();
		}
	}
	
	public static void start( String key ) {
		UtilTimer.init();
		
		long time = System.currentTimeMillis();
		UtilTimer.timers.put( key, time );
	}
	
	public static long stop( String key ) {
		long stop = System.currentTimeMillis();
		
		if( UtilTimer.timers.containsKey( key ) ) {
			long start = UtilTimer.timers.get( key );
			return stop - start;
		}
		
		return -1;
	}
}

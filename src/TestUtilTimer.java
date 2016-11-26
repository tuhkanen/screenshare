import static org.junit.Assert.*;

import org.junit.Test;

import screenshare.server.UtilTimer;


public class TestUtilTimer {

	@Test
	public void testIncrement() {
		UtilTimer.start( "foo");
		long time = UtilTimer.stop( "foo" );
		
		if( time < 0 )
			fail("Timer did not increase");
	}
	
	@Test
	public void testIncrementOfOneSecond() {
		UtilTimer.start( "foo");
		try {
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			fail("Thread could not sleep");
		}
		long time = UtilTimer.stop( "foo" );
		
		if( time < 1000 )
			fail("Timer did not increase");
	}
	
	@Test
	public void testMissingKey() {
		UtilTimer.start( "foo" );
		long time = UtilTimer.stop( "bar" );

		if( time > 0 )
			fail("Time measured for a key that does not exist");
	}
}

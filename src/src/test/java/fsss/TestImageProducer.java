package fsss;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Before;
import org.junit.Test;

import screenshare.common.ImageWrapper;
import screenshare.common.Parameters;
import screenshare.server.ImageProducer;

public class TestImageProducer {

	ConcurrentLinkedQueue<ImageWrapper> capturedImages;
	Parameters p;
	ImageProducer ip;
	
	@Before
	public void setUp() throws Exception {
		capturedImages = new ConcurrentLinkedQueue<ImageWrapper>();
		p = new Parameters("client", 9999, "localhost", 200, 200, 100, 100, 10);
		ip = new ImageProducer(capturedImages, p);
	}

	@Test
	public void testNoImages() {
		assertTrue( capturedImages.isEmpty() );	
	}
	
	@Test
	public void testBasicImageProduction() {
		try {
			new Thread(ip).start();
			Thread.sleep(500);
			ip.stop();
			
		} catch (InterruptedException e) {
			fail("Exception");
			e.printStackTrace();
		}
		System.out.println( capturedImages.size() );
		assertTrue( ! capturedImages.isEmpty() );
		//assertTrue(capturedImages.size() == 123);
		
	}


}

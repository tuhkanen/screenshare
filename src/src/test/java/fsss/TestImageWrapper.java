package fsss;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import screenshare.common.ImageWrapper;

public class TestImageWrapper {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSerialization() {
		BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		ImageWrapper iw = new ImageWrapper( bi );
		
		String filePath = "serialization_file.tmp";
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject( iw );
			oos.close();
			
			File f = new File( filePath );
			f.delete();
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception");	
		}
		
	}

}

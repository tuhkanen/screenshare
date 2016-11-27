package screenshare.client;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import screenshare.server.ScreenCaptureHelper;


public class ImageDisplayWindow {
	private JFrame frame;
	private JPanel panel;
	private JLabel pictureLabel;
	
	ImageDisplayWindow() {
		this.frame = new JFrame();
		this.panel = new JPanel( new BorderLayout() );
		this.frame.getContentPane().add( this.panel );
		this.frame.setSize( 600, 600 );
		this.frame.setVisible( true );
	}
	
	public void show( Image image ) {
		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(image, 0, 0, null);
	    bGr.dispose();

	    this.show( bimage );
	}
	
	public void show( BufferedImage image ) {
		if( this.pictureLabel != null )
			this.panel.remove( this.pictureLabel );
		
		// Resize the image
		BufferedImage tmp = new BufferedImage(800, 800, image.getType());
        Graphics2D g2 = tmp.createGraphics();
        //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
        g2.drawImage(image, 0, 0, 600, 600, null);
        g2.dispose();
		
		this.pictureLabel = new JLabel( new ImageIcon( tmp ));
		this.panel.add( pictureLabel, BorderLayout.CENTER );
		this.frame.revalidate();
		//this.frame.repaint();
		this.panel.repaint();
	}
	
	public static void showTestImage() {
		try {
			ImageDisplayWindow window = new ImageDisplayWindow();
			BufferedImage image = ScreenCaptureHelper.capture(500, 500, 500, 500);
			window.show( image );
		} catch (AWTException e) {
			System.out.println("Failed to show the test image");
			e.printStackTrace();
		}
	}
	
	public static void showEmptyImage() {
		ImageDisplayWindow window = new ImageDisplayWindow();
		window.frame.setVisible( true );
	}
}

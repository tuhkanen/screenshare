package screenshare.client;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import screenshare.common.ImageWrapper;
import screenshare.common.Parameters;



public class ShareClient {
	
	private ImageDisplayWindow display;
	
	public ShareClient() {
		display = new ImageDisplayWindow();
		
	}
		
	public void start( Parameters param ) {
		//System.out.println("Client started");
		SocketChannel sChannel;
		try {
			sChannel = SocketChannel.open();
			
			if (sChannel.connect(new InetSocketAddress( param.ip, param.port))) {

	    		ObjectInputStream ois = 
	                     new ObjectInputStream(sChannel.socket().getInputStream());

	    		ObjectOutputStream ous = new ObjectOutputStream( sChannel.socket().getOutputStream() );
	    			    		
	    		ous.writeObject( param );
	    		String s = (String)ois.readObject();	// This is handshake message
	    		//System.out.println("Received: '" + s + "'");
	    		
	    		//ImageIcon icon;
	    		LinkedList<ImageWrapper> tmp = new LinkedList<ImageWrapper>();
	    		/*
	    		 * Keep reading the new image and updating the image on the screen.
	    		 */
	    		while( true ) {
		    		//image = ImageIO.read( ois );
	    			tmp = (LinkedList<ImageWrapper>)ois.readObject();
	    			//icon.getImage();
		    		//image = (BufferedImage)ois.readObject();
		    		if ( tmp == null ) {
		    			Thread.sleep( param.delay );
		    			System.out.println("c");
		    		} else {
		    			//System.out.println( "Received image: " + image.getHeight() + "/" + image.getWidth() );
		    			for( ImageWrapper image : tmp ) {
		    				if( image != null ) {
				    			display.show( image.getImage() );
				    			Thread.sleep( param.delay );		    					
		    				} else {
		    				  System.out.println("n");
		    				}

		    			}

		    			System.out.println("C(" + tmp.size() + ")");
		    		}
	    		}
	    				
	    		//System.out.println("Press enter to end.");
	    		//System.in.read();
	    	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("Client End");

	}

}

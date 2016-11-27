
/*
 * Class to take screenshots and feed them to the socket channel
 */

package screenshare.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;

import screenshare.common.ImageWrapper;
import screenshare.common.Parameters;

public class ServerImageFeeder implements Runnable  {

	SocketChannel sChannel;
	ConcurrentLinkedQueue<ImageWrapper> capturedImages;
	
	public ServerImageFeeder(SocketChannel sChannel) {
			this.sChannel = sChannel;
			this.capturedImages = new ConcurrentLinkedQueue<ImageWrapper>();
	}
	
	public void run() {
		ObjectOutputStream oos;
		ObjectInputStream ois;
		
			try {
				oos = new ObjectOutputStream(sChannel.socket().getOutputStream());
				ois = new ObjectInputStream( sChannel.socket().getInputStream());
			
				Parameters param;
				param = (Parameters)ois.readObject();

				String message = "The message";
				oos.writeObject( message );
				
				try {
					/*
					 * Start image producer
					 */
					new Thread(new ImageProducer(this.capturedImages, param)).start();;
					/*
					 * Start to consume produced images
					 * Keep writing the image to the connection on defined intervals
					 */
					while( true ) {
						//BufferedImage image = ScreenCaptureHelper.capture(param.x, param.y, param.width, param.height);
						//ImageIcon icon = new ImageIcon(image);
						//ImageIO.write(image, "png", oos);
						
						LinkedList<ImageWrapper> tmp = new LinkedList<ImageWrapper>();
						for( int i=0; i < this.capturedImages.size() || i < 10; i++ ) {
							tmp.add( this.capturedImages.poll() );
						}
						if( !tmp.isEmpty() ) {
							UtilTimer.start("WriteToSocket");
							oos.writeObject( tmp );
							System.out.println( "Time to write to socket" + UtilTimer.stop("WriteToSocket") );
						}

						
						System.out.println("S(" + tmp.size() + ")");
						Thread.sleep( param.delay );
					}
				} catch (InterruptedException e1) {
					System.out.println("Could not read the parameters!");
					e1.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( ClassNotFoundException e ) {
				e.printStackTrace();
			}
		
	}
}

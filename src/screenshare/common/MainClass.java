
/*
 * Class to launch the applicaiton as server or client based on the parameters
 */

package screenshare.common;
import screenshare.client.ImageDisplayWindow;
import screenshare.client.ShareClient;
import screenshare.server.ShareServer;


public class MainClass {

	public static void main(String[] args) {
		System.out.println("Setting property: headless false");
		System.setProperty("java.awt.headless", "false");
		
			System.out.println("Start");
			System.out.println("Args length: " + args.length );
			
			try {
				Parameters param = Parameters.parseParameters( args );
				
				if( param.isServer() ) {
					ShareServer server = new ShareServer();
					server.start( param.port );
				} else if( param.isClient() ) {
					ShareClient client = new ShareClient();
					while( true ) {
						client.start( param );
						try {
							Thread.sleep( param.delay );
							System.out.print(".");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} else if( param.type == "test" ) {
					ImageDisplayWindow.showTestImage();
					
				} else {
					System.out.println("Unknown type");
					System.out.println("Args: [type] [port], Example: server 9999");
					System.out.println("Args: [type] [port] [x] [y] [width] [height] [fps], Example: client 9999 192.168.1.10 200 200 300 300 30");
				}
			} catch (Exception e1) {
				System.out.println("Args: [type] [port], Example: server 9999");
				System.out.println("Args: [type] [port] [x] [y] [width] [height] [fps], Example: client 9999 192.168.1.10 200 200 300 300 30");
				e1.printStackTrace();
			}
	}
}

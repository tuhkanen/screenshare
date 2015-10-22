package screenshare.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ShareServer {
	public void start(int port) {
		System.out.println("Server start");
		
		ServerSocketChannel ssChannel = null;
		try {
			ssChannel = ServerSocketChannel.open();
			ssChannel.bind(new InetSocketAddress(port));
			
			while( true ) {
				SocketChannel sChannel = ssChannel.accept();
				System.out.println("Client connected.");
				new Thread(new ServerImageFeeder(sChannel)).start();
			}
		} catch (IOException e1) {
			System.out.println("Failed to start server.");
			e1.printStackTrace();
		} finally {
			if( ssChannel != null )
				try {
					ssChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}

		System.out.println("Server end.");
	}
}

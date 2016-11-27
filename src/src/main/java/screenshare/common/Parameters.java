package screenshare.common;
import java.io.Serializable;

public class Parameters implements Serializable {
	
	public String type;
	
	public int port;
	public String ip;

	public int x;
	public int y;
	
	public int width;
	public int height;
	
	public int delay;
	
	public Parameters(String type, int port) {
		this.type = type;
		this.port = port;
	}
	
	public Parameters(String type, int port, String ip, int x, int y, int length, int height, int delay) {
		this.type = type;
		this.ip = ip;
		this.port = port;
		
		this.x = x;
		this.y = y;
		
		this.width = length;
		this.height = height;
		
		this.delay = delay;
	}
	
	public static Parameters parseParameters( String args[] ) throws Exception {
		if( args.length == 2 ) {
			return new Parameters( args[0], Integer.parseInt( args[1] ));
		} else if( args.length == 8) {
			return new Parameters( 
					args[0], 
					Integer.parseInt(args[1]), 
					args[2], 
					Integer.parseInt(args[3]),
					Integer.parseInt(args[4]), 
					Integer.parseInt(args[5]), 
					Integer.parseInt(args[6]),
					Integer.parseInt(args[7]));
		} else {
			throw new Exception();
		}
	}
	
	public boolean isServer() {
		return this.type.equals("server");
	}
	
	public boolean isClient() {
		return this.type.equals("client");
	}
}

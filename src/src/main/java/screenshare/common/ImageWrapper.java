package screenshare.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageWrapper implements Serializable{
	private transient BufferedImage image;
	
	public ImageWrapper ( BufferedImage img ) {
		this.image = img;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(image, "png", out);
	}
	
	 private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
		 in.defaultReadObject();
		 this.image = ImageIO.read(in);
	 }
}

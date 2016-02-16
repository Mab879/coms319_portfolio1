package swingingWeather;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private Image myImage;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		Image image =  null;
		try {
			image = Network.requestRadar(49083);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImagePanel IP = new ImagePanel(image);
		frame.add(IP);
		 frame.setSize(400,400);

		frame.setVisible(true);
		
		IP.draw(frame.getGraphics());

	}
	
	
	public ImagePanel(Image initial){
		
		this.myImage = initial; 
	}
	
	public void draw(Graphics g){
		g.drawImage(myImage, 0,0,null);

	}
	
	public void setImage(Image image){
		this.myImage = image;
		
	}
}

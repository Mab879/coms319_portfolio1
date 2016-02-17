package swingingWeather;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private Image [] myImage;
	private int X;
	private int Y;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		ImagePanel IP=null; 
				//Image[] image= new Image[6];
		try {
			IP = new ImagePanel(Network.requestRadar(49083));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		frame.setLayout(new BorderLayout());
		int size = 700;
		frame.setSize(size,size);

		frame.setVisible(true);
		frame.add(IP);

		
			IP.draw(frame.getGraphics());
		
		
	//ImagePanel IP = new ImagePanel(image[0]);
		//ImagePanel IP2 = new ImagePanel(image[1]);
		//frame.add(IP);
		///frame.add(IP2);

		
		
		

	}
	public ImagePanel(){
		
		this.myImage = null; 
		this.X = 0;
	}
	
	public ImagePanel(Image [] initial){
		
		this.myImage = initial; 
		this.X = 0;
	}
	
	
	public void draw(Graphics g){
		//for(int i=0; i<myImage.length; i++){
			g.drawImage(myImage[0],this.getX(),this.getY(),null);
			
		//}

	}
	
	public void setImages(Image [] image){
		this.myImage = image;
		
	}
}

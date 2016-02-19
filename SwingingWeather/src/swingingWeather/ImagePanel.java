
package swingingWeather;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 * 
 * Panel that displays radar Images
 *
 */
public class ImagePanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private Image [] myImage;
	private int myIiterator;
	private Timer radarTimer;
	private long interval = 300;
	private TimerTask loopRadar;
	private boolean isTimerRunning;

	public ImagePanel(){
		this.myImage = null; 
		this.radarTimer = new Timer();
		isTimerRunning = true;
	}
	
	
	public void draw(Graphics g){
		int myY = this.getY();
		int myX = this.getX();
		myIiterator = myImage.length-1;
		
		loopRadar = new TimerTask() {
			@Override
			public void run() {
				if(isTimerRunning){
					g.drawImage(myImage[myIiterator],myX,myY+70,null);
					if(myIiterator > 1)
						myIiterator--;
					else
						myIiterator = myImage.length-1;
				}
			}
	     };
		
	     this.radarTimer.scheduleAtFixedRate(loopRadar, 0, interval); 

	}
	
	public void setImages(Image [] image){
		this.myImage = image;
		
	}
	
	public void toggleLoop(){
		if(isTimerRunning)
			stopLoop();
		else
			startLoop();
	}
	public void stopLoop(){

		if(isTimerRunning){
			isTimerRunning = false;
		}
	}
	public void startLoop(){
		isTimerRunning = true;
	}
	
	public void setLoopSpeed(Long newInterval){
		this.interval=newInterval;
	}
}







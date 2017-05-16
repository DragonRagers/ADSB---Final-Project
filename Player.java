package net.mrpaul.MB190.finalProject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Polygon implements KeyListener{
	private int stepSize = 4;
	private boolean forward= false;
	private boolean right= false;
	private boolean left = false;	
	private boolean down = false;
	
	public Player(Point[] inShape, Point inPosition, double inRotation){
		super(inShape, inPosition, inRotation);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP){
			forward = true;
		}
		if(keyCode == KeyEvent.VK_LEFT){
			left= true;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			right= true;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP){
			forward = false;
		}
		if(keyCode == KeyEvent.VK_LEFT){
			left= false;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			right= false;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void move(){
		if (forward && right) {
			position.x+=stepSize * Math.sqrt(2) / 2;
			position.y-=stepSize * Math.sqrt(2) / 2;
		} 
		else if (forward && left){
			position.x-=stepSize * Math.sqrt(2) / 2;
			position.y-=stepSize * Math.sqrt(2) / 2;
		}
		else if (down && left){
			position.x-=stepSize * Math.sqrt(2) / 2;
			position.y+=stepSize * Math.sqrt(2) / 2;
		}
		else if (down && right){
			position.x+=stepSize * Math.sqrt(2) / 2;
			position.y+=stepSize * Math.sqrt(2) / 2;
		}
		else if (forward){
			position.y-=stepSize;
		}
		else if (right){
			position.x+=stepSize;
		}
		else if (left){
			position.x-=stepSize;
		}
		else if(down){
			position.y+=stepSize;
		}
	}
}

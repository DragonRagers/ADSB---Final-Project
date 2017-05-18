package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Polygon implements KeyListener{
	private int stepSize = 4;
	private boolean forward= false;
	private boolean right= false;
	private boolean left = false;	
	private boolean down = false;
	private Image character;
	
	public Player(Point[] inShape, Point inPosition, double inRotation){
		super(inShape, inPosition, inRotation);
		try {
			character = ImageIO.read(new File("down.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W){
			forward = true;
			try {
				character = ImageIO.read(new File("up.png"));
			} catch (IOException e1) {
			}
		}
		if(keyCode == KeyEvent.VK_A){
			left= true;
			try {
				character = ImageIO.read(new File("left.png"));
			} catch (IOException e1) {
			}
		}
		if(keyCode == KeyEvent.VK_D){
			right= true;
			try {
				character = ImageIO.read(new File("right.png"));
			} catch (IOException e1) {
			}
		}
		if(keyCode == KeyEvent.VK_S){
			down = true;
			try {
				character = ImageIO.read(new File("down.png"));
			} catch (IOException e1) {
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W){
			forward = false;
		}
		if(keyCode == KeyEvent.VK_A){
			left= false;
		}
		if(keyCode == KeyEvent.VK_D){
			right= false;
		}
		if(keyCode == KeyEvent.VK_S){
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
		else {
			if (forward)
				position.y-=stepSize;
			if (right)
				position.x+=stepSize;

			if (left)
				position.x-=stepSize;

			if (down)
				position.y+=stepSize;

		}
	}
	
	public void paint (Graphics brush){
		brush.drawImage(character, (int) position.x, (int) position.y, 100, 100, null);
  }
}

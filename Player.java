package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Polygon implements KeyListener{
	private int stepSize = 4;
	//movement
	private boolean up= false;
	private boolean right= false;
	private boolean left = false;	
	private boolean down = false;
	//shootie things
	private boolean sUp = false;
	private boolean sRight = false;
	private boolean sLeft = false;
	private boolean sDown = false;
	ArrayList<PlayerBullet> bullets = new ArrayList<>();
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
			up = true;
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
		//shootah things
		if(keyCode == KeyEvent.VK_UP){
			sUp = true;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			sDown= true;
		}
		if(keyCode == KeyEvent.VK_LEFT){
			sLeft = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			sRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W){
			up = false;
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
		//shootah things
		if(keyCode == KeyEvent.VK_UP){
			sUp = false;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			sDown= false;
		}
		if(keyCode == KeyEvent.VK_LEFT){
			sLeft = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			sRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void move(){
		if (up && right) {
			position.x+=stepSize * Math.sqrt(2) / 2;
			position.y-=stepSize * Math.sqrt(2) / 2;
		} 
		else if (up && left){
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
		else if (up){
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
		constrain();
	}

	public void shoot(){
		if (sLeft || sRight || sUp || sDown) {
			PlayerBullet b = new PlayerBullet(new Point(position.x, position.y),sLeft, sRight, sUp, sDown); 
			bullets.add(b);
		}
	}

	public ArrayList<PlayerBullet> getBullets(){
		return bullets;
	}

	public void paint (Graphics brush){
		brush.drawImage(character, (int) position.x - 50, (int) position.y - 50, 100, 100, null);
	}
}

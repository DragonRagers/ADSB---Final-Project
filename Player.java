package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Polygon implements KeyListener{
	private int stepSize = 6;
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
	public int bulletDmg = 10;
	ArrayList<PlayerBullet> bullets = new ArrayList<>();
	private int health = 6;
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
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_L) {
			health--;
		}
		
		
		if(keyCode == KeyEvent.VK_W){
			up = true;
		}
		if(keyCode == KeyEvent.VK_A){
			left= true;
		}
		if(keyCode == KeyEvent.VK_D){
			right= true;
		}
		if(keyCode == KeyEvent.VK_S){
			down = true;
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

	public boolean shoot(){
		if (sLeft) {
			try {
				character = ImageIO.read(new File("left.png"));
			} catch (IOException e1) {
			}
		} else if (sRight) {
			try {
				character = ImageIO.read(new File("right.png"));
			} catch (IOException e1) {
			}
		} else if (sUp) {
			try {
				character = ImageIO.read(new File("up.png"));
			} catch (IOException e1) {
			}
		} else if (sDown) {
			try {
				character = ImageIO.read(new File("down.png"));
			} catch (IOException e1) {
			}
		}		
		else if (left) {
			try {
				character = ImageIO.read(new File("left.png"));
			} catch (IOException e1) {
			}
		} else if (right) {
			try {
				character = ImageIO.read(new File("right.png"));
			} catch (IOException e1) {
			}
		} else if (up) {
			try {
				character = ImageIO.read(new File("up.png"));
			} catch (IOException e1) {
			}
		} else if (down) {
			try {
				character = ImageIO.read(new File("down.png"));
			} catch (IOException e1) {
			}
		}
		if (sLeft || sRight || sUp || sDown) {
			PlayerBullet b = new PlayerBullet(new Point(position.x, position.y + 15),sLeft, sRight, sUp, sDown); 
			bullets.add(b);
			return true;
		}
		return false;
	}

	public ArrayList<PlayerBullet> getBullets(){
		return bullets;
	}

	public void paint (Graphics brush){
		if (health <= 0) {
			try {
				character = ImageIO.read(new File("boom.png"));
			} catch (IOException e) {
			}
		}
		//super.paint(brush);
		brush.drawImage(character, (int) position.x - 20, (int) position.y - 25, 100, 100, null);
	}
		

	public void damage(int dmg) {
		health -= dmg;
	}
	
	public int getHealth() {
		return health;
	}
}

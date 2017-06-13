package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;

public class PlayerBullet extends Player{
	private boolean dLeft = false;
	private boolean dRight = false;
	private boolean dUp = false;
	private boolean dDown = false;
	private int speed = 8;
	
	static Point[] bulletShape = {
			new Point(0,0),
			new Point(7,0),
			new Point(7,7),
			new Point(0,7)
	};

	public PlayerBullet(Point pos, boolean sLeft, boolean sRight, boolean sUp, boolean sDown) {
		super(bulletShape, pos, 180);
		dLeft = sLeft;
		dRight = sRight;
		dUp = sUp;
		dDown = sDown;
	}
	
	public void move() {
		if (dUp && dRight) {
			position.x+=speed * Math.sqrt(2) / 2;
			position.y-=speed * Math.sqrt(2) / 2;
		} 
		else if (dUp && dLeft){
			position.x-=speed * Math.sqrt(2) / 2;
			position.y-=speed * Math.sqrt(2) / 2;
		}
		else if (dDown && dLeft){
			position.x-=speed * Math.sqrt(2) / 2;
			position.y+=speed * Math.sqrt(2) / 2;
		}
		else if (dDown && dRight){
			position.x+=speed * Math.sqrt(2) / 2;
			position.y+=speed * Math.sqrt(2) / 2;
		}
		else if (dUp){
			position.y-=speed;
		}
		else if (dRight){
			position.x+=speed;
		}
		else if (dLeft){
			position.x-=speed;
		}
		else if(dDown){
			position.y+=speed;
		}
	}
	public void paint (Graphics brush){
		int length = this.getPoints().length;
		int[] xCoord = new int[length];
		int[] yCoord = new int[length];
		for(int i =0; i < length ; i++){
			xCoord[i] = (int) this.getPoints()[i].x;
			yCoord[i] = (int) this.getPoints()[i].y;
		}
		brush.fillPolygon(xCoord, yCoord, length);
  }
  
	public boolean isOutside() {
		return position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720;
	}
	
	public Point position() {
		return position;
	}
	
}


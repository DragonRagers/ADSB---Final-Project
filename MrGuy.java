package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MrGuy extends Polygon implements Enemy {
	private int health = 50;
	private static Point[] hitBox = {new Point(-40,-40),
			new Point(-40, 40),
			new Point(40,40),
			new Point(40, -40)};
	private Image guy;
	
	private int moveSpeed = 0;
	
	public MrGuy(Point inPosition, double inRotation) {
		super(hitBox, inPosition, inRotation);
		try {
			guy = ImageIO.read(new File("guyLeft.png"));
		} catch (IOException e) {
		}
	}

	

	public void paint(Graphics brush) {
		brush.drawImage(guy, (int) position.x - 25, (int) position.y - 25, 50, 50, null);
		//brush.drawRect((int) position.x - 25, (int) position.y - 25, 80, 80);
		brush.drawString("HP: " + health, (int) position.x - 20, (int) position.y - 30);
		brush.drawString("MrGuy", (int) position.x - 20, (int) position.y - 45);

	}
	
	public void move(Point PlayerPoint) {
		double angle = Math.atan2(PlayerPoint.y - position.y, PlayerPoint.x - position.x);
		angle = angle - angle % (Math.PI/4);
		//System.out.println(Math.toDegrees(angle));
		
		position.y += Math.sin(angle) * moveSpeed;
		position.x += Math.cos(angle) * moveSpeed;
	}

	public void attack() {}

	public void hit(int dmg) {
		health -= dmg;
	}

	public int getHealth() {
		return health;
	}
}

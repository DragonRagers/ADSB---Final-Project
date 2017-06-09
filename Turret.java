package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Turret extends Polygon implements Enemy{
	private static int health = 3;
	private double angle;
	private static Point[] hitBox = {
			new Point(0,0),
			new Point(0,60),
			new Point(80,60),
			new Point(80,0)
	};
	private Image turret;
	private ArrayList<TurretBullet> bullets = new ArrayList<TurretBullet>(); 
	private int attack = 0;
	
	private int attackSpeed = 30;
	
	public Turret(Point inPosition) {
		super(hitBox, inPosition, 0);
		try {
			turret = ImageIO.read(new File("turretLeft.png"));
		} catch (IOException e) {
		}
	}

	public void paint(Graphics brush) {
		//super.paint(brush);
		brush.drawImage(turret, (int) position.x - 30, (int) position.y, 90, 50, null);
		//brush.drawString("HP: " + health, (int) position.x - 10, (int) position.y - 15);
		//brush.drawString("MrGuy", (int) position.x - 5, (int) position.y - 30);
		//brush.draw3DRect((int) position.x - 5, (int) position.y - 5, 60, 60);
	}
	
	public void move(Point playerPoint) {
		if (angle > -Math.PI && angle < -Math.PI/2 || angle < Math.PI && angle > Math.PI/2) {
			try {
				turret = ImageIO.read(new File("turretLeft.png"));
			} catch (IOException e) {
			}
		} else {
			try {
				turret = ImageIO.read(new File("turretRight.png"));
			} catch (IOException e) {
			}
		}
	}

	public void attack(Player p, Graphics brush) {
		angle = Math.atan2(p.position.y - position.y, p.position.x - position.x);
		if (attack == 0) {
			bullets.add(new TurretBullet(position, angle));
			attack++;
		} else {
			attack++;
		}
		if (attack == attackSpeed) {
			attack = 0;
		}
		
		for (int c = 0; c < bullets.size(); c++) {
			bullets.get(c).move();
			bullets.get(c).paint(brush);
		}
	}

	public void hit(int dmg) {
		health -= dmg;
	}

	public int getHealth() {
		return health;
	}

	public ArrayList<TurretBullet> getBullets() {
		return bullets;
		
	}
}

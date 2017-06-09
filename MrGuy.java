package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MrGuy extends Polygon implements Enemy {
	private static Point[] hitBox = {new Point(-25,-25),
			new Point(-25, 25),
			new Point(25,25),
			new Point(25, -25)};
	private Image guy;
	private int charge = 0;
	private double angle;
	private int attack = 0;
	
	private int health = 50;
	private int moveSpeed = 2;

	public MrGuy(Point inPosition) {
		super(hitBox, inPosition, 0);
		try {
			guy = ImageIO.read(new File("guyLeft.png"));
		} catch (IOException e) {
		}
		angle = 0;
	}



	public void paint(Graphics brush) {
		//super.paint(brush);
		brush.drawImage(guy, (int) position.x - 10, (int) position.y - 10, 50, 50, null);
		brush.drawString("HP: " + health, (int) position.x - 10, (int) position.y - 15);
		brush.drawString("MrGuy", (int) position.x - 5, (int) position.y - 30);
		//brush.draw3DRect((int) position.x - 5, (int) position.y - 5, 60, 60);
	}

	public void move(Point PlayerPoint) {
		System.out.println(charge);
		if (moveSpeed == 2) {
			angle = Math.atan2(PlayerPoint.y - position.y, PlayerPoint.x - position.x);
		}
		if (angle < -7 * Math.PI / 8) {
		} else if (angle < -5 * Math.PI / 8) {
			angle = -3 * Math.PI / 4;
		} else if (angle < -3 * Math.PI / 8) {
			angle = -Math.PI / 2;
		} else if (angle < -1 * Math.PI / 8) {
			angle = -Math.PI / 4;
		} else if (angle < 1 * Math.PI / 8) {
			angle = 0;
		} else if (angle < 3 * Math.PI / 8) {
			angle = Math.PI / 4;
		} else if (angle < 5 * Math.PI / 8) {
			angle = Math.PI / 2;
		} else if (angle < 7 * Math.PI / 8) {
			angle = 3 * Math.PI / 4;
		} else {
			angle = Math.PI;
		}
		if (angle > -Math.PI && angle < -Math.PI/2 || angle < Math.PI && angle > Math.PI/2) {
			try {
				guy = ImageIO.read(new File("guyLeft.png"));
			} catch (IOException e) {
			}
		} else {
			try {
				guy = ImageIO.read(new File("guyRight.png"));
			} catch (IOException e) {
			}
		}

		//System.out.println(Math.toDegrees(angle));

		position.y += Math.sin(angle) * moveSpeed;
		position.x += Math.cos(angle) * moveSpeed;
	}

	public void attack(Player p, Graphics brush) {
		double distance = Math.sqrt(Math.pow(p.position.x - position.x, 2) + Math.pow(p.position.y - position.y, 2));
		if (distance <= 200 && charge == 0) {
			moveSpeed = 9;
			charge++;
		}
		if (charge > 0) {
			charge++;
		}
		if (charge == 40) {
			moveSpeed = 2;
		}
		if (charge == 100) {
			charge = 0;
		}
		
		if (attack == 0 && p.contains(position)) {
			p.damage(1);
			attack = 1;
		}
		if (attack > 0) {
			attack++;
		}
		if (attack == 30) {
			attack = 0;
		}
	}

	public void hit(int dmg) {
		health -= dmg;
	}

	public int getHealth() {
		return health;
	}
}

package net.mrpaul.MB190.finalProject;

import java.awt.Color;
import java.awt.Graphics;

public class TurretBullet extends Polygon{
	private double angle;
	private static Point[] hitBox = {
			new Point(0,0),
			new Point(7,0),
			new Point(7,7),
			new Point(0,7)
	
	};
	private int moveSpeed = 6;
	
	public TurretBullet(Point turretPos, double rotation) {
		super(hitBox, new Point(turretPos.x, turretPos.y), 0);
		angle = rotation;
	}
	
	public void paint(Graphics brush) {
		brush.setColor(Color.RED);
		brush.fillRect((int) position.x, (int) position.y, 7, 7);
	}
	
	public void move() {
		position.x += Math.cos(angle) * moveSpeed;
		position.y += Math.sin(angle) * moveSpeed;
	}

}

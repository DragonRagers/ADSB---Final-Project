package net.mrpaul.MB190.finalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestDummy extends Polygon implements Enemy{
	private int health = 999;
	private static Point[] hitBox = {new Point(-30,-30),
			new Point(-30, 30),
			new Point(30,30),
			new Point(30, -30)};
	private Image dummy;
	
	public TestDummy(Point inPosition, double inRotation) {
		super(hitBox, inPosition, inRotation);
		try {
			dummy = ImageIO.read(new File("up.png"));
		} catch (IOException e) {
		}

	}

	public void paint(Graphics brush) {
		//super.paint(brush);
		brush.drawImage(dummy, (int) position.x - 20, (int) position.y - 25, 100, 100, null);
		//brush.drawRect((int) position.x - 25, (int) position.y - 25, 80, 80);
		brush.drawString("HP: " + health, (int) position.x - 10, (int) position.y - 30);
		brush.drawString("Test Dummy", (int) position.x - 25, (int) position.y - 45);

	}

	public void move(Point PlayerPoint) {}

	public void attack(Player p, Graphics brush) {}

	public void hit(int dmg) {
		health--;
	}

	public int getHealth() {return health;}
}

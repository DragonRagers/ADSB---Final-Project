package net.mrpaul.MB190.finalProject;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class Abb extends Game {
	private Point[] playerPoints = {
			new Point(0,5),
			new Point(5,5),
			new Point(5,0),
			new Point(0,0)
	};
	static int height = 720;
	static int width = 1280;
	private Player p = new Player(playerPoints, new Point(width/2, height/2), 180);
	static int counter = 0;
	Image background;
	ArrayList<PlayerBullet> bullets;
	
	//things
	static int attackspeed = 12;
	

	public Abb() {
		super("Abb",width, height);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(p);
		try {
			background = ImageIO.read(new File("background.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bullets = p.getBullets();
	}

	public void paint(Graphics brush) {		
		brush.drawImage(background, 0, 0, 1280, 720, null);
		p.move();
		p.paint(brush);
		if (counter % attackspeed == 0) {
			p.shoot();
		}
		for(int c = 0; c < bullets.size(); c++){
			PlayerBullet b = bullets.get(c);
			b.move();
			b.paint(brush);
			if (b.isOutside()) {
				bullets.remove(c);
			}
		}
		
		brush.drawString("Counter: " + counter, 10, 10);
		brush.drawString("Bullets: " + bullets.size(), 10, 25);
		counter++;

	}

	public static void main (String[] args) {
		Abb a = new Abb();
		a.repaint();
	}
}
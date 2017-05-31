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
	private static final int height = 720;
	private static final int width = 1280;
	private Player p = new Player(playerPoints, new Point(width/2, height/2), 180);
	private static int counter = 0;
	private Image background;
	private ArrayList<PlayerBullet> bullets;
	private boolean canShoot;
	private int bulletTimer;
	private ArrayList<Enemy> enemies;

	//changeable things
	static final int attackspeed = 10;
	static int bulletDmg = 10;

	public Abb() {
		//construct things
		super("Abb",width, height);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(p);

		//loads background
		try {
			background = ImageIO.read(new File("background.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//preps variables
		bullets = p.getBullets();
		canShoot = true;
		bulletTimer = 0;
		enemies = new ArrayList<Enemy>();
		enemies.add(new TestDummy(new Point(width/2, height/2), 0));
	}

	public void level() {

	}


	public void paint(Graphics brush) {		
		//background
		brush.drawImage(background, 0, 0, 1280, 720, null);

		//player
		p.move();
		p.paint(brush);

		//bullets
		if (canShoot) {
			if (p.shoot()) {
				canShoot = false;
				bulletTimer = 0;
			}
		}
		if (bulletTimer < attackspeed) {
			bulletTimer++;
		} else {
			canShoot = true;
		}
		brush.setColor(Color.GREEN);
		for(int c = 0; c < bullets.size(); c++){
			PlayerBullet b = bullets.get(c);
			b.move();
			b.paint(brush);
			if (b.isOutside()) {
				bullets.remove(c);
			}
		}

		//enemy draw move
		brush.setColor(Color.RED);
		for (Enemy e: enemies) {
			e.move();
			e.attack();
			e.paint(brush);
		}
		
		//enemy hit detection
		brush.setColor(Color.RED);
		for (int b = 0; b < bullets.size(); b++) {
			for (int e = 0; e < enemies.size(); e++) {
				if (enemies.get(e).contains(bullets.get(b).position())) {
					bullets.remove(b);
					enemies.get(e).hit(bulletDmg);
					if (enemies.get(e).getHealth() <= 0) {
						enemies.remove(e);
					}
				}
			}
		}

		//game over?
		if (p.getHealth() <= 0) {
			brush.setColor(Color.RED);
			brush.drawString("Ay its game over noob", width/2 - 50, height/2);
		}

		//number display
		brush.setColor(Color.BLACK);
		brush.drawString("Counter: " + counter, 10, 40);
		brush.drawString("Bullets: " + bullets.size(), 10, 55);
		brush.drawString("BulletTimer: " + bulletTimer, 10, 70);
		//health bar
		brush.setColor(Color.GRAY);
		brush.fillRect(10, 10, 180, 15);
		brush.setColor(Color.RED);
		brush.fillRect(10, 10, p.getHealth()*30, 15);

		counter++;

	}

	public static void main (String[] args) {
		Abb a = new Abb();
		a.repaint();
	}
}
package adsb.finalproject.bdgub.bdcon;



import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class Abb extends Game {
	private Point[] playerPoints = {new Point(-30,-30),
			new Point(-30, 30),
			new Point(30,30),
			new Point(30, -30)
	};


	private static final int height = 720;
	private static final int width = 1280;
	private Player p = new Player(playerPoints, new Point(width/2- 10 , height/2 - 10), 180);
	private static int counter = 0; 
	private Image background;
	private ArrayList<PlayerBullet> bullets;
	private boolean canShoot;
	private int bulletTimer;
	private ArrayList<Enemy> enemies;

	//changeable things
	static final int attackspeed = 10; //inversely speeds up

	public Abb() {
		//construct things
		super("Abb",width, height);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(p);

		//loads background
		try {
			background = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//preps variables
		bullets = p.getBullets();
		canShoot = true;
		bulletTimer = 0;

		//enemy testing
		enemies = new ArrayList<Enemy>();
		//enemies.add(new TestDummy(new Point(width/2, height/2), 0));
		enemies.add(new MrGuy(new Point(100,100)));
		enemies.add(new MrGuy(new Point(width - 100, height - 100)));
		enemies.add(new MrGuy(new Point(width - 100, 100)));
		enemies.add(new MrGuy(new Point(100, height - 100)));

		enemies.add(new Turret(new Point(100,100)));
		enemies.add(new Turret(new Point(width - 100, height - 100)));
		enemies.add(new Turret(new Point(width - 100, 100)));
		enemies.add(new Turret(new Point(100, height - 100)));

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

		//bullet outta range
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
			e.attack(p, brush);
			e.move(p.position);
			e.paint(brush);
		}

		//enemy hit detection
		for (int e = 0; e < enemies.size(); e++) {
			for (int b = 0; b < bullets.size(); b++) {
				if(enemies.size() == 0)
					break;
				if(e < 0)
					e = 0;
				if (enemies.get(e).contains(bullets.get(b).position())) {
					bullets.remove(b);
					enemies.get(e).hit(p.bulletDmg);
					if (enemies.get(e).getHealth() <= 0) {
						enemies.remove(e);	
					}
					e--;
				}
			}
		}
		

		//le player hit
		//turret bullet hit
		for (int e = 0; e < enemies.size(); e++) {
			if (enemies.get(e) instanceof Turret) {
				for (int c = 0; c < ((Turret) enemies.get(e)).getBullets().size(); c++) {
					if (p.contains(((Turret) enemies.get(e)).getBullets().get(c).position)) {
						((Turret) enemies.get(e)).getBullets().remove(c);
						p.damage(1);
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
		brush.setColor(Color.RED);
		brush.drawString("Counter: " + counter, 10, 40);
		brush.drawString("Bullets: " + bullets.size(), 10, 55);
		brush.drawString("BulletTimer: " + bulletTimer, 10, 70);
		//health bar
		brush.setColor(Color.GRAY);
		brush.fillRect(10, 10, 180, 15);
		brush.setColor(Color.RED);
		brush.fillRect(10, 10, p.getHealth()*30, 15);

		counter++;

		if (enemies.isEmpty()) {
			level();
		}
		
		if (p.getHealth() < 0) {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
			}
			System.exit(0);
		}
	}

	public void level() {
		//this 2D array is where you can design what level is procedurally generated, 0 = nothing, 1 = TestDummy, 2 = MrGuy, 3 = Turret
		int[][] toLoad = {
				{0,0,0,0,0,0,0},	
				{0,2,0,0,0,3,0},
				{0,0,0,0,0,0,0},
				{0,3,0,0,0,2,0},
				{0,0,0,1,0,0,0},
		};
		enemies.clear();
		
		for (int r = 0; r < toLoad.length; r++) {
			for (int c = 0; c < toLoad[0].length; c++) {
				if (toLoad[r][c]  == 1) {
					enemies.add(new TestDummy(new Point(width/toLoad[0].length * c, height/toLoad.length * r), 0));
				} else if (toLoad[r][c]  == 2) {
					enemies.add(new MrGuy(new Point(width/toLoad[0].length * c, height/toLoad.length * r)));
				} else if (toLoad[r][c]  == 3) {
					enemies.add(new Turret(new Point(width/toLoad[0].length * c, height/toLoad.length * r)));
				}
			}
		}
		
	}
	
	public static void main (String[] args) {
		Abb a = new Abb();
		a.repaint();
	}
}

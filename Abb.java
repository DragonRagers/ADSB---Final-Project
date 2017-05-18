package adsb.finalproject.bdgub.bdcon;


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
	Image background;
	
	
	public Abb() {
		super("Abb",width, height);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(p);
		try {
			background = ImageIO.read(new File("background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics brush) {		
		brush.drawImage(background, 0, 0, null);
		p.move();
		p.constrain();
		p.shoot();
		ArrayList<PlayerBullet> bullets = p.getBullets();
		for(PlayerBullet b: bullets){
			b.move();
			b.paint(brush);
		}
		p.paint(brush);
	}

	public static void main (String[] args) {
		Abb a = new Abb();
		a.repaint();
	}
}

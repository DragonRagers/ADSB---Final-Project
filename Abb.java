package net.mrpaul.MB190.finalProject;


import java.awt.*;
import java.awt.event.*;

class Abb extends Game {
	static int counter = 0;
	private Point[] playerPoints = {
			new Point(0,5),
			new Point(5,5),
			new Point(5,0),
			new Point(0,0)
		};
	private Player p = new Player(playerPoints, new Point(400,300), 180);
  public Abb() {
    super("Abb",800,600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(p);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	
    	p.move();
    	p.constrain();
    	p.paint(brush);
  }
  
	public static void main (String[] args) {
   		Abb a = new Abb();
		a.repaint();
  }
}

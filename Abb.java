package net.mrpaul.MB190.finalProject;

import java.awt.*;
import java.awt.event.*;

class Abb extends Game {
	static int counter = 0;

  public Abb() {
    super("ABB!",1280,1720);
    this.setFocusable(true);
	this.requestFocus();
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
  }
  
	public static void main (String[] args) {
		Abb a = new Abb();
		a.repaint();
  }
}
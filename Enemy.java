package net.mrpaul.MB190.finalProject;

import java.awt.Graphics;

public interface Enemy{
	public void paint(Graphics brush);
	public void move(Point PlayerPoint);
	public void attack();
	public void hit(int dmg);
	public int getHealth();
	public boolean contains(Point point);
}

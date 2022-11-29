package pack;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Shape {
	
	static int shapesAlive = 0;
	//instance variables
	private String type;
	private int x, y;
	private int width, height;
	private JLabel selectedRect = new JLabel();//The graphics object
	
	//constructors
	Shape() {
		shapesAlive++;
		type = "Basic Shape";
		x = 0;
		y = 0;
		width = 10;
		height = 10;
		
		selectedRect.setBounds(x, y, width, height);
		selectedRect.setOpaque(true);
		
		Main.m.add(selectedRect);
		
	}
	Shape(int x1, int y1, int x2, int y2) {
		x = x1;
		y = y1;
		width = x2;
		height = y2;
		
		selectedRect.setBounds(x, y, width, height);
		selectedRect.setOpaque(true);
		
		Main.m.add(selectedRect);
	}
	
	//accessor methods
	public String getType() {
		return type;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	//mutator methods
	public void setX(int xx) {
		x = xx;
	}
	public void setY(int yy) {
		y = yy;
	}
	public void setType(String s) {
		type = s;
	}
	public void instantiateShape() {
		System.out.print("Drawign " + type);
	}
	
	public void moveRect(int xx, int yy) {
		setX(x + xx);
		setY(y + yy);
		selectedRect.setLocation(x, y);
	}
	
	public void setRectPosition(int xx, int yy) {
		selectedRect.setLocation(xx, yy);
	}
	public void setRectScale(int xx, int yy) {
		selectedRect.setBounds(x, y, xx, yy);
		System.out.println(x + ", " + y + ", " + width + ", " + height);
	}
	public void setColor(Color c) {
		selectedRect.setBackground(c);
	}
	
	public void updateIcon(ImageIcon i) {
		selectedRect.setIcon(i);
	}
	
}

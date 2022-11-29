package pack;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rectangle extends Shape {
	
	private int r = (int)(Math.random()*255);
	private int g = (int)(Math.random()*255);
	private int b = (int)(Math.random()*255);
	
	private String path = "src/assets/piece";
	
	private int chessPiece = 0; //0-5  0PAWN    1KNIGHT    2QUEEN    3BISHOP    4ROOK    5KING
	public boolean isChessPiece = false;
	public int p2Piece = 0;
	
	ImageIcon icon;
	Rectangle() {
		super();
	}
	
	Rectangle(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		setColor(new Color(r,g,b));
		
	}
	
	public int getChessId() {
		return chessPiece;
	}
	public void setChessPiece(int id) {
		isChessPiece = true;
		chessPiece = id;
		icon = new ImageIcon(path + p2Piece + id + ".png");
		updateIcon(icon);
	}
	public void removeChessPiece() {
		updateIcon(null);
		isChessPiece = false;
	}
	
	
	
}

package pack;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.List;
import java.math.*;

public class Main {

	public static MyFrame m;
	public static Controller c;

	private static final int RECTSIZE = 75;
	private static Rectangle[][] rectMatrix = new Rectangle[8][8];

	private static Rectangle selectedPiece;
	private static ArrayList<Rectangle> acceptedMoves = new ArrayList<Rectangle>();

	public static void main(String[] args) {

		c = new Controller();
		m = new MyFrame("Chess");
		createRecs();
		setAllAccepted();
		setBoard();
	}

	public static void createRecs() { // instantiate a group of rectangles to do whatever with.
		// summon rectangles
		for (int i = 0; i < rectMatrix.length; i++) {
			for (int j = 0; j < rectMatrix[0].length; j++) {
				rectMatrix[i][j] = new Rectangle((i * RECTSIZE), j * RECTSIZE, RECTSIZE + 1, RECTSIZE);
				rectMatrix[i][j]
						.setColor(new Color(((i + j + 1) % 2) * 255, ((i + j + 1) % 2) * 255, ((i + j + 1) % 2) * 255));
			}
		}
	}
	
	public static void setBoard() {
		
		for (int i = 0; i < 8; i++) {//set all below pieces to p2
			for (int j = 6; j < 8; j++) {
				rectMatrix[i][j].p2Piece = 1;
			}
		}
		for (int i = 0; i < 8; i++) {
			rectMatrix[i][1].setChessPiece(0);
			rectMatrix[i][6].setChessPiece(0);
		}
		
		rectMatrix[0][0].setChessPiece(4);
		rectMatrix[7][0].setChessPiece(4);
		rectMatrix[0][7].setChessPiece(4);
		rectMatrix[7][7].setChessPiece(4);
		
		rectMatrix[3][0].setChessPiece(5);
		rectMatrix[3][7].setChessPiece(5);
		
		rectMatrix[1][7].setChessPiece(1);
		rectMatrix[1][0].setChessPiece(1);
		rectMatrix[6][7].setChessPiece(1);
		rectMatrix[6][0].setChessPiece(1);
		rectMatrix[2][7].setChessPiece(3);
		rectMatrix[2][0].setChessPiece(3);
		rectMatrix[5][7].setChessPiece(3);
		rectMatrix[5][0].setChessPiece(3);
		rectMatrix[4][0].setChessPiece(2);
		rectMatrix[4][7].setChessPiece(2);
	}
	
	public static MyFrame getMyFrame() {
		return m;
	}

	public static void setSelectedPiece(Rectangle rr) {
		if (rr.isChessPiece) {
			selectedPiece = rr;
			//System.out.println("new piece selected");
		}
	}

	public static void tryMoveTo(Rectangle rr) {
		if (selectedPiece != null) {
			for (Rectangle rec : acceptedMoves) {
				if (rr.p2Piece != selectedPiece.p2Piece || !rr.isChessPiece)
				if (rec == rr) {
					moveTo(rr);
					break;
				}
			}
		}
	}

	private static void moveTo(Rectangle rr) {
		Controller.lastMove = selectedPiece.p2Piece;
		rr.p2Piece = selectedPiece.p2Piece;
		rr.setChessPiece(selectedPiece.getChessId());

		selectedPiece.removeChessPiece();
		selectedPiece = null;
		
	}

	public static Rectangle findRectByPixil(int xx, int yy) {

		int xg = (int) ((xx - (RECTSIZE / 2) + 30) / RECTSIZE);// -10 for center of rect
		int yg = (int) ((yy - (RECTSIZE / 2) + 5) / RECTSIZE);// -22 for title bar offset
		return rectMatrix[xg][yg];
	}

	public static String getPositionInMatrix(Rectangle rr) {
		for (int i = 0; i < rectMatrix.length; i++) {
			for (int j = 0; j < rectMatrix[0].length; j++) {
				if (rectMatrix[i][j] == rr)
					return (i + ", " + j);
			}
		}
		return "not in matrix";
	}

	public static void setAllAccepted() {// TESTING ---- remove
		for (Rectangle[] rows : rectMatrix) {
			for (Rectangle rec : rows) {
				acceptedMoves.add(rec);
			}
		}
	}
	
	public static void getMovableTiles() {
		if (selectedPiece != null) {
			int id = selectedPiece.getChessId();
			String[] pos = getPositionInMatrix(selectedPiece).split(", ");
			int x = Integer.parseInt(pos[0]);
			int y = Integer.parseInt(pos[1]);
			int direction = 1;
			if (selectedPiece.p2Piece != 0) {
				direction = -1;
			}
			
			acceptedMoves.removeAll(acceptedMoves);
			
			if (id == 0) {//pawn
				if (!rectMatrix[x][y+direction].isChessPiece)
					acceptedMoves.add(rectMatrix[x][y+direction]);
				if (x > 0) {
					if (rectMatrix[x-1][y+direction].isChessPiece)
						acceptedMoves.add(rectMatrix[x-1][y+direction]);
				}
				if (x < rectMatrix[0].length-1) {
					if (rectMatrix[x+1][y+direction].isChessPiece)
						acceptedMoves.add(rectMatrix[x+1][y+direction]);
				}
			} else if (id == 1) {//knight
				knightMoves(x, y);
			} else if (id == 2) {//queen
				bishopMoves(x, y);
				rookMoves(x, y);
				kingMoves(x, y);
			} else if (id == 3) {//bishop
				bishopMoves(x, y);
			} else if (id == 4) {//rook
				rookMoves(x, y);
			} else if (id == 5) {//king
				kingMoves(x, y);
			}
			
//			System.out.println(acceptedMoves.size());
//			for (Rectangle rr : acceptedMoves) {//---------------TEMP for debugging movement
//				if ((selectedPiece.p2Piece != rr.p2Piece && rr.isChessPiece) || !rr.isChessPiece) {
//					rr.updateIcon(new ImageIcon("src/assets/moveable.png"));
//				}
//			}
			
		}
	}
	
	private static void kingMoves(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x+i >= 0 && x+i < rectMatrix[0].length && y+j >= 0 && y+j < rectMatrix.length) {
					acceptedMoves.add(rectMatrix[x+i][y+j]);
				}
			}
		}
	}
	
	private static void rookMoves(int x, int y) {
		for (int dir = -1; dir <= 1; dir+=2) {
			for (int i = 1; i < rectMatrix.length; i++) {//get x directions
				
				Rectangle thisRect = rectMatrix[x][y];
				
				if (x+(i*dir) >= rectMatrix.length || x+(i*dir) < 0) {
					break;
				} else
					thisRect = rectMatrix[x+(i*dir)][y];
				
				if (thisRect.isChessPiece) {
					if (thisRect.p2Piece != selectedPiece.p2Piece)
						acceptedMoves.add(thisRect);
					break;
				} else {
					acceptedMoves.add(thisRect);
				}
			}
			for (int i = 1; i < rectMatrix.length; i++) {//get x directions
				
				Rectangle thisRect = rectMatrix[x][y];
				
				if (y+(i*dir) >= rectMatrix.length || y+(i*dir) < 0) {
					break;
				} else
					thisRect = rectMatrix[x][y+(i*dir)];
				
				if (thisRect.isChessPiece) {
					if (thisRect.p2Piece != selectedPiece.p2Piece)
						acceptedMoves.add(thisRect);
					break;
				} else {
					acceptedMoves.add(thisRect);
				}
			}
		}
	}
	
	private static void bishopMoves(int x, int y) {
		for (int dir = -1; dir <= 1; dir+=2) {
			for (int i = 1; i < rectMatrix.length; i++) {//get x directions
				
				Rectangle thisRect = rectMatrix[x][y];
				
				if (x+(i*dir) >= rectMatrix.length || x+(i*dir) < 0 || y+(i*dir) >= rectMatrix.length || y+(i*dir) < 0) {//if the rectangle is within the matrix
					break;
				} else
					thisRect = rectMatrix[x+(i*dir)][y+(i*dir)];//set it to that rectangle
				
				if (thisRect.isChessPiece) {//if it hits a piece stop
					if (thisRect.p2Piece != selectedPiece.p2Piece)//if the piece is on the other team, allow it to move there as well
						acceptedMoves.add(thisRect);
					break;
				} else {
					acceptedMoves.add(thisRect);
				}
			}
			for (int i = 1; i < rectMatrix.length; i++) {//get x directions
				
				Rectangle thisRect = rectMatrix[x][y];
				
				if (x+(i*dir) >= rectMatrix.length || x+(i*dir) < 0 || y+(i*-dir) >= rectMatrix.length || y+(i*-dir) < 0) {//if the rectangle is within the matrix
					break;
				} else
					thisRect = rectMatrix[x+(i*dir)][y+(i*-dir)];//set it to that rectangle
				
				if (thisRect.isChessPiece) {//if it hits a piece stop
					if (thisRect.p2Piece != selectedPiece.p2Piece)//if the piece is on the other team, allow it to move there as well
						acceptedMoves.add(thisRect);
					break;
				} else {
					acceptedMoves.add(thisRect);
				}
			}
		}
	}
	
	private static void knightMoves(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int xmult = 1;
			int ymult = 1;
			if (i == 1) {
				xmult = -1;
			} else if (i == 2) {
				ymult = -1;
			} else if (i == 3) {
				xmult = -1;
				ymult = -1;
			}
			if (((x+(1*xmult) < rectMatrix[0].length && xmult == 1) || (x+(1*xmult) >= 0 && xmult == -1)) && ((y+(2*ymult) < rectMatrix.length && ymult == 1) || (y+(2*ymult) >= 0 && ymult == -1)))
				acceptedMoves.add(rectMatrix[x+(1*xmult)][y+(2*ymult)]);
			if (((x+(2*xmult) < rectMatrix[0].length && xmult == 1) || (x+(2*xmult) >= 0 && xmult == -1)) && ((y+(1*ymult) < rectMatrix.length && ymult == 1) || (y+(1*ymult) >= 0 && ymult == -1)))
				acceptedMoves.add(rectMatrix[x+(2*xmult)][y+(1*ymult)]);
		}
	}
	

}

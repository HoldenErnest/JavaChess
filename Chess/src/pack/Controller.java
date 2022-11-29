package pack;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class Controller extends Component implements MouseListener {

	private int downX;
	private int downY;
	
	public static int lastMove = 1;//which player took the last move
	Controller() {
		super();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		downX = e.getX();
		downY = e.getY();
		if ((downX < MyFrame.SCREENWIDTH && downX > 0) && (downY < MyFrame.SCREENHEIGHT && downY > 0)) {
			if (SwingUtilities.isLeftMouseButton(e)) {//left click update selected
				//System.out.println(Main.getPositionInMatrix(Main.findRectByPixil(downX, downY)));
				if (Main.findRectByPixil(downX, downY).p2Piece != lastMove) {
					Main.setSelectedPiece(Main.findRectByPixil(downX, downY));
				if (Main.findRectByPixil(downX, downY).isChessPiece)
					Main.getMovableTiles();
				}
			} else if (SwingUtilities.isRightMouseButton(e)) {//right click try to move selected
				Main.tryMoveTo(Main.findRectByPixil(downX, downY));
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

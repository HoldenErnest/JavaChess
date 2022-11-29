package pack;

import java.awt.Color;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	public static final int SCREENWIDTH = 75*8+17;
	public static final int SCREENHEIGHT = 75*8+39;
	
	
	MyFrame(String s) {
		super(s);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(SCREENWIDTH, SCREENHEIGHT);
		this.setLayout(null);
		this.addMouseListener(Main.c);
		
		this.setVisible(true);
	}
	
}
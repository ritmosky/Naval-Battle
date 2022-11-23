package view;
import javax.swing.*;
import java.awt.*;


public class vuePlateau extends JFrame{
 
	public vuePlateau(){
		super();
		initFenetre();
	}
 
	private void initFenetre(){
		this.setTitle("NAVAL BATTLE");
		this.setSize(600,600);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

}
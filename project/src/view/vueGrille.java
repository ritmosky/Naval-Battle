package view;
import java.awt.*;
import javax.swing.*;    
import modele.*;
import java.awt.event.*;



public class vueGrille {
	JFrame frame; 

	public vueGrille(/*Grille grid,*/ int l, int c) {
		frame = new JFrame();
		frame.setSize(200, 200);  // l, c x 10		
		
		/*JPanel panelGridName = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel labelGrid = new JLabel("GRILLE 1", JLabel.CENTER);
		frame.add(labelGrid);
		frame.setVisible(true);  */
	      
	      
		JPanel panelGrid = new JPanel(new FlowLayout(FlowLayout.CENTER));
		GridLayout g = new GridLayout(l,c);
		//GridBagConstraints gbc = new GridBagConstraints();
		//JPanel panelGrid = new JPanel(new GridLayout(l,c));
		frame.add(panelGrid);
		frame.setVisible(true);
		for (int i= 0; i< l; i++) {
			//gbc.gridx = i;
			for (int j= 0; j< c; j++) {
				//gbc.gridy = j;
				//String coord = grid.xyToString(l,c);
				//String symb = grid.getCase(coord);
				//frame.add(new Button(symb));
				Button b = new Button("i");
				b.setSize(10,10);
				panelGrid.add(b);
			}
		}
	}
}

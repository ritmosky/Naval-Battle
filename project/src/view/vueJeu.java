package view;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class vueJeu {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				vuePlateau fenetre = new vuePlateau();
				vueGrille g = new vueGrille(15,15);
				//fenetre.add(g);
				fenetre.setVisible(true);
			}
		});
	}

}




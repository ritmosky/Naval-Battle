package view;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollBar;

/** 
 * La classe implémente la vue concernant l'aide du jeu.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class ViewAide {

	private JFrame frame;

	/**
	 * Constructeur
	 */
	public ViewAide() { initialize(); }

	/**
	 * retourne le frame de la vue
	 * @return retourne le frame de la vue
	 */
	public JFrame getFrame() { return this.frame; }
	 
	/** 
	 * La fonction permet d'initialiser la vue
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("AIDE DE JEU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JButton acceuilBttn = new JButton("Retour sur la page d'Acceuil");
		frame.getContentPane().add(acceuilBttn, BorderLayout.SOUTH);
		
		JTextPane tx = new JTextPane();
		tx.setText("\n La bataille navale oppose deux joueurs.\n"
				+ "\n Chaque joueur dispose de deux grilles carrees 15x15, ainsi que d'une flotte composee de 10 bateaux de taille et de puissance differentes.\n Chaque joueur possede 1 cuirasse, 2 croiseurs, 3 destroyers et 4 sous-marins.\n"
				+ "\n La premiere grille represente la zone du joueur et la deuxieme grille celle de l ordinateur.\n"
				+ "\n Deux types d actions peuvent etre réalisees par tour : deplacer ou tirer avec un navire prealablement choisi.\n"
				+ "\n On ne peux deplacer un navire que si celui-ci n a pas ete touche par un tir ennemi. En cas de deplacement impossible il faudra tirer avec le navire selectionne.\n"
				+ "\n Pour couler un navire, il faut avoir touche toutes ses cases. \n Le premier tir d un destroyer est une fusee eclairante qui devoile pendant 5 secondes, dans la grille 2 un carre de cote 4x4 correspondant a la grille exacte ennemie.\n"
				+ "\n Les parties non termines peuvent etre sauvegardees pour pouvoir les continuer plus tard.\n"
				+ "\n Il faudra donc selectionner 'Charger partie' au lieu de 'Nouvelle partie'.\n"
				+ "\n Chaque Navire a une Puissance (allant de 1 à 9). La puissance represente le nombre de case touchee par le tir du navire en question.\n"
				+ "\n Les cases pouvant etre impactees par le tir sont la case ciblee par le tir ainsi que les 8 cases l encadrant (en commençant par la case en bas a gauche de la case cible, le reste suivant le sens des aiguilles d une montre).\n"
				+ "\n Le Destroyer etant muni d une fusee eclairante, le carre est forme avec comme 1er sommet (sommet en haut à gauche) la case cible.\n");
		frame.getContentPane().add(tx, BorderLayout.CENTER);
		
		acceuilBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewAcceuil acc = new ViewAcceuil();
				acc.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}
}

package controller;
import java.util.Scanner;
import view.ViewAcceuil;



/** 
 * La classe est utilisé pour le lancement du jeu.
 * L'utilisateur pourra choisir de lancer l'application soit en mode console, soit en moe grapghique.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class MainMenu {

	/** 
	 * Fonction d'affichage des options.
	 * @param none
	 * @return void
	 */
	public void affichage() {

		Scanner in1 = new Scanner (System.in);	
		String choice = "";

		while (choice != "q") {
			System.out.println("\n\n   ------------------- MODE CONSOLE -------------------\n");
			System.out.println("\t ---------------------------------");
			System.out.println("\t|   1. NOUVELLE PARTIE, TAPEZ 1   |");
			System.out.println("\t|   2. CHARGER PARTIE,  TAPEZ 2   |");
			System.out.println("\t|   3. AFFICHER AIDE,   TAPEZ 3   |");
			System.out.print("\t ---------------------------------\n\n CHOIX => ");
			
			choice = in1.nextLine();
			Controller controller = new Controller();
			switch(choice) { 
			case "1": 
				System.out.println("\n --------------------- NOUVELLE PARTIE ---------------------\n");
				System.out.println("\n POUR QUITTER A TOUT MOMENT, TAPEZ qq");
				controller.partie(false);
				continue;		
			case "2": 
				System.out.println("\n --------------------- CHARGER PARTIE ---------------------\n");
				System.out.println("\n POUR QUITTER A TOUT MOMENT, TAPEZ qq");
				controller.chargerPartie(true, null);
				continue;
			case "3": 
				System.out.println("\n --------------------- AFFICHER AIDE ---------------------");
				System.out.println("\n La bataille navale oppose deux joueurs.\n"
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
				continue;	
			}
		}
		in1.close();
	}

	
	/** 
	 * Programme principal de l'application
	 * @param none
	 * @return void
	 */
	public static void main(String[] args) {
		Scanner inc = new Scanner (System.in);	
		String ch = "";
		while (!ch.equals("1") && !ch.equals("2")) {
			System.out.println("\n\n   ------------------- MODE DE JEU -------------------\n");
			System.out.println("\t ---------------------------------");
			System.out.println("\t|   1. MODE CONSOLE,    TAPEZ 1   |");
			System.out.println("\t|   2. MODE GRAPHIQUE,  TAPEZ 2   |");
			System.out.print("\t ---------------------------------\n\n CHOIX => ");
			ch = inc.nextLine();
		}
		
		if (ch.equals("1")) {
			MainMenu menu = new MainMenu();
			menu.affichage();	
		}

		if (ch.equals("2")) {
			ViewAcceuil acc = new ViewAcceuil();
			acc.getFrame().setVisible(true);
		}

	}
}

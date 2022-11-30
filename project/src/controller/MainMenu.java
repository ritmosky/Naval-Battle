package controller;
import java.util.Scanner;



public class MainMenu {
	
	public void affichage() {
		Scanner in1 = new Scanner (System.in);	
		String choice = "";

		while (choice != "q") {
			System.out.println("\n\n   ------------------- MENU -------------------\n");
			System.out.println("\t ---------------------------------");
			System.out.println("\t|   1. NOUVELLE PARTIE, TAPEZ 1   |");
			System.out.println("\t|   2. CHARGER PARTIE,  TAPEZ 2   |");
			System.out.println("\t|   3. AFFICHER AIDE,   TAPEZ 3   |");
			System.out.print("\t ---------------------------------\n\n CHOIX => ");
			
			choice = in1.nextLine();
			Controller controller = new Controller();
			switch(choice) { 
			case "1": 
				System.out.println("\n --------------------- NOUVELLE PARTIE ---------------------");
				controller.partie(false);
				continue;		
			case "2": 
				System.out.println("\n --------------------- CHARGER PARTIE ---------------------");
				controller.chargerPartie();
				continue;
			case "3": 
				System.out.println("\n --------------------- AFFICHER AIDE ---------------------");
				continue;	
			case "q": 
				System.out.println("\n --------------------- FIN --------------------- \n");
				System.exit(0);;	
			}
		}
		in1.close();
	}
	
	
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.affichage();
		
	}
}

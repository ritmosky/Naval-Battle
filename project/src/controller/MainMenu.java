package controller;
import java.util.Scanner;


public class MainMenu {

	
	public void affichage() {
		Scanner in1 = new Scanner (System.in);	
		String choice = "";

		while (choice != "q") {
			System.out.println("\n\n --------------------- MENU ---------------------\n");
			System.out.println("  1. Pour Jouer une partie: TAPEZ 1");
			System.out.println("  2. Pour Charger une partie: TAPEZ 2");
			System.out.println("  3. Pour afficher l Aide: TAPEZ 3");
			System.out.println("  3. Pour Quitter et Sauvegarder: TAPEZ q \n");
			System.out.print(" ------------------------------------------------\n\n choice => ");
			
			choice = in1.nextLine();
			Controller controller = new Controller();
			switch(choice) {
			
			case "1": 
				System.out.println("\n --------------------- Debuter une partie ---------------------");
				controller.partie(false);
				continue;
				
			case "2": 
				System.out.println("\n --------------------- Charger une partie ---------------------");
				controller.chargerPartie();
				continue;
				
			case "3": 
				System.out.println("\n --------------------- Afficher une aide ---------------------");
				continue;
				
			case "q": 
				System.out.println("\n --------------------- !!! FIN, MERCI !!! --------------------- \n");
				break;	
			}
			
		}
		
		in1.close();
	}
	
	
	
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.affichage();
		System.out.println("\n --------------------- !!! MERCI !!! --------------------- \n");

	}

}

package navalBattle;
import java.util.Scanner;


public class Menu {

	
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

			switch(choice) {
			
			case "1": 
				System.out.println("\n --------------------- Debuter une partie ---------------------");
				Controller controller = new Controller();
				controller.partie();
				continue;
				
			case "2": 
				System.out.println("\n --------------------- Charger une partie ---------------------");
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
		Menu menu = new Menu();
		menu.affichage();
		System.out.println("\n --------------------- !!! MERCI !!! --------------------- \n");

	}

}

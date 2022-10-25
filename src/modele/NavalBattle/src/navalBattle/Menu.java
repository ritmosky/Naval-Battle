package navalBattle;
import java.util.Scanner;


public class Menu {

	public void printMenu() {
		Scanner in1 = new Scanner (System.in);
		
		
		String choice = in1.nextLine();
		do {
			System.out.println("\n\n ---------- MENU ----------\n");
			System.out.println("\n  - Pour affronter l ordi dans une nouvelle partie, tapez 1");
			System.out.println("\n  - Pour charger une ancienne partie avec un fichier, tapez 2");
			System.out.println("\n  - Pour sauvegarder la partie en cours, tapez 3");
			System.out.println("\n  - Pour quitter le jeu, tapez q\n");
			
		} while (choice != "q");
	}
	
	
	
	public static void main(String[] args) {
		

	}

}

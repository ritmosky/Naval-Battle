package navalBattle;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;


public class Controller {


	// ------------------------ VARIABLES GLOBALES ------------------------ //


	public static int NLINE = 15; 
	public static int NCOL = 15; 
	public static String ALPH = "abcdefghijklmnopqrstuvwxyz";


	public static HashMap<String, String> CUIRASSE = new HashMap<String, String>() {{
		put("puissance", "9");
		put("taille", "7");
		put("symbole", "**");
		put("nombre", "1");
	}};

	public static HashMap<String, String> CROISEUR = new HashMap<String, String>() {{
		put("puissance", "4");
		put("taille", "5");
		put("symbole", "oo");
		put("nombre", "2");
	}};

	public static HashMap<String, String> DESTROYER = new HashMap<String, String>() {{
		put("puissance", "1");
		put("taille", "3");
		put("symbole", "++");
		put("nombre", "3");
	}};

	public static HashMap<String, String> SOUSMARIN = new HashMap<String, String>() {{
		put("puissance", "1");
		put("taille", "1");
		put("symbole", "ss");
		put("nombre", "4");
	}}; 


	Joueur j1; 
	Joueur j2;


	// ------------------------ CONSTRUCTOR ------------------------ //


	Controller() {
		j2 = new Joueur("ordi");
	};


	// ------------------------ SETTERS & GETTERS ------------------------ //


	public void setJ1(String n) { this.j1 = new Joueur(n); }

	public Joueur getJoueurAt(int p) {
		if (p == 0) {
			return this.j2;
		}
		return this.j1;
	}


	// ------------------------ PLACEMENT DES NAVIRES ------------------------ //


	public void DistribuerNavire(Joueur j) {
		int nb = Integer.parseInt(this.CROISEUR.get("nombre"));
		for (int n=0; n< nb; n++) {
			Croiseur N = new Croiseur();
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.CUIRASSE.get("nombre"));
		for (int n=0; n< nb; n++) {
			Cuirasse N = new Cuirasse();
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.DESTROYER.get("nombre"));
		for (int n=0; n< nb; n++) {
			Destroyer N = new Destroyer();
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.SOUSMARIN.get("nombre"));
		for (int n=0; n< nb; n++) {
			SousMarin N = new SousMarin();
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}
	}


	// ------------------------ AFFICHAGE DES 2 GRILLES ------------------------ //


	public void afficher2Grilles(Joueur currentPlayer, Joueur ennemi) {
		System.out.print("\n");
		System.out.print("\n----- Tour de " + currentPlayer.name + " -----\n\n\n");
		System.out.print("\t\t" + "  GRILLE 1" + "\t\t\t\t\t\t\t\t" + "  GRILLE 2\n\n\n");

		int nCol = currentPlayer.getGrid1().nCol;
		String alphCol = currentPlayer.getGrid1().alphCol;

		Grille1 grid1CurrentJ = currentPlayer.getGrid1();
		Grille2 grid2CurrentJ = currentPlayer.getGrid2();

		grid2CurrentJ.setCases(ennemi.getGrid1().getCases());

		// CONSTRUCTION DU CADRE
		for (int n=0; n<2; n++) {	
			System.out.print(" |");
			for (int i=0; i< nCol; i++) {
				if (i<10) {
					System.out.print(" ");
				}
				System.out.print(""+i+"|");
			}
			System.out.print("\t\t\t\t");
		}
		System.out.print("\n");

		for (int n=0; n<2; n++) {	
			System.out.print(" ");
			for (int i=0; i< nCol; i++) {
				System.out.print(" --");
			} 
			System.out.print("\t\t\t\t");
		}
		System.out.print("\n");

		for (int i=0; i< alphCol.length(); i++) {

			// REMPLISSAGE DE LA GRILLE 1 DU JOUEUR COURANT
			System.out.print(alphCol.charAt(i)+"|"); 
			for (int j=0; j< nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				/*if (grid1CurrentJ.estCaseNavireTouchee(coord)) {
					System.out.print("xx");
				}
				else if (grid1CurrentJ.estCaseVideTouchee(coord)) {
					System.out.print("tt");
				}
				else {
					System.out.print(grid1CurrentJ.getCase(coord));	
				}*/
				System.out.print(grid1CurrentJ.getCase(coord));	
				System.out.print("|");		
			}

			// REMPLISSAGE DE LA GRILLE 2 DU JOUEUR COURANT
			System.out.print("\t\t\t\t"); 
			System.out.print(alphCol.charAt(i)+"|");	
			for (int j=0; j< nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				/*if (grid2CurrentJ.getCasesNavireTouchees().contains(coord)) {
					System.out.print("xx");
				}
				else if (grid2CurrentJ.getCasesTouchees().contains(coord)) {
					System.out.print("tt");
				}
				else {
					//System.out.print("  ");	
					System.out.print(grid2CurrentJ.getCase(coord));	
				}*/
				System.out.print(grid2CurrentJ.getCase(coord));	
				System.out.print("|");		
			}
			System.out.print("\n");

			for (int n=0; n<2; n++) {	
				System.out.print(" ");
				for (int a=0; a< nCol; a++) {
					System.out.print(" --");
				} 
				System.out.print("\t\t\t\t");
			}	
			System.out.print("\n");
		}
	}


	// ------------------------ DEPLACEMENT ------------------------ //


	// RETOURNE LE NAVIRE CORRESPONDANT AUX COORDONNEES
	public int trouverNavireAvecCoord(Joueur j, String coord) {
		for (int i= 0; i< j.getNavires().size(); i++) {
			Navire navire = j.getNavires().get(i);

			//ON RENVOIE L'INDEX DU NAVIRE SI UNE DES COORDONNEES CORRESPOND AU PARAMETRE 
			if (navire.getCasesNavire().contains(coord)) { 
				return i; 
			}
		}
		return -1;
	}


	public boolean deplacerNavire(Joueur j, String navireCoord) {
		System.out.print("\n\n----- DEPLACER ----- \n");
		Scanner inn = new Scanner (System.in); 

		// AFFICHAGE DU NAVIRE A DEPLACER
		int indNavireADeplacer = this.trouverNavireAvecCoord(j, navireCoord);
		Navire navireADeplacer = j.getNavires().get(indNavireADeplacer);
		String symbole = navireADeplacer.symbole;

		Grille grid1 = j.getGrid1();
		ArrayList<String> newCoords = new ArrayList<String>();


		// ON NE PEUT PAS DEPLACER UN NAVIRE TOUCHE
		if (navireADeplacer.getCasesTouchees().size() != 0) { 
			System.out.print("\n\n- !!! Navire touche, Deplacement impossible !!! \n");
			return false; 
		}


		// DEPLACEMENT HORIZONTAL
		if (navireADeplacer.disposition == "horizontale") {
			System.out.print("\nDisposition: Horizontale\n- Sens de deplacement ? O pour la gauche et 1 pour la droite : \n\n=> choix: ");
			int choice = inn.nextInt();

			// DEPLACER VERS LA GAUCHE
			if (choice == 0) { 
				for (String coord: navireADeplacer.getCasesNavire()) { 
					int x = grid1.stringToXY(coord)[0]; 
					int y = grid1.stringToXY(coord)[1]; 
					String xyString = grid1.xyToString(x-1, y);
					if (grid1.estCaseVide(xyString) == false) { 
						System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
						return false; 
					}
					// MODIFICATION DE LA GRILLE 1
					grid1.setCase("  ", coord);
					grid1.setCase(symbole, xyString);
					// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
					newCoords.add(xyString);
				}
			}

			// DEPLACER VERS LA DROITE
			else if (choice == 1) { 
				for (String coord: navireADeplacer.getCasesNavire()) { 
					int x = grid1.stringToXY(coord)[0]; 
					int y = grid1.stringToXY(coord)[1]; 
					String xyString = grid1.xyToString(x+1, y);
					if (grid1.estCaseVide(xyString) == false) { 
						System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
						return false; 
					}
					// MODIFICATION DE LA GRILLE 1
					grid1.setCase("  ", coord);
					grid1.setCase(symbole, xyString);
					// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
					newCoords.add(xyString);
				}
			}
		}


		// DEPLACEMENT VERTICAL
		else if (navireADeplacer.disposition == "verticale") {
			System.out.print("\nDisposition: Verticale \n- Sens de deplacement: O pour le haut et 1 pour le bas : \n\n=> choix: ");
			int choice = inn.nextInt();

			// DEPLACER VERS LE HAUT
			if (choice == 0) { 
				for (String coord: navireADeplacer.getCasesNavire()) { 
					int x = grid1.stringToXY(coord)[0]; 
					int y = grid1.stringToXY(coord)[1]; 
					String xyString = grid1.xyToString(x, y-1);
					if (grid1.estCaseVide(xyString) == false) { 
						System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
						return false; 
					}
					// MODIFICATION DE LA GRILLE 1
					grid1.setCase("  ", coord);
					grid1.setCase(symbole, xyString);
					// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
					newCoords.add(xyString);
				}
			}

			// DEPLACER VERS LE BAS
			else if (choice == 1) { 
				for (String coord: navireADeplacer.getCasesNavire()) { 
					int x = grid1.stringToXY(coord)[0]; 
					int y = grid1.stringToXY(coord)[1]; 
					String xyString = grid1.xyToString(x, y+1);
					if (grid1.estCaseVide(xyString) == false) { 
						System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
						return false; 
					}
					// MODIFICATION DE LA GRILLE 1
					grid1.setCase("  ", coord);
					grid1.setCase(symbole, xyString);
					// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
					newCoords.add(xyString);
				}
			}
		}

		// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
		navireADeplacer.setCasesNavire(newCoords);
		j.getNavires().set(indNavireADeplacer, navireADeplacer);
		return true;
	}


	// ------------------------ TIR ------------------------ //

	/*Chaque destroyer n’est muni que d’une seule fusée éclairante. Le premier tir d’un destroyer 
	 * dévoile un carré de 4*4 cases dans la grille adverse à partir du coin haut et gauche. 
	 * Mais attention, les navires adverses de ce carré ne seront visibles que lors du tour du 
	 * jeu (quelques secondes)
	 * , les sous-marins ne peuvent 
	 *  être coulés que par d’autres sous-marins. Dans ce cas, le navire adverse coulé disparaît et 
	 *  la case touchée s’affiche sur la grille n° 2.*/



	// TIRER SUR NAVIRE ENNEMI pensez aux cases libre grid2, les navires coulee disparaissent ---------------------------------
	public boolean tirerSurNavire(Joueur j, Joueur otherJ, String coord) {
		System.out.print("\n\n----- TIR ----- \n");

		// AFFICHAGE DU NAVIRE QUI DOIT TIRER
		int indexNavireChoisi = this.trouverNavireAvecCoord(j, coord);
		Navire navireChoisi = j.getNavires().get(indexNavireChoisi);

		Scanner inn = new Scanner (System.in); 
		Grille grid1Ennemy = otherJ.getGrid1();
		Grille grid2 = j.getGrid2();

		// DEMANDER LES COORDONNEES DE LA CIBLE
		System.out.print("\nDonner les coordonnees de la cible dans la grille 2: ");
		String coordCible = inn.nextLine();
		Navire navireCible = otherJ.getNavires().get(indexNavireChoisi);

		// LES COORDONNEES ONT DEJA ETE RENSEIGNEES
		if (grid2.estCaseVide(coordCible) == false) { 
			System.out.print("\n\n!!! Coordonnees deja renseignees !!!");
			return false;
		}

		// LES COORDONNEES NE SONT PAS CELLES D'UN NAVIRE ENNEMI
		if (grid2.estCaseNavire(coordCible) == false) { 
			System.out.print("\n!!! Tir Loupee !!!");
			grid2.setCase("tt", coordCible);
			return true;
		}

		// LES COORDONNEES SONT CELLES D'UN NAVIRE ENNEMI
		if (grid2.estCaseNavire(coordCible) == true) {
			// CAS DU PREMIER TIR DU DESTROYER
			if (navireChoisi.estDestroyer() == true && navireChoisi.getPremierTirEffectue() == true) {
				navireChoisi.premierTir();
				// recherche coin le plus haut a gauche
				// recherche de 4 cases
				// les faire apparaitres pendant quelques secondes
			}

			// CAS DU SOUS MARIN DETRUISANT UN SOUS MARIN, sous marin adverse disparait? cases libres?
			if (navireChoisi.estSousMarin() == true && navireCible.estSousMarin()) {

			}
			grid1Ennemy.setCase("xx", coordCible);
			//grid2.setCase("xx", coordCible);
		}

		
		if (navireCible.estCoule() == true) {

		}

		return false;
	}
}

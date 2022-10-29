package navalBattle;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Random;
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


	public void setGrid2(Joueur j1, Joueur j2) {
		Grille2 grid2J1 = j1.getGrid2();
		grid2J1.setCases(j2.getGrid1().getCases());

		Grille2 grid2J2 = j2.getGrid2();
		grid2J2.setCases(j2.getGrid1().getCases());
	}


	// ------------------------ AFFICHAGE DES 2 GRILLES ------------------------ //


	public void afficher2Grilles(Joueur currentPlayer, Joueur ennemi) {
		System.out.print("\n");
		System.out.print("\t\t" + "  GRILLE 1" + "\t\t\t\t\t\t\t\t" + "  GRILLE 2\n\n\n");

		int nCol = currentPlayer.getGrid1().nCol;
		String alphCol = currentPlayer.getGrid1().alphCol;

		Grille1 grid1CurrentJ = currentPlayer.getGrid1();
		Grille2 grid2CurrentJ = currentPlayer.getGrid2();

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
				System.out.print(grid1CurrentJ.getCase(coord));	
				System.out.print("|");		
			}

			// REMPLISSAGE DE LA GRILLE 2 DU JOUEUR COURANT
			System.out.print("\t\t\t\t"); 
			System.out.print(alphCol.charAt(i)+"|");	
			for (int j=0; j< nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				System.out.print(grid2CurrentJ.masquerCase(coord));
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
		
		if (j.name != "ordi") { System.out.print("\n\n----- DEPLACER ----- \n"); }
		Scanner inDeplacer = new Scanner (System.in); 

		// NAVIRE A DEPLACER
		int choice = -1;
		int indNavireADeplacer = this.trouverNavireAvecCoord(j, navireCoord);
		Navire navireADeplacer = j.getNavires().get(indNavireADeplacer);

		ArrayList<String> newCoords = new ArrayList<String>();

		// ON NE PEUT PAS DEPLACER UN NAVIRE TOUCHE navireADeplacer.getCasesTouchees().size()
		if (navireADeplacer.getCasesTouchees() != null) { 
			if (j.name != "ordi") { System.out.print("\n\n- !!! Navire touche, Deplacement impossible !!! \n"); }
			return false; 
		}

		// DEPLACEMENT DU SOUS MARIN
		if (navireADeplacer.estSousMarin() == true) { 		
			
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.name == "ordi") {
				Random r = new Random();
				choice = r.nextInt(0, 4);
			}
			
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			if (j.name != "ordi") {
				do {
					System.out.println("\n Choisissez le sens de deplacement");
					System.out.println("- Vers le haut, tapez 0");
					System.out.println("- Vers le bas, tapez 1");
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n\n=> choix: ");
					choice = inDeplacer.nextInt();
				} while (choice != 0 && choice != 1 && choice != 2 && choice != 3);
			}
			
			newCoords = j.deplacer(navireADeplacer, choice);
			// DEPLACEMENT IMPOSSIBLE
			if (newCoords == null) {
				return false;
			}
			// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
			navireADeplacer.setCasesNavire(newCoords);
			j.getNavires().set(indNavireADeplacer, navireADeplacer);
			return true; 
		}
		

		// DEPLACEMENT HORIZONTAL
		if (navireADeplacer.disposition == "horizontale") {
			
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.name == "ordi") {
				Random r = new Random();
				choice = r.nextInt(2, 4);
			}

			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			if (j.name != "ordi") {
				do {
					System.out.println("\n Disposition: Horizontale, Choisissez le sens de deplacement");
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n\n=> choix: ");
					choice = inDeplacer.nextInt();
				} while (choice != 2 && choice != 3);
			}
		}

		
		// DEPLACEMENT VERTICAL
		else if (navireADeplacer.disposition == "verticale") {
			
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.name == "ordi") {
				Random r = new Random();
				choice = r.nextInt(0, 2);
			}

			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			if (j.name != "ordi") {
				do {
					System.out.println("\n Disposition: Verticale, Choisissez le sens de deplacement");
					System.out.println("- Vers le haut, tapez 0");
					System.out.print("- Vers le bas, tapez 1 \n\n=> choix: ");
					choice = inDeplacer.nextInt();
				} while (choice != 0 && choice != 1);
			}
		}

		newCoords = j.deplacer(navireADeplacer, choice);
		// SI DEPLACEMENT IMPOSSIBLE
		if (newCoords == null) {
			return false;
		}
		
		// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
		navireADeplacer.setCasesNavire(newCoords);
		j.getNavires().set(indNavireADeplacer, navireADeplacer);
		return true;
	}
	
	
	


	// ------------------------ TIR ------------------------ //


	// TIRER SUR NAVIRE ENNEMI pensez aux cases libre grid2, les navires coulee disparaissent ---------------------------------
	public boolean tirerSurNavire(Joueur j, Joueur otherJ, String coord) {
		
		if (j.name != "ordi") { System.out.print("\n\n----- TIR ----- \n"); }

		// NAVIRE QUI DOIT TIRER
		int indexNavireChoisi = this.trouverNavireAvecCoord(j, coord);
		Navire navireChoisi = j.getNavires().get(indexNavireChoisi);

		
		Scanner inTir = new Scanner (System.in); 
		Grille grid1Ennemy = otherJ.getGrid1();
		Grille grid2 = j.getGrid2();

		// CHOIX DE LA CIBLE COORDONNEES DE LA CIBLE
		String coordCible = "";
		do {
			// CHOIX DE LA CIBLE PAR L ORDI
			if (j.name == "ordi") {
				Random r;
				coordCible = "";
			}
			
			// CHOIX PAR LE JOUEUR PHYSIQUE
			if (j.name != "ordi") {
				System.out.print("\nDonner les coordonnees d une cible (non touchee) dans la grille 2: ");
				coordCible = inTir.nextLine();
			}
		} while (grid2.estCaseTouchee(coordCible) == true);
		

		Navire navireCible = otherJ.getNavires().get(indexNavireChoisi);

		// LES COORDONNEES NE SONT PAS CELLES D'UN NAVIRE ENNEMI
		if (grid2.estCaseNavire(coordCible) == false) { 
			if (j.name != "ordi") { System.out.print("\n!!! Tir Loupee !!!"); }
			grid1Ennemy.setCase("tt", coordCible);
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
			if (navireChoisi.estSousMarin() == true && navireCible.estSousMarin()) {}
			grid1Ennemy.setCase("xx", coordCible);
		}


		if (navireCible.estCoule() == true) {
			if (j.name != "ordi") { System.out.println("\n !!! WARNING !!! Un de vos Navire a ete coulee \n"); }
			for (String coordN: navireCible.getCasesNavire()) {
				otherJ.getGrid1().setCase("  ", coordN);
			}
		}
		return false;
	}


	// ------------------------ DEROULEMENT DE PARTIE ------------------------ //

// gerer choix aleatoire pour ordi dans deplacer et tir
	public void partie() {

		// DEMANDER LE NOM DES JOUEURS
		Scanner inPartie = new Scanner (System.in); 
		System.out.print("\n\n- Nom du joueur qui affronte ORDI : ");
		String nameJ = inPartie.nextLine();
		this.setJ1(nameJ);

		// INITIALISATIONS DES GRILLES 1 DES JOUEURS
		this.DistribuerNavire(this.j1);
		this.DistribuerNavire(this.j2);

		// INITIALISATIONS DES GRILLES 2 DES JOUEURS
		this.setGrid2(this.j1, this.j2);
		Joueur currentJ;
		Joueur ennemi;
		int tour = 1;
		boolean fini = false;

		
		// DEROULEMENT DES TOURS
		while (fini != true) {
			boolean actionEffectuee = false;
			Scanner inTour = new Scanner (System.in); 
			currentJ = this.getJoueurAt(tour%2);
			ennemi = this.getJoueurAt((tour+1)%2);
			
			// AFFICHAGE DES 2 GRILLES
			System.out.print("\n\n\n--------------------------------------------------- Tour de " + currentJ.name + " ---------------------------------------------------\n\n\n");
			this.afficher2Grilles(currentJ, ennemi);

			
			// GESTION DU TOUR DU JOUEUR
			if (currentJ.name != "ordi") {
				
				// DEMANDER LES COORDONNEES DU NAVIRE DU JOUEUR COURANT
				int indNavire = -1;
				String coord = "";
				do {
					System.out.print("\n\n- Choisissez les coordonnees d un navire de la grille 1 : \n\n=> choix : ");
					coord = inTour.nextLine();
					indNavire = this.trouverNavireAvecCoord(currentJ, coord);
				} while(indNavire < 0);
				
				// DEMANDER L'ACTION A EFFECTUER
				int choice = -1;
				do {
					System.out.println("\n\n- Pour Deplacer le navire, taper 1");
					System.out.print("- Pour Tirer sur un navire ennemi, taper 2");
					System.out.print("\n\n=> choix : ");
					choice = inTour.nextInt();
				} while(choice != 1 && choice != 2);

				// EXECUTION DE L ACTION
				if (choice == 1) {
					actionEffectuee = this.deplacerNavire(currentJ, coord); // si peut pas bouger -> tirer, gerer deplacement impossible ------------------
				}
				else if (choice == 2) {
					this.tirerSurNavire(currentJ, ennemi, coord); // this.setGrid2(currentJ, ennemi); ---------
				}
			}
			

			// GESTION DU TOUR DE L ORDINATEUR
			if (currentJ.name == "ordi") {
				// CHOIX ALEATOIRE DE LA CASE D UN NAVIRE
				Random randP = new Random();
				int xR; 
				int yR;
				String coordR;
				do { 
					xR = randP.nextInt(currentJ.getGrid1().nCol);
					yR = randP.nextInt(currentJ.getGrid1().nLine);
					coordR = currentJ.getGrid1().xyToString(xR, yR);
				} while (currentJ.getGrid1().estCaseNavire(coordR) == false);

				// ACTION ALEATOIRE
				int action = 1; //randP.nextInt(1);
				if (action == 1) { System.out.print("\n\n##### ordi a deplacer navire de coord: " + coordR); actionEffectuee = this.deplacerNavire(currentJ, coordR); }
				if (action == 2) { this.tirerSurNavire(currentJ, ennemi, coordR); }
			}
			
			
			// PASSAGE AU PROCHAIN TOUR
			this.setGrid2(currentJ, ennemi);
			this.afficher2Grilles(currentJ, ennemi);
			fini = ennemi.estVaincu();
			for (int i = 1; i < 6; i++) {
				int sec = 6 - i;
			System.out.print("\n ---------------- TOUR DU PROCHAIN JOUEUR DANS " + sec + " SECONDES ----------------");
			try { TimeUnit.SECONDS.sleep(1); } catch(Exception e) { System.out.println(e); }
			}
			tour++;	
		}
	}

}

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
		System.out.print("\n\n----- DEPLACER ----- \n");
		Scanner inDeplacer = new Scanner (System.in); 

		// AFFICHAGE DU NAVIRE A DEPLACER
		int indNavireADeplacer = this.trouverNavireAvecCoord(j, navireCoord);
		Navire navireADeplacer = j.getNavires().get(indNavireADeplacer);

		ArrayList<String> newCoords = new ArrayList<String>();

		// ON NE PEUT PAS DEPLACER UN NAVIRE TOUCHE navireADeplacer.getCasesTouchees().size()
		if (navireADeplacer.getCasesTouchees() != null) { 
			System.out.print("\n\n- !!! Navire touche, Deplacement impossible !!! \n");
			return false; 
		}

		// DEPLACEMENT DU SOUS MARIN
		if (navireADeplacer.estSousMarin() == true) { 
			System.out.print("\n- Sens de deplacement ? O => pour le haut | 1 => pour le bas | 2 => pour la gauche | 3 => pour la droite : \n\n=> choix: ");
			int choice = inDeplacer.nextInt();
			newCoords = j.deplacer(navireADeplacer, choice);
			if (newCoords == null) {
				return false;
			}
			return true; 
		}

		// DEPLACEMENT HORIZONTAL
		if (navireADeplacer.disposition == "horizontale") {
			System.out.print("\nDisposition: Horizontale\n- Sens de deplacement ? 2 => pour la gauche | 3 => pour la droite : \n\n=> choix: ");
			int choice = inDeplacer.nextInt();
			newCoords = j.deplacer(navireADeplacer, choice);
		}

		// DEPLACEMENT VERTICAL
		else if (navireADeplacer.disposition == "verticale") {
			System.out.print("\nDisposition: Verticale \n- Sens de deplacement ? O => pour le haut | 1 => pour le bas : \n\n=> choix: ");
			int choice = inDeplacer.nextInt();
			newCoords = j.deplacer(navireADeplacer, choice);
		}

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
		System.out.print("\n\n----- TIR ----- \n");

		// AFFICHAGE DU NAVIRE QUI DOIT TIRER
		int indexNavireChoisi = this.trouverNavireAvecCoord(j, coord);
		Navire navireChoisi = j.getNavires().get(indexNavireChoisi);

		Scanner inTir = new Scanner (System.in); 
		Grille grid1Ennemy = otherJ.getGrid1();
		Grille grid2 = j.getGrid2();

		// DEMANDER LES COORDONNEES DE LA CIBLE
		System.out.print("\nDonner les coordonnees de la cible dans la grille 2: ");
		String coordCible = inTir.nextLine();
		inTir.close();
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
				System.out.print("\n\n- Choisissez les coordonnees d un navire de la grille 1 : \n\n=> choix : ");
				String coord = inTour.nextLine();

				// DEMANDER L'ACTION A EFFECTUER
				int choice;
				System.out.print("\n\n- Pour Deplacer le navire, taper 1 : \n- Pour Tirer sur un navire ennemi, taper 2 : \n\n=> choix : ");
				choice = inTour.nextInt();

				if (choice == 1) {
					actionEffectuee = this.deplacerNavire(currentJ, coord); // si peut pas bouger -> tirer
				}
				else if (choice == 2) {
					this.tirerSurNavire(currentJ, ennemi, coord);
				}
			}
			

			// GESTION DU TOUR DE L ORDINATEUR
			if (currentJ.name == "ordi") {
				// CHOIX ALEATOIRE DE LA CASE D UN NAVIRE
				Random r = new Random();
				int xR; 
				int yR;
				String coordR;
				do { 
					xR = r.nextInt(currentJ.getGrid1().nCol);
					yR = r.nextInt(currentJ.getGrid1().nLine);
					coordR = currentJ.getGrid1().xyToString(xR, yR);
				} while (currentJ.getGrid1().estCaseNavire(coordR) == false);

				// ACTION ALEATOIRE
				int action = r.nextInt(1);
				if (action == 0) { actionEffectuee = this.deplacerNavire(currentJ, coordR); }
				if (action == 1) { this.tirerSurNavire(currentJ, ennemi, coordR); }
			}
			
			
			// PASSAGE AU PROCHAIN TOUR
			this.afficher2Grilles(currentJ, ennemi);
			this.afficher2Grilles(currentJ, ennemi);
			fini = ennemi.estVaincu();
			System.out.print("\n\n ---------------- TOUR DU PROCHAIN JOUEUR DANS 6 SECONDES ----------------\n");
			try { TimeUnit.SECONDS.sleep(10); } catch(Exception e) { System.out.println(e); }
			tour++;	
		}
	}

}

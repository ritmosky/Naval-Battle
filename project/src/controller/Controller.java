package controller;
import modele.*;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;



/** 
 * Le Controller est la classe qui va gérer tout le déroulement du jeu.
 * Elle contient des déclarations de dictionnaires contenant toutes les informations relatives aux différents navires.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Controller {
	/**
	 * nombre de ligne du jeu
	 */
	public int nLine = 15; 
	
	/**
	 * nombre de colonne du jeu
	 */
	public int nCol = 15; 
	
	/**
	 * nombre de case occupé par les navires
	 */
	public int nbCaseNavire;
	
	String alph = "abcdefghijklmnopqrstuvwxyz";
	Joueur j1; 
	Joueur j2;
	Joueur currentj;
	
	/**
	 * Dictionnaire contenant toutes les informations relatives au Cuitrasse.
	 */
	final HashMap<String, String> CUIRASSE = new HashMap<String, String>() {private static final long serialVersionUID = 1L;
	{
		put("puissance", "9");
		put("taille", "7");
		put("symbole", "**");
		put("nombre", "1");
	}};

	/**
	 * Dictionnaire contenant toutes les informations relatives au Croiseur.
	 */
	final HashMap<String, String> CROISEUR = new HashMap<String, String>() {private static final long serialVersionUID = 1L;
	{
		put("puissance", "4");
		put("taille", "5");
		put("symbole", "oo");
		put("nombre", "2");
	}};

	/**
	 * Dictionnaire contenant toutes les informations relatives au Destroyer.
	 */
	final HashMap<String, String> DESTROYER = new HashMap<String, String>() {private static final long serialVersionUID = 1L;
	{
		put("puissance", "1");
		put("taille", "3");
		put("symbole", "++");
		put("nombre", "3");
	}};

	/**
	 * Dictionnaire contenant toutes les informations relatives au Sous Marin.
	 */
	final HashMap<String, String> SOUSMARIN = new HashMap<String, String>() {private static final long serialVersionUID = 1L;
	{
		put("puissance", "1");
		put("taille", "1");
		put("symbole", "ss");
		put("nombre", "4");
		put("couleur", "4");
	}}; 

	/**
	 * Dictionnaire contenant toutes les informations relatives au couleurs d'affichage.
	 */
	final HashMap<String, String> COULEUR = new HashMap<String, String>() {private static final long serialVersionUID = 1L;
	{
		put("xx", "\033[41m"); // rouge 
		put("tt", "\033[47m"); // bleu  
		put("oo", "\033[0;32m"); // vert
		put("ss", "\033[0;36m"); // cyan
		put("++", "\033[0;35m"); // violet
		put("**", "\033[0;33m"); // jaune
		put("  ", "\033[0;37m"); // blanc
	}};
	
	/**
	 * Constructeur
	 */
	public Controller() {
		this.j1 = new Joueur("JOUEUR", this.alph.substring(0, this.nLine), this.nLine, this.nCol);
		this.j2 = new Joueur("ordi", this.alph.substring(0, this.nLine), this.nLine, this.nCol);
		this.currentj = this.j1;
		this.nbCaseNavire = Integer.parseInt(SOUSMARIN.get("nombre"))*Integer.parseInt(SOUSMARIN.get("taille"));
		this.nbCaseNavire += Integer.parseInt(CROISEUR.get("nombre"))*Integer.parseInt(CROISEUR.get("taille"));
		this.nbCaseNavire += Integer.parseInt(DESTROYER.get("nombre"))*Integer.parseInt(DESTROYER.get("taille"));
		this.nbCaseNavire += Integer.parseInt(CUIRASSE.get("nombre"))*Integer.parseInt(CUIRASSE.get("taille"));
	};

	/**
	 * retourne le joueur courant
	 * @return retourne le joueur courant
	 */
	public Joueur getCurrentJ() { return this.currentj; }
	
	/**
	 * permet de changer le joueur qui à la main
	 * @param jj le joueur courant
	 */
	public void setCurrentJ(Joueur jj) { this.currentj = jj; }

	/**
	 * retourne le joueur adverse.
	 * @return retourne le joueur ennemi (adverse)
	 */
	public Joueur getEnnemy() { 
		if (this.currentj == this.j1) {
			return this.j2; 
		}
		if (this.currentj == this.j2) {
			return this.j1; 
		}
		return null;
	}
	
	/**
	 * retourne le joueur 1
	 * @return retourne le joueur 1
	 */
	public Joueur getJ1() { return this.j1; }
	
	/**
	 * retourne le joueur 2
	 * @return retourne le joueur 2
	 */
	public Joueur getJ2() { return this.j2; }
	
	/**
	 * retourne le joueur qui à la main au tour p.
	 * @param p le p-ième tour de jeu
	 * @return retourne le joueur ayant la main au tour
	 */
	public Joueur getJoueurAt(int p) {
		if (p == 0) { return this.j2; }
		return this.j1;
	}


	/**
	 * permet de distribuer les navires au joueur j
	 * @param j le joueur
	 */
	public void DistribuerNavire(Joueur j) {
		// CAS D UNE NOUVELLE PARTIE 
		int nb = Integer.parseInt(this.CROISEUR.get("nombre"));
		int taille = Integer.parseInt(this.CROISEUR.get("taille"));
		int puissance = Integer.parseInt(this.CROISEUR.get("puissance"));
		String symbole = this.CROISEUR.get("symbole");
		for (int n=0; n< nb; n++) {
			Croiseur N = new Croiseur(taille, puissance, symbole);
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.CUIRASSE.get("nombre"));
		taille = Integer.parseInt(this.CUIRASSE.get("taille"));
		puissance = Integer.parseInt(this.CUIRASSE.get("puissance"));
		symbole = this.CUIRASSE.get("symbole");
		for (int n=0; n< nb; n++) {
			Cuirasse N = new Cuirasse(taille, puissance, symbole);
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.DESTROYER.get("nombre"));
		taille = Integer.parseInt(this.DESTROYER.get("taille"));
		puissance = Integer.parseInt(this.DESTROYER.get("puissance"));
		symbole = this.DESTROYER.get("symbole");
		for (int n=0; n< nb; n++) {
			Destroyer N = new Destroyer(taille, puissance, symbole);
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}

		nb = Integer.parseInt(this.SOUSMARIN.get("nombre"));
		taille = Integer.parseInt(this.SOUSMARIN.get("taille"));
		puissance = Integer.parseInt(this.SOUSMARIN.get("puissance"));
		symbole = this.SOUSMARIN.get("symbole");
		for (int n=0; n< nb; n++) {
			SousMarin N = new SousMarin(taille, puissance, symbole);
			j.addNavire(N);
			j.getGrid1().placerNavire(N);
		}
	}
	

	/**
	 * permet de mettre à jour les Grille2 des joueurs.
	 * @param J1 le joueur actuel
	 * @param J2 le joueur adverse
	 */
	public void setGrid2(Joueur J1, Joueur J2) {
		Grille2 grid2J1 = J1.getGrid2();
		grid2J1.setCases(J2.getGrid1().getCases());
		Grille2 grid2J2 = J2.getGrid2();
		grid2J2.setCases(J1.getGrid1().getCases());
	}


	/**
	 * permet d'afficher les grilles à chaque tour
	 * @param currentPlayer le joueur actuelle
	 * @param ennemi le joueur ennemi
	 * @param casesEclairees mes cases dévoilées par le premier tir de fusée
	 */
	public void afficher2Grilles(Joueur currentPlayer, Joueur ennemi, ArrayList<String> casesEclairees) {
		System.out.print("\n");
		System.out.print("\t\t" + "  GRILLE 1" + "\t\t\t\t\t\t\t\t" + "  GRILLE 2\n\n\n");
		Grille1 grid1CurrentJ = currentPlayer.getGrid1();
		Grille2 grid2CurrentJ = currentPlayer.getGrid2();
		String alphCol = currentPlayer.getGrid1().getAlphaCol();

		// CONSTRUCTION DU CADRE
		for (int n= 0; n< 2; n++) {	
			System.out.print(" |");
			for (int i=0; i< this.nCol; i++) {
				if (i< 10) { System.out.print(" "); }
				System.out.print(""+i+"|");
			}
			System.out.print("\t\t\t\t");
		}
		System.out.print("\n");
		for (int n= 0; n< 2; n++) {	
			System.out.print(" ");
			for (int i= 0; i< this.nCol; i++) {
				System.out.print(" --");
			} 
			System.out.print("\t\t\t\t");
		}
		System.out.print("\n");
		for (int i= 0; i< alphCol.length(); i++) {
			// REMPLISSAGE DE LA GRILLE 1 DU JOUEUR COURANT 
			System.out.print(alphCol.charAt(i)+"|"); 
			for (int j=0; j< this.nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String coul = COULEUR.get(grid1CurrentJ.getCase(coord));
				System.out.print(coul + grid1CurrentJ.getCase(coord) + "\033[0m");	
				System.out.print("|");		
			}
			// REMPLISSAGE DE LA GRILLE 2 DU JOUEUR COURANT
			System.out.print("\t\t\t\t"); 
			System.out.print(alphCol.charAt(i)+"|");	
			for (int j=0; j< this.nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String coul = COULEUR.get(grid2CurrentJ.getCase(coord));
				if (casesEclairees != null && casesEclairees.contains(coord)) { 
					if (coord.equals(casesEclairees.get(0))) {System.out.print("\u001B[45m" + "tt" + "\033[0m");}
					if (grid2CurrentJ.getCase(coord) == "  ") {System.out.print("\u001B[45m" + "  " + "\033[0m");}
					if (grid2CurrentJ.getCase(coord) != "  " && !coord.equals(casesEclairees.get(0))) {System.out.print("\u001B[45m" + "ff" + "\033[0m");}
				}
				else { System.out.print(coul + "  " /*grid2CurrentJ.getCase(coord)*/ + "\033[0m");	}
				System.out.print("|");		
			}
			System.out.print("\n");
			for (int n=0; n<2; n++) {	
				System.out.print(" ");
				for (int a=0; a< this.nCol; a++) {
					System.out.print(" --");
				} 
				System.out.print("\t\t\t\t");
			}	
			System.out.print("\n");
		}
	}


	/**
	 * renvoie l'index du navire correspondant aux coordonnées en paramètre.
	 * Si les coordonnées ne correspond à aucun navire du joueur alors elle retourne -1.
	 * @param j le joueur actuelle
	 * @param coord coordonnée du navire à chercher
	 * @return index du navire dans la liste de navires du joueur
	 */
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

	/**
	 * permet de déplacer un navire du joueur
	 * @param j le joueur actuelle
	 * @param navireCoord coordonnées d'une case du navire à déplacer
	 * @return retourne un entier -1 si le déplacement est impossible ou 1 si il est possible
	 */
	public int deplacerNavire(Joueur j, String navireCoord) {
		Scanner inDeplacer = new Scanner (System.in); 
		boolean fait = false;
		// NAVIRE A DEPLACER
		String choice = "";
		int indNavireADeplacer = this.trouverNavireAvecCoord(j, navireCoord);
		if (indNavireADeplacer == -1) { return -1; }
		Navire navireADeplacer = j.getNavires().get(indNavireADeplacer);
		ArrayList<String> newCoords = new ArrayList<String>();

		// ON NE PEUT PAS DEPLACER UN NAVIRE TOUCHE 
		if (navireADeplacer.getCasesTouchees().size() != 0) { 
			if (j.getName() != "ordi") { System.out.print("\n!!! NAVIRE TOUCHE; DEPLACEMENT IMPOSSIBLE; EFFECTUER UN TIR !!!"); }
			return -1; 
		}

		// DEPLACEMENT DU SOUS MARIN
		if (navireADeplacer.getName() == "SOUSMARIN") { 		
			// DEPLACEMENT PAR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = Integer.toString(r.nextInt(0, 4));
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers le haut, tapez 0");
					System.out.println("- Vers le bas, tapez 1");
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n=> CHOIX : ");
					choice = inDeplacer.nextLine();
				} while (!choice.equals("0") && !choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
			}
			newCoords = j.deplacer(navireADeplacer, choice);
			// DEPLACEMENT IMPOSSIBLE
			if (newCoords == null) { 
				if (j.getName()!="ordi") {System.out.println("!!! DEPLACEMENT IMPOSSIBLE; EFFECTUER UN TIR !!!");}
				return -1; 
			}
			// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
			navireADeplacer.setCasesNavire(newCoords);
			j.getNavires().set(indNavireADeplacer, navireADeplacer);
			return 1; 
		}

		// DEPLACEMENT HORIZONTAL
		if (navireADeplacer.getDisposition() == "horizontale") {
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = Integer.toString(r.nextInt(2, 4));
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n\n=> choix: ");
					choice = inDeplacer.nextLine();
				} while (!choice.equals("2") && !choice.equals("3"));
			}
			newCoords = j.deplacer(navireADeplacer, choice);
			// DEPLACEMENT IMPOSSIBLE
			if (newCoords == null) { 
				if (j.getName()!="ordi") {System.out.println("!!! DEPLACEMENT IMPOSSIBLE; EFFECTUER UN TIR !!!");}
				return -1; 
			}
			// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
			navireADeplacer.setCasesNavire(newCoords);
			j.getNavires().set(indNavireADeplacer, navireADeplacer);
			return 1;
		}

		// DEPLACEMENT VERTICAL
		else if (navireADeplacer.getDisposition() == "verticale") {
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = Integer.toString(r.nextInt(0, 2));
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers le haut, tapez 0");
					System.out.print("- Vers le bas, tapez 1 \n\n=> choix: ");
					choice = inDeplacer.nextLine();
				} while (!choice.equals("0") && !choice.equals("1"));
			}
			newCoords = j.deplacer(navireADeplacer, choice);
			// DEPLACEMENT IMPOSSIBLE
			if (newCoords == null) { 
				if (j.getName()!="ordi") {System.out.println("!!! DEPLACEMENT IMPOSSIBLE; EFFECTUER UN TIR !!!");}
				return -1; 
			}
			// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
			navireADeplacer.setCasesNavire(newCoords);
			j.getNavires().set(indNavireADeplacer, navireADeplacer);
			return 1;
		}
		return -1;
	}


	/**
	 * permet de tirer sur un navire
	 * @param j le joueur actuelle
	 * @param ennemy le joueur adverse
	 * @param coord coordonnées d'une case du navire qui tire
	 * @return retourne un booleen suivant que le tir a été efectué ou pas
	 */
	public boolean tirerSurNavire(Joueur j, Joueur ennemy, String coord) {
		// NAVIRE QUI DOIT TIRER
		int indexNavireChoisi = this.trouverNavireAvecCoord(j, coord);
		int pssc = j.getNavires().get(indexNavireChoisi).getPuissance();
		Scanner inTir = new Scanner (System.in); 
		Grille grid1Ennemy = ennemy.getGrid1();
		Grille grid2 = j.getGrid2();

		// CHOIX DE LA CIBLE
		String coordCible = "";
		do {
			// CHOIX DE LA CIBLE PAR L ORDI
			if (j.getName() == "ordi") {
				Random r = new Random();;
				int xR = r.nextInt(this.nCol);
				int yR = r.nextInt(this.nLine);
				coordCible = j.getGrid1().xyToString(xR, yR);
			}
			// CHOIX PAR LE JOUEUR PHYSIQUE
			else {
				do {
					System.out.print("\nCASE CIBLE DANS GRILLE 2 : ");
					coordCible = inTir.nextLine();
				} while (grid2.estCaseValide(coordCible) == false);
			}
		} while (grid2.estCaseValide(coordCible) == false);
		
		// ACTION DU TIR
		boolean b = false;
		int[] tabxy = grid2.stringToXY(coordCible); 
		if (grid2.estCaseNavire(coordCible) == false) { grid1Ennemy.setCase("tt", coordCible); --pssc;}
		if (grid2.estCaseNavire(coordCible) == true) { 
			int indN = this.trouverNavireAvecCoord(ennemy, coordCible);
			ennemy.addCasesNaviresTouches(coordCible, indN);
			grid1Ennemy.setCase("xx", coordCible); 
			b = true; 
			--pssc;
		}
		for (int x= tabxy[0]-1; x<= tabxy[0]+1; x++) {
			for (int y= tabxy[1]-1; y<= tabxy[1]+1; y++) {
				if (pssc != 0) {
					if (x<0 || y<0 || y >= this.nLine || x >= this.nCol) { continue; }
					String c = grid2.xyToString(x, y);
					if (c.equals(coordCible) == false) {
						int indN = this.trouverNavireAvecCoord(ennemy, c);
						if (indN > -1) { ennemy.addCasesNaviresTouches(c, indN); }
						if (grid2.estCaseNavire(c) == false) { grid1Ennemy.setCase("tt", c);}
						if (grid2.estCaseNavire(c) == true) { grid1Ennemy.setCase("xx", c); b = true;}
						pssc--;
					}
				}
			}
		}

		// PREMIER TIR DU DESTROYER
		ArrayList<String> casesEclairees = new ArrayList<>();
		if (j.getNavires().get(indexNavireChoisi).getName() == "DESTROYER" && j.getNavires().get(indexNavireChoisi).getTirFusee() == false && !j.getName().equals("ordi")) {
			for (int x= tabxy[0]; x< tabxy[0]+4; x++) {
				for (int y= tabxy[1]; y< tabxy[1]+4; y++) {
					if (x<0 || y<0 || y>=this.nLine || x>=this.nCol) { continue; }
					String c = grid2.xyToString(x, y);
					casesEclairees.add(c);
				}
			}
			j.getNavires().get(indexNavireChoisi).tirFusee();
			if (j.getName() != "ordi") {
				System.out.println("\n !!! PREMIER TIR DU DESTROYER, AFFICHAGE DE LA GRILLE PENDANT 5 SECONDES !!!");}
			this.afficher2Grilles(j, ennemy, casesEclairees);
			for (int i = 1; i < 5; i++) {try { TimeUnit.SECONDS.sleep(1);} catch(Exception e) {System.out.println(e); }}
			if (j.getName() != "ordi") {System.out.println("\n !!! FIN DU TIR DE LA FUSEE ECLAIRANTE !!!");}
			b = true;
		}
		return b;
	}

	/**
	 * permet d'actualiser les cases touchées des navires contenus dans la Grille1 du joueur ennemy
	 * @param j le joueur actuelle
	 * @param ennemy le joueur adverse
	 */
	public void actuNavireTouchee(Joueur j, Joueur ennemy) {
		for (String Unecase: ennemy.getCasesNaviresTouches()) {
			int indN = this.trouverNavireAvecCoord(ennemy, Unecase);
			if (indN==-1) { continue; }
			else { ennemy.addCasesNaviresTouches(Unecase, indN); }
		}
	}
	
	/**
	 * permet de tirer sur un navire dans la partie graphique
	 * @param j le joueur actuelle
	 * @param ennemy le joueur adverse
	 * @param indN index du navire qui tire
	 * @param coordCible coordonnées sur laquelle tirer
	 * @return retourne un booleen suivant que le tir a été efectué ou pas
	 */
	public boolean tirerGraphique(Joueur j, Joueur ennemy, int indN, String coordCible) {
		boolean b = false;
		int pssc = j.getNavires().get(indN).getPuissance();
		Grille grid1Ennemy = ennemy.getGrid1();
		Grille grid2 = j.getGrid2();
		int[] tabxy = grid2.stringToXY(coordCible); 

		
		if (grid2.estCaseNavire(coordCible) == false) { grid1Ennemy.setCase("tt", coordCible); --pssc;}
		if (grid2.estCaseNavire(coordCible) == true) { 
			int indCN = this.trouverNavireAvecCoord(ennemy, coordCible);
			ennemy.addCasesNaviresTouches(coordCible, indCN);
			grid1Ennemy.setCase("xx", coordCible); 
			b = true; 
			--pssc;
		}
		for (int x= tabxy[0]-1; x<= tabxy[0]+1; x++) {
			for (int y= tabxy[1]-1; y<= tabxy[1]+1; y++) {
				if (pssc != 0) {
					if (x<0 || y<0 || y >= this.nLine || x >= this.nCol) { continue; }
					String c = grid2.xyToString(x, y);
					if (c.equals(coordCible) == false) {
						int indEnnemy = this.trouverNavireAvecCoord(ennemy, c);
						if (indEnnemy > -1) { ennemy.addCasesNaviresTouches(c, indEnnemy); }
						if (grid2.estCaseNavire(c) == false) { grid1Ennemy.setCase("tt", c);}
						if (grid2.estCaseNavire(c) == true) { grid1Ennemy.setCase("xx", c); b = true;}
						pssc--;
					}
				}
			}
		}		
		this.actuNavireTouchee(j,ennemy);
		return b;
	}
	
	/**
	 * permet le déroulement de la partie en mode console
	 * @param partieChargee booléen indiquant si c'est une nouvelle partie ou une partie chargée
	 */
	public void partie(boolean partieChargee) {
		// INITIALISATIONS DES GRILLES 
		if (partieChargee == false) { 
			this.DistribuerNavire(this.j1); 
			this.DistribuerNavire(this.j2);
		}
		
		this.setGrid2(this.j1, this.j2);
		Joueur currentJ;
		Joueur ennemi;
		int tour = 1;
		boolean fini = false;

		// DEROULEMENT DES TOURS
		while (fini != true) {
			boolean deplacementFait = false;
			currentJ = this.getJoueurAt(tour%2);
			ennemi = this.getJoueurAt((tour+1)%2);
			this.currentj = currentJ;

			// AFFICHAGE DES 2 GRILLES
			System.out.print("\n\n--------------------- TOUR " + currentJ.getName() + " ---------------------\n\n");
			if (currentJ.getName()!="ordi") { this.afficher2Grilles(currentJ, ennemi, null); }

			// GESTION DU TOUR DE L ORDINATEUR
			String coordR;
			if (currentJ.getName() == "ordi") {
				// CHOIX ALEATOIRE DE LA CASE D UN NAVIRE
				Random randP = new Random();
				/*int xR; 
				int yR;
				do { 
					xR = randP.nextInt(this.nCol);
					yR = randP.nextInt(this.nLine);
					coordR = currentJ.getGrid1().xyToString(xR, yR);
				} while (currentJ.getGrid1().estCaseNavire(coordR) == false);*/
				int indNav = randP.nextInt(this.j2.getNavires().size());
				int indCoord = randP.nextInt(this.j2.getNavires().get(indNav).getCasesNavire().size());
				coordR = this.j2.getNavires().get(indNav).getCasesNavire().get(indCoord);
				// ACTION ALEATOIRE System.out.println(coordR);
				int action = randP.nextInt(2);
				if (action == 0) { 
					//System.out.print("\n\n##### ordi a deplacer navire de coord: " + coordR); 
					int f = this.deplacerNavire(currentJ, coordR); 
					deplacementFait = f == 1;
				}
				if (action == 1 || deplacementFait == false) { 
					//System.out.print("\n\n##### ordi a tirer avec navire de coord: " + coordR); 
					this.tirerSurNavire(currentJ, ennemi, coordR); 
				}
			}

			// GESTION DU TOUR DU JOUEUR
			else {
				// DEMANDER LES COORDONNEES DU NAVIRE DU JOUEUR COURANT
				Scanner inCoord = new Scanner (System.in); 
				int indNavire = -1;
				String coord = "";
				do {
					System.out.print("\n\n- QUITTER/SAVE, TAPER qq");
					System.out.print("\n- NAVIRE A UTILISER DANS GRILLE 1, TAPEZ LA CASE DU NAVIRE \nCHOIX => ");
					Scanner innCoord = new Scanner (System.in); 
					coord = innCoord.nextLine();
		  			indNavire = this.trouverNavireAvecCoord(currentJ, coord);
				} while(indNavire < 0 && !coord.equals("qq"));
				if (coord.equals("qq") == true) { this.sauvegarderPartie(); }

				// DEMANDER L'ACTION A EFFECTUER
				Scanner inAction = new Scanner (System.in); 
				String choice = "";
				do {
					System.out.println("\n\n- DEPLACER LE NAVIRE, TAPEZ 1");
					System.out.print("- TIRER AVEC LE NAVIRE, TAPEZ 2");
					System.out.print("\n- QUITTER/SAVE, TAPER qq");
					System.out.print("\nCHOIX => ");
					choice = inAction.nextLine();
				} while(choice.equals("1") == false && choice.equals("2") == false && choice.equals("qq") == false);
				// EXECUTER ACTION
				if (choice.equals("1") == true) { 
					int f = this.deplacerNavire(currentJ, coord); 
					deplacementFait = f == 1;
				}
				if (choice.equals("qq") == true) { this.sauvegarderPartie(); }
				if (choice.equals("2") == true || deplacementFait == false) { this.tirerSurNavire(currentJ, ennemi, coord); }
			}

			// PASSAGE AU PROCHAIN TOUR
			this.setGrid2(currentJ, ennemi);
			if (currentJ.getName()!="ordi") { this.afficher2Grilles(currentJ, ennemi, null); }
			fini = this.nbCaseNavire == ennemi.getCasesNaviresTouches().size();
			if (currentJ.getName()!= "ordi") { System.out.println("\nNB CASE COULE ENNEMI = "+ ennemi.getCasesNaviresTouches().size() + "/" + this.nbCaseNavire); }
			if (fini == true) {
				System.out.println("\n !!! FIN DE LA PARTIE, " + currentJ.getName() + " GAGNE !!! ");
				System.exit(0);
			}
			if (currentJ.getName()!= "ordi") { try { TimeUnit.SECONDS.sleep(2); } catch(Exception e) { System.out.println(e); }}
			tour++;	
		}
	}


	/**
	 * permet de sauvegarder la partie en cours
	 */
	public void sauvegarderPartie() {
		File doss = new File("../../resources/sauvegarde/"); 
		int nbFich = 0;
		for (File fich : doss.listFiles()) { 
			if(fich.getName().startsWith("save")) {
				nbFich++;
			}
		}
		// CREATION DU NOUVEAU FICHIER DE SAUVEGARDE ET VERIFICATION
		try {		
			File f = new File ("../../resources/sauvegarde/" + "save"+ (nbFich+1) +".txt");
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// SAUVEGARDE POUR JOUEUR
			bw.write(this.getJ1().getName() + "\n");
			for (Navire N: this.getJ1().getNavires()) {
				String line = N.getName() + "," + N.getDisposition() + "," + !N.estCoule();
				for (String uneCase: N.getCasesNavire()) {
					line += ",";
					line += uneCase;
					if (this.getJ1().getGrid1().estCaseNavireTouchee(uneCase)) { line += "x"; } 
				}
				line += "\n";
				bw.write(line);
			}
			bw.write("\n");
			// SAUVEGARDE POUR ORDI
			bw.write(this.getJ2().getName() + "\n");
			for (Navire N: this.getJ2().getNavires()) {
				String line = N.getName() + "," + N.getDisposition() + "," + !N.estCoule();
				for (String uneCase: N.getCasesNavire()) {
					line += ",";
					line += uneCase;
					if (this.getJ2().getGrid1().estCaseNavireTouchee(uneCase)) { line += "x"; } 
				}
				line += "\n";
				bw.write(line);
			}
			bw.close();
			System.out.println("\n\n ---------- SAUVEGARDE TERMINEE ----------\n");
		}
		catch (Exception e) { System.err.println(e); }
		System.exit(0);
	}
	

	/**
	 * permet de charger une partie sauvegarder
	 * @param console booléen indiquant si la partie est en mode console ou non
	 * @param nameF nom du fichier de sauvegarde à utiliser
	 */
	public void chargerPartie(boolean console, String nameF) {
		// REPERTOIRE DE SAUVEGARDE
		File doss = new File("../../resources/sauvegarde/"); 

		// AFFICHER LES SAUVEGARDES DISPONIBLES
		int nb = 0;
		for (File fich : doss.listFiles()) { 
			if(fich.getName().startsWith("save") && console==true) {
				System.out.println("- " + fich.getName()); 
				nb++;
			}
		}	
		if (nb == 0 && console == true) { 
			System.out.println("\n !!! AUCUNE SAUVEGARDE TROUVEE, NOUVELLE PARTIE !!!"); 
			this.partie(false);
		}

		else {
			File nouvFich = new File("");
			if (console == true) {
				// CHOIX DU FICHIER A CHARGER
				Scanner inS = new Scanner (System.in); 
				String name = "";
				// POUR CONSOLE
				if (console == true) {
					do {
						System.out.print("\n=> Entrez le nom du fichier de sauvegarde : "); 
						name = inS.nextLine();
						nouvFich = new File ("../../resources/sauvegarde/" + name);
					} while (nouvFich.exists() == false);
				}
			}
			// POUR GRAPHIQUE
			if (console == false) { nouvFich = new File ("../../resources/sauvegarde/" + nameF); }
			
			// LECTURE DU FICHIER
			BufferedReader in = null;
			ArrayList<Navire> navires = new ArrayList<Navire>();
			try {
				in = new BufferedReader(new FileReader(nouvFich));
				String line = "";

				// RECUPERATION DES DONNEES ET INITIALISATION DE LA GRILLE 1 DU JOUEUR 1
				String proprio = ""; 
				while ((line = in.readLine())!= null) {
					if (!line.equals("ordi") && line.length() < 10) { proprio = "!ordi"; continue; }
					if (line.equals("ordi") && line.length() < 10) { proprio = "ordi"; continue;}

					if (!proprio.equals("ordi")) {
						Navire N = this.lineToNavire(line);
						//navires.add(N);
						this.j1.addNavire(N);
						String symbole = N.getSymbole();
						for (String c: N.getCasesNavire()) {
							if (N.getCasesTouchees().contains(c)) { 
								this.j1.getGrid1().setCase("xx", c);
								int indN = this.trouverNavireAvecCoord(this.j1, c);
								this.j1.addCasesNaviresTouches(c, indN);
							}
							else { this.j1.getGrid1().setCase(symbole, c); }
						}
					}
					
					if (proprio.equals("ordi")) {
						Navire N = this.lineToNavire(line);
						//navires.add(N);
						this.j2.addNavire(N);
						String symbole = N.getSymbole();
						for (String c: N.getCasesNavire()) {
							if (N.getCasesTouchees().contains(c)) { 
								this.j2.getGrid1().setCase("xx", c);
								int indN = this.trouverNavireAvecCoord(this.j2, c);
								this.j2.addCasesNaviresTouches(c, indN);
							}
							else { this.j2.getGrid1().setCase(symbole, c); }
						}
					}
				}
			} catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
			catch (IOException e) { System.out.println(e.getMessage()); }
			if (console == true) { this.partie(true);}
		}
	}

	
	/**
	 * permet de charger une ligne de sauvegarde en un navire 
	 * @param line ligne contenant les informations du navire enregistré
	 * @return retourne un navire correspondant à la ligne de sauvegarde
	 */
	public Navire lineToNavire(String line) {
		ArrayList<String> seq = new ArrayList<String>(Arrays.asList(line.split(",")));
		String name = seq.remove(0);
		String disposition = seq.remove(0);
		String pasCoule = seq.remove(0);
		ArrayList<String> casesTouchees = new ArrayList<String>();
		ArrayList<String> cases = new ArrayList<String>();

		for (String coord: seq) {
			String fin = Character.toString(coord.charAt(coord.length()-1));
			if (fin.equals("x")) { 
				coord = coord.replaceAll("x", "");
				casesTouchees.add(coord);
			}
			cases.add(coord);
		}
		
		if (name.equals("CUIRASSE")) {
			int taille = Integer.parseInt(this.CUIRASSE.get("taille"));
			int puissance = Integer.parseInt(this.CUIRASSE.get("puissance"));
			String symbole = this.CUIRASSE.get("symbole");
			Cuirasse cuirasse = new Cuirasse(taille, puissance, symbole);
			cuirasse.setCasesNavire(cases);
			cuirasse.setCasesNavireTouchees(casesTouchees);
			cuirasse.setDisposition(disposition);
			if (pasCoule == "false") { cuirasse.setCasesNavireTouchees(cases); }
			return cuirasse;
		}
		if (name.equals("CROISEUR")) {
			int taille = Integer.parseInt(this.CROISEUR.get("taille"));
			int puissance = Integer.parseInt(this.CROISEUR.get("puissance"));
			String symbole = this.CROISEUR.get("symbole");
			Croiseur croiseur = new Croiseur(taille, puissance, symbole);
			croiseur.setCasesNavire(cases);
			croiseur.setCasesNavireTouchees(casesTouchees);
			croiseur.setDisposition(disposition);
			if (pasCoule == "false") { croiseur.setCasesNavireTouchees(cases); }
			return croiseur;
		}
		if (name.equals("DESTROYER")) {
			int taille = Integer.parseInt(this.DESTROYER.get("taille"));
			int puissance = Integer.parseInt(this.DESTROYER.get("puissance"));
			String symbole = this.DESTROYER.get("symbole");
			Destroyer destroyer = new Destroyer(taille, puissance, symbole);
			destroyer.setCasesNavire(cases);
			destroyer.setCasesNavireTouchees(casesTouchees);
			destroyer.setDisposition(disposition);
			if (pasCoule == "false") { destroyer.setCasesNavireTouchees(cases); }
			return destroyer;
		}
		if (name.equals("SOUSMARIN")) {
			int taille = Integer.parseInt(this.SOUSMARIN.get("taille"));
			int puissance = Integer.parseInt(this.SOUSMARIN.get("puissance"));
			String symbole = this.SOUSMARIN.get("symbole");
			SousMarin sousMarin = new SousMarin(taille, puissance, symbole);
			sousMarin.setCasesNavire(cases);
			sousMarin.setCasesNavireTouchees(casesTouchees);
			sousMarin.setDisposition(disposition);
			if (pasCoule == "false") { sousMarin.setCasesNavireTouchees(cases); }
			return sousMarin;
		}
		return null;
	}
}



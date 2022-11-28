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




public class Controller {
	int nLine = 15; 
	int nCol = 15; 
	String alph = "abcdefghijklmnopqrstuvwxyz";
	Joueur j1; 
	Joueur j2;
	
	final HashMap<String, String> CUIRASSE = new HashMap<String, String>() {{
		put("puissance", "9");
		put("taille", "7");
		put("symbole", "**");
		put("nombre", "1");
	}};

	final HashMap<String, String> CROISEUR = new HashMap<String, String>() {{
		put("puissance", "4");
		put("taille", "5");
		put("symbole", "oo");
		put("nombre", "2");
	}};

	final HashMap<String, String> DESTROYER = new HashMap<String, String>() {{
		put("puissance", "1");
		put("taille", "3");
		put("symbole", "++");
		put("nombre", "3");
	}};

	final HashMap<String, String> SOUSMARIN = new HashMap<String, String>() {{
		put("puissance", "1");
		put("taille", "1");
		put("symbole", "ss");
		put("nombre", "4");
		put("couleur", "4");
	}}; 

	final HashMap<String, String> COULEUR = new HashMap<String, String>() {{
		put("xx", "\033[41m"); // rouge 
		put("tt", "\033[47m"); // bleu  
		put("oo", "\033[0;32m"); // vert
		put("ss", "\033[0;36m"); // cyan
		put("++", "\033[0;35m"); // violet
		put("**", "\033[0;33m"); // jaune
		put("  ", "\033[0;37m"); // blanc
	}};
	
	Controller() {
		this.j1 = new Joueur("joueur", this.alph.substring(0, this.nLine), this.nLine, this.nCol);
		this.j2 = new Joueur("ordi", this.alph.substring(0, this.nLine), this.nLine, this.nCol);
	};

	public Joueur getJoueurAt(int p) {
		if (p == 0) { return this.j2; }
		return this.j1;
	}


	// ------------------------ PLACEMENT DES NAVIRES ------------------------ //
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


	public void setGrid2(Joueur J1, Joueur J2) {
		Grille2 grid2J1 = J1.getGrid2();
		grid2J1.setCases(J2.getGrid1().getCases());
		Grille2 grid2J2 = J2.getGrid2();
		grid2J2.setCases(J1.getGrid1().getCases());
	}


	// ------------------------ AFFICHAGE DES 2 GRILLES ------------------------ //
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
				System.out.print(coul + grid1CurrentJ.getCase(coord) + "\033[0m");	///////////////////////////////////
				System.out.print("|");		
			}
			// REMPLISSAGE DE LA GRILLE 2 DU JOUEUR COURANT
			System.out.print("\t\t\t\t"); 
			System.out.print(alphCol.charAt(i)+"|");	
			for (int j=0; j< this.nCol; j++) {
				char y = alphCol.charAt(i);
				String coord = Character.toString(y) + j;
				String coul = COULEUR.get(grid2CurrentJ.getCase(coord));
				if (casesEclairees != null && casesEclairees.contains(coord)) { //////////////////////////////
					if (grid2CurrentJ.getCase(coord) != "  ") {System.out.print("\033[40m" + grid2CurrentJ.masquerCase(coord) + "\033[0m");}
				}
				else { System.out.print(grid2CurrentJ.getCase(coord));}
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
		Scanner inDeplacer = new Scanner (System.in); 
		// NAVIRE A DEPLACER
		int choice = -1;
		int indNavireADeplacer = this.trouverNavireAvecCoord(j, navireCoord);
		Navire navireADeplacer = j.getNavires().get(indNavireADeplacer);
		ArrayList<String> newCoords = new ArrayList<String>();

		// ON NE PEUT PAS DEPLACER UN NAVIRE TOUCHE 
		if (navireADeplacer.getCasesTouchees().size() != 0) { 
			if (j.getName() != "ordi") { System.out.print("\n!!! NAVIRE TOUCHE, DEPLACEMENT IMPOSSIBLE !!!"); }
			return false; 
		}

		// DEPLACEMENT DU SOUS MARIN
		System.out.println("\nSENS DE DEPLACEMENT");
		if (navireADeplacer.getName() == "SOUSMARIN") { 		
			// DEPLACEMENT PAR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = r.nextInt(0, 4);
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers le haut, tapez 0");
					System.out.println("- Vers le bas, tapez 1");
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n=> CHOIX : ");
					choice = inDeplacer.nextInt();
				} while (choice != 0 && choice != 1 && choice != 2 && choice != 3);
			}
			newCoords = j.deplacer(navireADeplacer, choice);
			// DEPLACEMENT IMPOSSIBLE
			if (newCoords == null) { return false; } 
			// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
			navireADeplacer.setCasesNavire(newCoords);
			j.getNavires().set(indNavireADeplacer, navireADeplacer);
			return true; 
		}

		// DEPLACEMENT HORIZONTAL
		if (navireADeplacer.getDisposition() == "horizontale") {
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = r.nextInt(2, 4);
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers la gauche, tapez 2 ");
					System.out.print("- Vers la droite, tapez 3 \n\n=> choix: ");
					choice = inDeplacer.nextInt();
				} while (choice != 2 && choice != 3 && choice != 9);
			}
		}

		// DEPLACEMENT VERTICAL
		else if (navireADeplacer.getDisposition() == "verticale") {
			// DEPLACEMENT POUR L ORDINATEUR
			if (j.getName() == "ordi") {
				Random r = new Random();
				choice = r.nextInt(0, 2);
			}
			// CHOIX DE DEPLACEMENT POUR JOUEUR PHYSIQUE
			else {
				do {
					System.out.println("- Vers le haut, tapez 0");
					System.out.print("- Vers le bas, tapez 1 \n\n=> choix: ");
					choice = inDeplacer.nextInt();
				} while (choice != 0 && choice != 1);
			}
		}
		newCoords = j.deplacer(navireADeplacer, choice);
		// SI DEPLACEMENT IMPOSSIBLE
		if (newCoords == null) { return false; } 
		// UPDATE DES NOUVELLES COORDONNEES DU NAVIRE DU JOUEUR
		navireADeplacer.setCasesNavire(newCoords);
		j.getNavires().set(indNavireADeplacer, navireADeplacer);
		return true;
	}


	// ------------------------ TIR ------------------------ //
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
				System.out.print("\nCASE CIBLE DANS GRILLE 2 : ");
				coordCible = inTir.nextLine();
			}
		} while (grid2.estCaseTouchee(coordCible) == true);
		
		// ACTION DU TIR
		boolean b = false;
		int[] tabxy = grid2.stringToXY(coordCible); 
		if (grid2.estCaseNavire(coordCible) == false) { grid1Ennemy.setCase("tt", coordCible);}
		if (grid2.estCaseNavire(coordCible) == true) { grid1Ennemy.setCase("xx", coordCible); b = true;}

		for (int x= tabxy[0]-1; x<= tabxy[0]+1; x++) {
			if (pssc<0) { break;}
			for (int y= tabxy[1]-1; y<= tabxy[1]+1; y++) {
				pssc--;
				if (pssc<0) { break;}
				if (x<0 || y<0 || y>=this.nLine || x>=this.nCol) { continue; }
				String c = grid2.xyToString(x, y);
				if (c != coordCible) {
					int indN = this.trouverNavireAvecCoord(ennemy, c);
					if (indN > -1) { ennemy.addCasesNaviresTouches(c, indN); }
					if (grid2.estCaseNavire(c) == false) { grid1Ennemy.setCase("tt", c);}
					if (grid2.estCaseNavire(c) == true) { grid1Ennemy.setCase("xx", c); b = true;}
				}

			}
		}

		// PREMIER TIR DU DESTROYER ------------------------------------
		ArrayList<String> casesEclairees = new ArrayList<>();
		if (j.getNavires().get(indexNavireChoisi).getName() == "DESTROYER" && j.getNavires().get(indexNavireChoisi).getTirFusee() == false) {
			for (int x= tabxy[0]; x< tabxy[0]+4; x++) {
				for (int y= tabxy[1]; y< tabxy[1]+4; y++) {
					if (x<0 || y<0 || y>=this.nLine || x>=this.nCol) { continue; }
					String c = grid2.xyToString(x, y);
					casesEclairees.add(c);
				}
			}
			j.getNavires().get(indexNavireChoisi).tirFusee();
			if (j.getName() != "ordi") {System.out.println("\n !!! PREMIER TIR DU DESTROYER, AFFICHAGE DE LA GRILLE PENDANT 5 SECONDES !!!");}
			this.afficher2Grilles(j, ennemy, casesEclairees);
			for (int i = 1; i < 5; i++) {try { TimeUnit.SECONDS.sleep(1);} catch(Exception e) {System.out.println(e); }}
			if (j.getName() != "ordi") {System.out.println("\n !!! FIN DU TIR DE LA FUSEE ECLAIRANTE !!!");}
			b = true;
		}
		
		if (ennemy.gotNavireCoule() == true && b == true) {
			ArrayList<Integer> l = ennemy.getIndNavireCoule();
			if (ennemy.getName() == "ordi") { System.out.println("\n !!! VOUS AVEZ COULE " + l.size() + " NAVIRE(S) ENNEMI(S)"); }
			if (ennemy.getName() != "ordi") { System.out.println("\n !!! WARNING !!! " + l.size() + " NAVIRE(S) COULE(S) \n"); }
		}
		return b;
	}


	// ------------------------ DEROULEMENT DE PARTIE ------------------------ //
	public void partie(boolean partieChargee) {
		// INITIALISATIONS DES GRILLES 
		if (partieChargee == false) { this.DistribuerNavire(this.j1); }
		this.DistribuerNavire(this.j2);
		this.setGrid2(this.j1, this.j2);
		Joueur currentJ;
		Joueur ennemi;
		int tour = 1;
		boolean fini = false;

		// DEROULEMENT DES TOURS
		while (fini != true) {
			boolean actionEffectuee = false;
			currentJ = this.getJoueurAt(tour%2);
			ennemi = this.getJoueurAt((tour+1)%2);

			// AFFICHAGE DES 2 GRILLES
			System.out.print("\n\n--------------------------------------------------- TOUR DE " + currentJ.getName());
			System.out.print(" ---------------------------------------------------\n\n");
			this.afficher2Grilles(currentJ, ennemi, null);

			// GESTION DU TOUR DE L ORDINATEUR
			if (currentJ.getName() == "ordi") {
				// CHOIX ALEATOIRE DE LA CASE D UN NAVIRE
				Random randP = new Random();
				int xR; 
				int yR;
				String coordR;
				do { 
					xR = randP.nextInt(this.nCol);
					yR = randP.nextInt(this.nLine);
					coordR = currentJ.getGrid1().xyToString(xR, yR);
				} while (currentJ.getGrid1().estCaseNavire(coordR) == false);
				// ACTION ALEATOIRE
				int action = randP.nextInt(2); // false DEPLACER si deplacement impossible,j.deplacer(navireADeplacer, choice) ----------------------------------------------------------------------------
				if (action == 0) { System.out.print("\n\n##### ordi a deplacer navire de coord: " + coordR); this.deplacerNavire(currentJ, coordR); }
				if (action == 1) { System.out.print("\n\n##### ordi a tirer avec navire de coord: " + coordR); this.tirerSurNavire(currentJ, ennemi, coordR); }
			}

			// GESTION DU TOUR DU JOUEUR
			else {
				Scanner inCoord = new Scanner (System.in); 
				// DEMANDER LES COORDONNEES DU NAVIRE DU JOUEUR COURANT
				int indNavire = -1;
				String coord = "";
				do {
					System.out.print("\n\n- NAVIRE A UTILISER DANS GRILLE 1 : \nCHOIX => ");
					coord = inCoord.nextLine();
		  			indNavire = this.trouverNavireAvecCoord(currentJ, coord);
				} while(indNavire < 0);
				// DEMANDER L'ACTION A EFFECTUER
				Scanner inAction = new Scanner (System.in); 
				int choice = -1;
				do {
					System.out.println("\n\n- DEPLACER LE NAVIRE, TAPEZ 1");
					System.out.print("- TIRER AVEC LE NAVIRE, TAPEZ 2");
					System.out.print("\nCHOIX => ");
					choice = inAction.nextInt();
				} while(choice != 1 && choice != 2);
				// EXECUTER ACTION
				if (choice == 1) { this.deplacerNavire(currentJ, coord); }
				else if (choice == 2) { this.tirerSurNavire(currentJ, ennemi, coord); }
			}

			// PASSAGE AU PROCHAIN TOUR
			this.setGrid2(currentJ, ennemi);
			this.afficher2Grilles(currentJ, ennemi, null);
			fini = ennemi.estVaincu();
			if (currentJ.getName()!="ordi") {for (int i = 1; i < 5; i++) {
				int sec = 5 - i;
				System.out.println(sec);
				try { TimeUnit.SECONDS.sleep(1); } catch(Exception e) { System.out.println(e); }
			}}
			tour++;	
		}
	}


	// ------------------------ SAUVEGARDE UNE PARTIE ------------------------ //
	public void sauvegarderPartie(Joueur j) {
		System.out.print("\n\n----- SAUVEGARDE DE PARTIE ----- \n"); 

		String pwd = System.getProperty("user.dir");
		System.out.println("\n\n Repertoire courant : " + pwd);

		// REPERTOIRE DE SAUVEGARDE
		File saveF = new File("../sauvegardes"); 
		File[] saveFolder = saveF.listFiles();

		// CREATION DU NOUVEAU FICHIER DE SAUVEGARDE ET VERIFICATION
		try {		

			File f = new File ("../sauvegardes/" + "saveFile"+(saveFolder.length+1)+".txt");
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// ECRITURE DANS LE FICHIER
			for (Navire N: j.getNavires()) {
				String line = N.getName() + "," + N.getDisposition() + "," + !N.estCoule();
				for (String uneCase: N.getCasesNavire()) {
					line += ",";
					line += uneCase;
				}
				line += "\n";
				bw.write(line);
			}	
			bw.close();
			System.out.println("\n\n ---------- SAUVEGARDE TERMINEE ----------\n");
		}
		catch (Exception e) { System.err.println(e); }
	}


	// ------------------------ CHARGER UNE PARTIE ------------------------ //
	public void chargerPartie() {
		System.out.print("\n\n----- CHARGEMENT DE PARTIE ----- \n\n"); 

		// REPERTOIRE DE SAUVEGARDE
		File saveF = new File("../sauvegardes"); 
		File[] saveFolder = saveF.listFiles();

		// AFFICHER LES SAUVEGARDES DISPONIBLES
		for (int i = 0; i < saveFolder.length; i++) {
			System.out.println("- " + saveFolder[i].getName()); 
		}

		// CHOIX DU FICHIER A CHARGER
		Scanner inS = new Scanner (System.in); 
		String name = "";
		File myFile;
		do {
			System.out.print("\n=> Entrez le nom du fichier de sauvegarde : "); 
			name = inS.nextLine();
			myFile = new File ("../sauvegardes/" + name);
		} while (Arrays.asList(saveFolder).contains(myFile) == false);

		// LECTURE DU FICHIER
		BufferedReader in = null;
		ArrayList<Navire> navires = new ArrayList<Navire>();
		try {
			in = new BufferedReader(new FileReader(myFile));
			String line = "";
			
			// RECUPERATION DES DONNEES ET INITIALISATION DE LA GRILLE 1 DU JOUEUR 1
			while ((line = in.readLine())!= null) {
				Navire N = this.lineToNavire(line);
				navires.add(N);
				this.j1.addNavire(N);
				String symbole = N.getSymbole();
				for (String c: N.getCasesNavire()) {
					this.j1.getGrid1().setCase(symbole, c);
				}
			}


		} catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
		catch (IOException e) { System.out.println(e.getMessage()); }
		try { in.close(); } catch (IOException e) { System.out.println(e.getMessage()); }  

		this.partie(true);
	}

	
	public Navire lineToNavire(String line) {
		ArrayList<String> seq = new ArrayList(Arrays.asList(line.split(",")));
		String name = seq.remove(0);
		String disposition = seq.remove(0);
		String pasCoule = seq.remove(0);
				
		ArrayList<String> coords = new ArrayList<String>();
		for (String coord: seq) {
			coords.add(coord);
		}
		if (name.equals("CUIRASSE")) {
			int taille = Integer.parseInt(this.CUIRASSE.get("taille"));
			int puissance = Integer.parseInt(this.CUIRASSE.get("puissance"));
			String symbole = this.CUIRASSE.get("symbole");
			Cuirasse cuirasse = new Cuirasse(taille, puissance, symbole);
			cuirasse.setCasesNavire(coords);
			cuirasse.setDisposition(disposition);
			if (pasCoule == "false") { cuirasse.setCasesNavireTouchees(coords); }
			return cuirasse;
		}
		if (name.equals("CROISEUR")) {
			int taille = Integer.parseInt(this.CROISEUR.get("taille"));
			int puissance = Integer.parseInt(this.CROISEUR.get("puissance"));
			String symbole = this.CROISEUR.get("symbole");
			Croiseur croiseur = new Croiseur(taille, puissance, symbole);
			croiseur.setCasesNavire(coords);
			croiseur.setDisposition(disposition);
			if (pasCoule == "false") { croiseur.setCasesNavireTouchees(coords); }
			return croiseur;
		}
		if (name.equals("DESTROYER")) {
			int taille = Integer.parseInt(this.DESTROYER.get("taille"));
			int puissance = Integer.parseInt(this.DESTROYER.get("puissance"));
			String symbole = this.DESTROYER.get("symbole");
			Destroyer destroyer = new Destroyer(taille, puissance, symbole);
			destroyer.setCasesNavire(coords);
			destroyer.setDisposition(disposition);
			if (pasCoule == "false") { destroyer.setCasesNavireTouchees(coords); }
			return destroyer;
		}
		if (name.equals("SOUSMARIN")) {
			int taille = Integer.parseInt(this.SOUSMARIN.get("taille"));
			int puissance = Integer.parseInt(this.SOUSMARIN.get("puissance"));
			String symbole = this.SOUSMARIN.get("symbole");
			SousMarin sousMarin = new SousMarin(taille, puissance, symbole);
			sousMarin.setCasesNavire(coords);
			sousMarin.setDisposition(disposition);
			if (pasCoule == "false") { sousMarin.setCasesNavireTouchees(coords); }
			return sousMarin;
		}
		return new Navire(0,0,"");
	}
}

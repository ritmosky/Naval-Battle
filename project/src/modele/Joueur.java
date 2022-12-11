package modele;
import java.util.ArrayList;


/** 
 * La classe implémente la classe Joueur.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Joueur {

	String name;
	private Grille1 grid1;
	private Grille2 grid2;
	private ArrayList<Navire> navires;
	private ArrayList<String> casesNaviresTouches;


	/**
	 * Constructeur
	 * @param s nom attribué au joueur
	 * @param alpha lettres correspondantes aux colonnes
	 * @param l nombre de ligne 
	 * @param c nombre de colonne
	 */
	public Joueur (String s, String alpha, int l, int c){
		this.name = s;
		this.navires = new ArrayList<Navire>();
		this.casesNaviresTouches = new ArrayList<String>(); 
		this.grid1 = new Grille1(alpha, l, c);
		this.grid2 = new Grille2(alpha, l, c);
	} 
	
	/**
	 * retourne la grille 1 du joueur
	 * @return retourne la grille 1 du joueur
	 */
	public Grille1 getGrid1() { return this.grid1; }
	
	/**
	 * update la grille 1 du joueur
	 * @param g nouvelle grille 1 du joueur
	 */
	public void setGrid1(Grille1 g) { this.grid1 = g; }
	
	/**
	 * retourne la grille 2 du joueur
	 * @return retourne la grille 2 du joueur
	 */
	public Grille2 getGrid2() { return this.grid2; }
	
	/**
	 * retourne la liste de navires du joueur
	 * @return retourne la liste de navires du joueur
	 */
	public ArrayList<Navire> getNavires() { return this.navires; }

	/**
	 * La fonction permet d'ajouter un navire à un joueur.
	 * @param n navire à ajouter
	 */
	public void addNavire(Navire n) { this.navires.add(n); }
	
	/**
	 * retourne le nom du joueur
	 * @return retourne le nom du joueur
	 */
	public String getName() { return this.name; }
	
	/**
	 * retourne la liste des cases de navires impactées par un tir
	 * @return retourne la liste des cases de navires impactées par un tir
	 */
	public ArrayList<String> getCasesNaviresTouches() { return this.casesNaviresTouches; }
	
	/**
	 * La fonction permet d'ajouter de ajouter une case touchée par un tir au navire appartenant au joueur.
	 * @param c coordonnée de la case
	 * @param indNavire index du navire dans la liste de navire du joueur
	 */
	public void addCasesNaviresTouches(String c, int indNavire) { 
		if (this.casesNaviresTouches.contains(c) == false) { this.casesNaviresTouches.add(c); }
		if (this.casesNaviresTouches.contains(c) == true) {
			this.getNavires().get(indNavire).addCasesTouchees(c);}
	}

	/**
	 * La fonction renvoie la liste d'index des navires coulés.
	 * @return renvoie la liste d'index des navires coulés.
	 */
	public ArrayList<Integer> getIndNavireCoule() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		int i = 0;
		for (Navire N: this.getNavires()) {
			if (N.estCoule() == true) { l.add(i); }
			i++;
		}
		return l;
	}
	
	/**
	 * La fonction renvoie un booleen suivant que le navire est coulé ou pas.
	 * @return renvoie un booleen suivant que le navire est coulé ou pas.
	 */
	public boolean gotNavireCoule() { return this.getIndNavireCoule().size() != 0; }
	
	/**
	 * La fonction renvoie les nouvelles coordonnées du navire après un déplacement à gauche, si le déplacement est impossible elle renvoit un objet null.
	 * @param N un des navires du joueur
	 * @return renvoie les nouvelles coordonnées du navire après un déplacement à gauche, si le déplacement est impossible elle renvoit un objet null.
	 */
	public ArrayList<String> deplacerAGauche(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int xMin = this.getGrid1().minXCasesNavire(N);
		int y = this.getGrid1().stringToXY(uneCoord)[1];
		if(xMin - 1 < 0) { return null;}
		String coordTest = this.getGrid1().xyToString(xMin - 1, y);
		
		// SI ON SORT DU CADRE OU SI IL Y A UN OBSTACLE
		if (xMin - 1 < 0 || this.getGrid1().estCaseVide(coordTest) == false) { return null; }
			
		int xMax = xMin + N.taille - 1;
		String coordMax = this.getGrid1().xyToString(xMax, y);
		this.getGrid1().setCase("  ", coordMax);

		for (int i = xMin - 1; i < xMax; i++) {
			String newCoord = this.getGrid1().xyToString(i, y);
			this.getGrid1().setCase(symbole, newCoord);
		}
		return newCoords;
	}

	/**
	 * La fonction renvoie les nouvelles coordonnées du navire après un déplacement à droite, si le déplacement est impossible elle renvoit un objet null.
	 * @param N un des navires du joueur
	 * @return renvoie les nouvelles coordonnées du navire après un déplacement à droite, si le déplacement est impossible elle renvoit un objet null.
	 */
	public ArrayList<String> deplacerADroite(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int xMin = this.getGrid1().minXCasesNavire(N);
		int y = this.getGrid1().stringToXY(uneCoord)[1];
		int xMax = xMin + N.taille - 1;
		if(xMax + 1 >= this.getGrid1().nCol) { return null;}
		String coordTest = this.getGrid1().xyToString(xMax + 1, y);
		
		// SI ON SORT DU CADRE OU SI IL Y A UN OBSTACLE
		if (xMax + 1 > this.getGrid1().nCol - 1 || this.getGrid1().estCaseVide(coordTest) == false) { return null; }
			
		String coordMin = this.getGrid1().xyToString(xMin, y);
		this.getGrid1().setCase("  ", coordMin);
		
		for (int i = xMin + 1; i <= xMax + 1; i++) {
			String newCoord = this.getGrid1().xyToString(i, y);
			this.getGrid1().setCase(symbole, newCoord);
			newCoordsArray.add(newCoord);
		}
		return newCoordsArray;
	}

	/**
	 * La fonction renvoie les nouvelles coordonnées du navire après un déplacement en haut, si le déplacement est impossible elle renvoit un objet null.
	 * @param N un des navires du joueur
	 * @return renvoie les nouvelles coordonnées du navire après un déplacement en haut, si le déplacement est impossible elle renvoit un objet null.
	 */
	public ArrayList<String> deplacerEnHaut(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int yMin = this.getGrid1().minYCasesNavire(N);
		int x = this.getGrid1().stringToXY(uneCoord)[0];
		if(yMin - 1 < 0) { return null;}
		String coordTest = this.getGrid1().xyToString(x, yMin - 1);
		
		// SI ON SORT DU CADRE OU SI IL Y A UN OBSTACLE
		if (yMin - 1 < 0 || this.getGrid1().estCaseVide(coordTest) == false) { return null; }
			
		int yMax = yMin + N.taille - 1;
		String coordMax = this.getGrid1().xyToString(x, yMax);
		this.getGrid1().setCase("  ", coordMax);
		
		for (int j = yMin - 1; j < yMax; j++) {
			String newCoord = this.getGrid1().xyToString(x, j);
			this.getGrid1().setCase(symbole, newCoord);
			newCoordsArray.add(newCoord);
		}
		return newCoordsArray;
	}

	/**
	 * La fonction renvoie les nouvelles coordonnées du navire après un déplacement en bas, si le déplacement est impossible elle renvoit un objet null.
	 * @param N un des navires du joueur
	 * @return renvoie les nouvelles coordonnées du navire après un déplacement en bas, si le déplacement est impossible elle renvoit un objet null.
	 */
	public ArrayList<String> deplacerEnBas(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int yMin = this.getGrid1().minYCasesNavire(N);
		int x = this.getGrid1().stringToXY(uneCoord)[0];
		int yMax = yMin + N.taille - 1;
		if(yMax + 1 >= this.getGrid1().nLine) { return null;}
		String coordTest = this.getGrid1().xyToString(x, yMax + 1);
		
		// SI ON SORT DU CADRE OU SI IL Y A UN OBSTACLE
		if (yMax + 1 > this.getGrid1().nLine - 1 || this.getGrid1().estCaseVide(coordTest) == false) { return null; }
			
		String coordMin = this.getGrid1().xyToString(x, yMin);
		this.getGrid1().setCase("  ", coordMin);
		
		for (int j = yMin+1; j <= yMax + 1; j++) {
			String newCoord = this.getGrid1().xyToString(x, j);
			this.getGrid1().setCase(symbole, newCoord);
			newCoordsArray.add(newCoord);
		}
		return newCoordsArray;
	}


	/**
	 * La fonction renvoie les nouvelles coordonnées du navire après un déplacement.
	 * @param N un des navires du joueur
	 * @param sens le sens de déplacement
	 * @return renvoie les nouvelles coordonnées du navire après un déplacement.
	 */
	public ArrayList<String> deplacer(Navire N, String sens) {
		switch(sens) {
		case "0": return deplacerEnHaut(N);
		case "1": return deplacerEnBas(N);
		case "2": return deplacerAGauche(N);
		case "3": return deplacerADroite(N);
		default: return null;
		}
	}
}

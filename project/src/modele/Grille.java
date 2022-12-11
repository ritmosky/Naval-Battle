package modele;
import java.util.ArrayList;
import java.util.Collections;


/** 
 * La classe implémente la classe mère de tous les types de grille.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Grille {
	
	int nLine;
	int nCol;
	String alphCol;
	ArrayList<String> caseValide;
	private String[][] cases;  

	/**
	 * la liste contenant le nom des coordonnées valides
	 */
	public ArrayList<String> caseNom;

	/**
	 * Constructeur
	 * @param alphabet lettres de l'alphabet
	 * @param l nombre de ligne 
	 * @param c nombre de colonne
	 */
	public Grille (String alphabet, int l, int c){
		this.nLine = l;
		this.nCol = c;
		this.alphCol = alphabet;
		this.caseValide = new ArrayList<String>();
		this.caseNom = new ArrayList<String>();
		this.cases = new String[nLine][nCol];
		for (int i= 0; i< nLine; i++) {
			for (int j=0; j< nCol; j++) {
				this.cases[i][j] = "  ";
				this.caseValide.add(this.xyToString(j,i));
			}
		}
		
		for(int s=0; s<this.nCol; s++) {
			for (int i=0; i<this.nLine; i++) {
				this.caseNom.add(Character.toString(this.alphCol.charAt(s))+i);
			}
		}
	}

	/**
	 * retourne le contenu de la case
	 * @param coord coordonnée de la case à interroger
	 * @return retourne le contenu de la case
	 */
	public String getCase(String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		return this.cases[x][y];
	}
	
	/**
	 * La fonction retourne un booléen suivant que la case en paramètre est valide ou non.
	 * @param coord coordonnée de la case
	 * @return retourne si la coordonnée est valide ou pas
	 */
	public boolean estCaseValide(String coord) { return this.caseValide.contains(coord); }
	
	/**
	 * retourne les cases touchées dans la grille
	 * @return retourne les cases touchées
	 */
	public ArrayList<String> getCaseTouche() {
		ArrayList<String> l = new ArrayList<>();
		for (int i= 0; i< nLine; i++) {
			for (int j=0; j< nCol; j++) {
				String c = this.xyToString(i, j);
				if (this.estCaseVideTouchee(c)) { l.add(c); }
			}
		}
		return l;
	}
	
	/**
	 * retourne les cases de la grille
	 * @return retourne les cases de la grille
	 */
	public String[][] getCases() { return this.cases; }
	
	/**
	 * retourne les lettres correspondant aux colonnes de la grille
	 * @return retourne les lettres correspondant aux colonnes de la grille
	 */
	public String getAlphaCol() { return this.alphCol; }

	/**
	 * permet de mettre à jour les contenus des cases de la grille
	 * @param desCases les nouvelles valeurs à stocker
	 */
	public void setCases(String[][] desCases) { this.cases = desCases; }
	
	/**
	 * permet de mettre à jour la valeur d'une case de la grille
	 * @param symbole nouvelle valeur à stocker
	 * @param coord coordonnée de la case à mettre à jour
	 */
	public void setCase(String symbole, String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		this.cases[x][y] = symbole;
	}

	/**
	 * La fonction retourne un tableau d'entier contenant l'index de ligne et de colonne correspondant au paramètre.
	 * @param coord coordonnée de la case
	 * @return retourne le tableau avec l'index de ligne et de colonne
	 */
	public int[] stringToXY(String coord) {
		char l = coord.charAt(0);
		int y = this.alphCol.indexOf(l);
		coord.substring(0,coord.length());
		int x = Integer.parseInt(coord.substring(1));
		int[] tuple = new int[]{x,y};
		return tuple; 
	}

	/**
	 * La fonction retourne une coordonnée correspondant à l'index de ligne et de colonne en paramètre.
	 * @param x index de la colonne
	 * @param y index de la ligne
	 * @return retourne la coordonnée correspondant à l'index de ligne et de colonne en paramètre.
	 */
	public String xyToString(int x, int y) {
		char l = this.alphCol.charAt(y);
		return Character.toString(l) + x;
	}

	
	/**
	 * La fonction retourne un booléen suivant que la case est vide ou pas.
	 * @param coord coordonnée de la case
	 * @return retourne si la case est vide ou pas
	 */
	public boolean estCaseVide(String coord) { return this.getCase(coord) == "  "; }
	
	/**
	 * La fonction retourne un booléen suivant que la case est vide et touchée par un tir ou pas.
	 * @param coord coordonnée de la case
	 * @return boolean
	 */
	public boolean estCaseVideTouchee(String coord) { return this.getCase(coord) == "tt"; }
	
	/**
	 * La fonction retourne un booléen suivant que la case contient un navire touché par un tir ou pas.
	 * @param coord coordonnée de la case
	 * @return retourne si la case est celle d'un navire touché par un tir
	 */
	public boolean estCaseNavireTouchee(String coord) { return this.getCase(coord) == "xx"; }

	/**
	 * La fonction retourne un booléen suivant que la case est touchée par un tir ou pas
	 * @param coord coordonnée de la case
	 * @return retourne si la case est touchée
	 */
	public boolean estCaseTouchee(String coord) { return this.getCase(coord) == "xx" || this.getCase(coord) == "tt"; }
	
	/**
	 * La fonction retourne un booléen suivant que la case contient un navire ou pas.
	 * @param coord coordonnée de la case
	 * @return retourne si la case est celle d'un navire 
	 */
	public boolean estCaseNavire(String coord) { return this.getCase(coord) != "  " && this.getCase(coord) != "tt"; }
	
	/**
	 * La fonction retourne un entier correspondant à l'index de la case la plus à gauche du navire
	 * @param N le navire
	 * @return retourne un entier correspondant à l'index de la case la plus à gauche du navire
	 */
	public int minXCasesNavire(Navire N) {
		ArrayList<Integer> xTab = new ArrayList<Integer>();
		for (String coord: N.getCasesNavire()) {
			int x = stringToXY(coord)[0];
			xTab.add(x);
		}
		return Collections.min(xTab);
	}
	
	/**
	 * La fonction retourne un entier correspondant à l'index de la case la plus en bas du navire
	 * @param N le navire
	 * @return retourne un entier correspondant à l'index de la case la plus en bas du navire
	 */
	public int minYCasesNavire(Navire N) {
		ArrayList<Integer> yTab = new ArrayList<Integer>();
		for (String coord: N.getCasesNavire()) {
			int y = stringToXY(coord)[1];
			yTab.add(y);
		}
		return Collections.min(yTab);
	}
}

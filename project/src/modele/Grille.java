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
	public ArrayList<String> caseNom;

	/**
	 * Constructeur
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
	 * 
	 */
	public String getCase(String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		return this.cases[x][y];
	}
	
	/**
	 * La fonction retourne un booléen suivant que la case en paramètre est valide ou non.
	 * @param coord coordonnée de la case
	 * @return boolean
	 */
	public boolean estCaseValide(String coord) { return this.caseValide.contains(coord); }
	
	/**
	 * 
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
	 * 
	 */
	public String[][] getCases() { return this.cases; }
	
	/**
	 * 
	 */
	public String getAlphaCol() { return this.alphCol; }

	/**
	 * 
	 */
	public void setCases(String[][] desCases) { this.cases = desCases; }
	
	/**
	 * 
	 */
	public void setCase(String symbole, String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		this.cases[x][y] = symbole;
	}

	/**
	 * La fonction retourne un tableau d'entier contenant l'index de ligne et de colonne correspondant au paramètre.
	 * @param coord coordonnée de la case
	 * @return int[]
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
	 * @return String
	 */
	public String xyToString(int x, int y) {
		char l = this.alphCol.charAt(y);
		return Character.toString(l) + x;
	}

	
	/**
	 * La fonction retourne un booléen suivant que la case est vide ou pas.
	 * @param coord coordonnée de la case
	 * @return boolean
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
	 * @return boolean
	 */
	public boolean estCaseNavireTouchee(String coord) { return this.getCase(coord) == "xx"; }

	/**
	 * La fonction retourne un booléen suivant que la case est touchée par un tir ou pas
	 * @param coord coordonnée de la case
	 * @return boolean
	 */
	public boolean estCaseTouchee(String coord) { return this.getCase(coord) == "xx" || this.getCase(coord) == "tt"; }
	
	/**
	 * La fonction retourne un booléen suivant que la case contient un navire ou pas.
	 * @param coord coordonnée de la case
	 * @return boolean
	 */
	public boolean estCaseNavire(String coord) { return this.getCase(coord) != "  " && this.getCase(coord) != "tt"; }
	
	/**
	 * La fonction retourne un entier correspondant à l'index de la case la plus à gauche du navire
	 * @param N un navire
	 * @return int
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
	 * @param N un navire
	 * @return int
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

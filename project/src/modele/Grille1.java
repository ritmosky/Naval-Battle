package modele;
import java.util.ArrayList;
import java.util.Random;

/** 
 * La classe implémente la classe Grille1 contenant les navires du joueur actuelle.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Grille1 extends Grille {
	
	/**
	 * Constructeur
	 * @param alpha lettres correspondantes aux colonnes
	 * @param l nombre de ligne 
	 * @param c nombre de colonne
	 */
	public Grille1(String alpha, int l, int c) { 
		super(alpha, l, c); 
	}
	
	/**
	 * La fonction retourne un booléen suivant que le navire peut se placer dans un sens donné.
	 * @param coord coordonnée de la case
	 * @param tailleNavire taille du navire
	 * @param sens le sens de placement (descendant=0 ou ascendant=1, de la droite vers la gauche=2, de la gauche vers la droite=3)
	 * @return retourne un booléen suivant que le navire peut se placer dans un sens donné.
	 */
	public boolean peutSePlacerSelonUnSens(String coord, int tailleNavire, int sens) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];

		switch(sens) {

		// PLACEMENT DU NAVIRE DANS LA POSITION VERTICALE DU HAUT VERS LE BAS
		case 0: 
			for (int j= y; j< y + tailleNavire; j++) {
				if (j > 14) { return false; }
				String xyString = this.xyToString(x, j);
				if (this.estCaseVide(xyString) == false) { return false; }
			}
			return true;

		// PLACEMENT DU NAVIRE DANS LA POSITION VERTICALE DU BAS VERS LE HAUT
		case 1:
			for (int j= y; j> y - tailleNavire ; j--) {
				if (j < 0) { return false; }
				String xyString = this.xyToString(x, j);
				if (this.estCaseVide(xyString) == false) { return false; }
			}
			return true;

		// PLACEMENT DU NAVIRE DANS LA POSITION HORIZONTALE DE LA DROITE VERS LA GAUCHE
		case 2:
			for (int i= x; i> x - tailleNavire; i--) {
				if (i < 0) { return false; }
				String xyString = this.xyToString(i, y);
				if (this.estCaseVide(xyString) == false) { return false; }
			}
			return true;


			// PLACEMENT DU NAVIRE DANS LA POSITION HORIZONTALE DE LA GAUCHE VERS LA DROITE
		case 3:
			for (int i= x; i< x + tailleNavire; i++) {
				if (i>14) { return false; }
				String xyString = this.xyToString(i, y);
				if (this.estCaseVide(xyString) == false) { return false; }
			}
			return true;
		}
		return false;
	}


	/**
	 * La fonction permet de placer un navire sur la Grille1.
	 * 0 = SENS DESCENDANT
	 * 1 = ASCENDANT
	 * 2 = DE LA DROITE VERS LA GAUCHE
	 * 3 = DE LA GAUCHE VERS LA DROITE
	 * @param N le navire
	 */
	public void placerNavire(Navire N) {
		String symbole = N.symbole;
		String coord;
		int tailleNavire = N.taille;
		int sensPlacement; 
		int x; 
		int y;

		// CAS D UNE NOUVELLE PARTIE
		Random r = new Random();
		do { 
			x = r.nextInt(this.nCol);
			y = r.nextInt(this.nLine);
			sensPlacement = r.nextInt(4);
			coord = this.xyToString(x, y);
		} while (this.peutSePlacerSelonUnSens(coord, tailleNavire, sensPlacement) == false);

		String uneCoord;
		ArrayList<String> coords = new ArrayList<String>();

		switch (sensPlacement) {

		case 0:
			for (int j= y; j< y + tailleNavire; j++) {
				uneCoord = this.xyToString(x, j);
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
			}
			N.setDisposition("verticale");
			N.setCasesNavire(coords);
			return;

		case 1:
			for (int j= y; j> y - tailleNavire; j--) {
				uneCoord = this.xyToString(x, j);		
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
			} 
			N.setDisposition("verticale");
			N.setCasesNavire(coords);
			return;

		case 2:
			for (int i= x; i> x - tailleNavire; i--) {
				uneCoord = this.xyToString(i, y);
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
			} 
			N.setDisposition("horizontale");
			N.setCasesNavire(coords);
			return;
			
		case 3:
			for (int i= x; i< x + tailleNavire; i++) {
				uneCoord = this.xyToString(i, y);
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
			} 
			N.setDisposition("horizontale");
			N.setCasesNavire(coords);
			return;
		}		
	}
}
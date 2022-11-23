package modele;
import java.util.ArrayList;
import java.util.Random;


public class Grille1 extends Grille {
	public Grille1(String alpha, int l, int c) { 
		super(alpha, l, c); 
	}
	
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


	// PERMET DE PLACER UN NAVIRE SUR LA GRILLE 1 SELON UN SENS DE PLACEMENT
	// 0 = SENS DESCENDANT, 1 = ASCENDANT
	// 2 = DE LA DROITE VERS LA GAUCHE, 3 = DE LA GAUCHE VERS LA DROITE
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
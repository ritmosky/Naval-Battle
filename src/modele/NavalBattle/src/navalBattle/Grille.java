package navalBattle;
import java.util.Random;
import java.util.ArrayList;

public class Grille {


	int nLine = Controller.NLINE;
	int nCol = Controller.NCOL;
	String alphCol = Controller.ALPH.substring(0, nLine);;
	private String[][] cases;  


	// ------------------------ CONSTRUCTORS ------------------------ //


	Grille (){
		this.cases = new String[nLine][nCol];

		// ON CREE DES CASES VIDES A L'INITIALISATION
		for (int i= 0; i< nLine; i++) {
			for (int j=0; j< nCol; j++) {
				this.cases[i][j] = "  ";
			}
		}
	}


	// ------------------------ GETTERS ------------------------ //


	public String getCase(String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		return this.cases[x][y];
	}
	
	public String[][] getCases() { return this.cases; }

	public void setCases(String[][] desCases) { this.cases = desCases; }
	
	// ------------------------ SETTERS ------------------------ //


	public void setCase(String symbole, String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		this.cases[x][y] = symbole;
	}


	// ------------------------ METHODS ------------------------ //


	public int[] stringToXY(String coord) {
		char l = coord.charAt(0);
		int y = this.alphCol.indexOf(l);
		coord.substring(0,coord.length());
		int x = Integer.parseInt(coord.substring(1));
		int[] tuple = new int[]{x,y};
		return tuple; 
	}

	public String xyToString(int x, int y) {
		char l = this.alphCol.charAt(y);
		return Character.toString(l) + x;
	}

	public boolean estCaseVide(String coord) {
		return this.getCase(coord) == "  ";
	}

	public boolean estCaseVideTouchee(String coord) {
		return this.getCase(coord) == "tt";
	}

	public boolean estCaseNavireTouchee(String coord) {
		return this.getCase(coord) == "xx";
	}

	public boolean estCaseNavire(String coord) {
		return this.getCase(coord) != "  " && this.getCase(coord) != "tt";
	}
}



class Grille1 extends Grille {


	// ------------------------ CONSTRUCTOR ------------------------ //


	Grille1() { super(); }


	// ------------------------ PLACEMENT DES NAVIRES ------------------------ //


	public boolean peutSePlacerSelonUnSens(String coord, int tailleNavire, int sens) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];


		switch(sens) {

		// PLACEMENT DU NAVIRE DANS LA POSITION VERTICALE DU HAUT VERS LE BAS
		case 0: 
			for (int j= y; j< y + tailleNavire; j++) {
				if (j > 14) {
					return false;
				}
				String xyString = this.xyToString(x, j);
				if (this.estCaseVide(xyString) == false) { 
					return false; 
				}
			}
			return true;


			// PLACEMENT DU NAVIRE DANS LA POSITION VERTICALE DU BAS VERS LE HAUT
		case 1:
			for (int j= y; j> y - tailleNavire ; j--) {
				if (j < 0) {
					return false;
				}
				String xyString = this.xyToString(x, j);
				if (this.estCaseVide(xyString) == false) { 
					return false; 
				}
			}
			return true;


			// PLACEMENT DU NAVIRE DANS LA POSITION HORIZONTALE DE LA DROITE VERS LA GAUCHE
		case 2:
			for (int i= x; i> x - tailleNavire; i--) {
				if (i < 0) {
					return false;
				}
				String xyString = this.xyToString(i, y);
				if (this.estCaseVide(xyString) == false) { 
					return false; 
				}
			}
			return true;


			// PLACEMENT DU NAVIRE DANS LA POSITION HORIZONTALE DE LA GAUCHE VERS LA DROITE
		case 3:
			for (int i= x; i< x + tailleNavire; i++) {
				if (i>14) {
					return false;
				}
				String xyString = this.xyToString(i, y);
				if (this.estCaseVide(xyString) == false) { 
					return false; 
				}
			}
			return true;
		}
		return false;
	}


	// PERMET DE PLACER UN NAVIRE SUR LA GRILLE 1 SELON UN SENS DE PLACEMENT
	// 0 = SENS DESCENDANT, 1 = ASCENDANT
	// 2 = DE LA DROITE VERS LA GAUCHE, 3 = DE LA GAUCHE VERS LA DROITE
	
	public void placerNavire(Navire N) {
		Random r = new Random();
		String symbole = N.symbole;
		int tailleNavire = N.taille;
		int sensPlacement; 
		int x; 
		int y;
		String coord;

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
				N.addCasesNavire(coord);
			}
			N.setDisposition("verticale");
			return;

		case 1:
			for (int j= y; j> y - tailleNavire; j--) {
				uneCoord = this.xyToString(x, j);		
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
				N.addCasesNavire(coord);
			} 
			N.setDisposition("verticale");
			return;

		case 2:
			for (int i= x; i> x - tailleNavire; i--) {
				uneCoord = this.xyToString(i, y);
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
				N.addCasesNavire(coord);
			} 
			N.setDisposition("horizontale");
			return;
			
		case 3:
			for (int i= x; i< x + tailleNavire; i++) {
				uneCoord = this.xyToString(i, y);
				this.setCase(symbole, uneCoord);
				coords.add(uneCoord);
				N.addCasesNavire(coord);
			} 
			N.setDisposition("horizontale");
			return;
		}		
	}
}


class Grille2 extends Grille {


	// ------------------------ CONSTRUCTOR ------------------------ //


	Grille2() { super(); }


	// ------------------------ METHODS ------------------------ //


	public String masquerCase(String coord) {
		if (this.estCaseNavire(coord) == true) { 
			return "  ";
		}
		return this.getCase(coord);
	}
}

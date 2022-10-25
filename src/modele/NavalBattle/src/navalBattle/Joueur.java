package navalBattle;
import java.util.ArrayList;


public class Joueur {


	String name;
	private Grille1 grid1;
	private Grille2 grid2;
	private ArrayList<Navire> navires;


	// ------------------------ CONSTRUCTOR ------------------------ //


	Joueur (String s){
		this.name = s;
		this.navires = new ArrayList<Navire>();
		this.grid1 = new Grille1();
		this.grid2 = new Grille2();
	} 


	// ------------------------ GETTERS ------------------------ //


	public Grille1 getGrid1() { return this.grid1; }	

	public Grille2 getGrid2() { return this.grid2; }

	public ArrayList<Navire> getNavires() { return this.navires; }


	// ------------------------ METHODS ------------------------ //


	public void addNavire(Navire n) {
		this.navires.add(n);
	}


	public boolean estVaincu() {
		for (Navire N: this.getNavires()) {
			if (N.estCoule() == false) {
				return false;
			}
		}
		return true;
	}


	public ArrayList<String> deplacerAGauche(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		int minX = this.getGrid1().minXCasesNavire(N);

		for (String coord: N.getCasesNavire()) { 
			int x = grid1.stringToXY(coord)[0]; 
			int y = grid1.stringToXY(coord)[1]; 
			
			if (x-1 < 0) { return null; }
			
			String xyString = grid1.xyToString(x-1, y);

			if (this.getGrid1().estCaseVide(xyString) == false && x < minX) { 
				System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
				return null; 
			}
			// MODIFICATION DE LA GRILLE 1
			this.grid1.setCase("  ", coord);
			this.grid1.setCase(symbole, xyString);
			// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
			newCoords.add(xyString);
		}
		return newCoords;
	}

	public ArrayList<String> deplacerADroite(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		int minX = this.getGrid1().minXCasesNavire(N);

		for (String coord: N.getCasesNavire()) { 
			int x = grid1.stringToXY(coord)[0]; 
			int y = grid1.stringToXY(coord)[1]; 
			
			if (x+1 > this.getGrid1().nCol-1) { return null; }
			
			String xyString = grid1.xyToString(x+1, y);

			if (this.getGrid1().estCaseVide(xyString) == false && x > minX + N.taille - 1) { 
				System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
				return null; 
			}
			// MODIFICATION DE LA GRILLE 1
			this.grid1.setCase("  ", coord);
			this.grid1.setCase(symbole, xyString);
			// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
			newCoords.add(xyString);
		}
		return newCoords;
	}


	public ArrayList<String> deplacerEnHaut(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		int minY = this.getGrid1().minYCasesNavire(N);

		for (String coord: N.getCasesNavire()) { 
			int x = grid1.stringToXY(coord)[0]; 
			int y = grid1.stringToXY(coord)[1]; 
			
			if (y-1 < 0) { return null; }
			
			String xyString = grid1.xyToString(x, y-1);
			
			if (this.getGrid1().estCaseVide(xyString) == false && y < minY) { 
				System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
				return null; 
			}
			// MODIFICATION DE LA GRILLE 1
			this.grid1.setCase("  ", coord);
			this.grid1.setCase(symbole, xyString);
			// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
			newCoords.add(xyString);
		}
		return newCoords;
	}


	public ArrayList<String> deplacerEnBas(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		int minY = this.getGrid1().minYCasesNavire(N);

		for (String coord: N.getCasesNavire()) { 
			int x = grid1.stringToXY(coord)[0]; 
			int y = grid1.stringToXY(coord)[1]; 
			
			if (y+1 > this.getGrid1().nLine-1) { return null; }
			
			String xyString = grid1.xyToString(x, y+1);

			if (this.getGrid1().estCaseVide(xyString) == false && y > minY + N.taille - 1) { 
				System.out.print("\n\n- !!! Presence d obstacles, Deplacement impossible !!! \n");
				return null; 
			}
			// MODIFICATION DE LA GRILLE 1
			this.grid1.setCase("  ", coord);
			this.grid1.setCase(symbole, xyString);
			// STOCKAGE DES NOUVEAUX COORDONNEES DU NAVIRE
			newCoords.add(xyString);
		}
		return newCoords;
	}


	public ArrayList<String> deplacer(Navire N, int sens) {
		
		switch(sens) {
		case 0:
			return deplacerEnHaut(N);
		case 1:
			return deplacerEnBas(N);
		case 2:
			return deplacerAGauche(N);
		case 3:
			return deplacerADroite(N);
		default:
			return null;
		}
	}
}

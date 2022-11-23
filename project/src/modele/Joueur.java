package modele;
import java.util.ArrayList;


public class Joueur {

	String name;
	private Grille1 grid1;
	private Grille2 grid2;
	private ArrayList<Navire> navires;

	public Joueur (String s, String alpha, int l, int c){
		this.name = s;
		this.navires = new ArrayList<Navire>();
		this.grid1 = new Grille1(alpha, l, c);
		this.grid2 = new Grille2(alpha, l, c);
	} 
	
	
	public Grille1 getGrid1() { return this.grid1; }	

	public Grille2 getGrid2() { return this.grid2; }

	public ArrayList<Navire> getNavires() { return this.navires; }

	public void addNavire(Navire n) { this.navires.add(n); }
	
	public String getName() { return this.name; }


	public boolean estVaincu() {
		for (Navire N: this.getNavires()) {
			if (N.estCoule() == false) { return false; }
		}
		return true;
	}


	public ArrayList<String> deplacerAGauche(Navire N) {
		ArrayList<String> newCoords = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int xMin = this.getGrid1().minXCasesNavire(N);
		int y = this.getGrid1().stringToXY(uneCoord)[1];
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

	
	public ArrayList<String> deplacerADroite(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int xMin = this.getGrid1().minXCasesNavire(N);
		int y = this.getGrid1().stringToXY(uneCoord)[1];
		int xMax = xMin + N.taille - 1;
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


	public ArrayList<String> deplacerEnHaut(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int yMin = this.getGrid1().minYCasesNavire(N);
		int x = this.getGrid1().stringToXY(uneCoord)[0];
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


	public ArrayList<String> deplacerEnBas(Navire N) {
		ArrayList<String> newCoordsArray = new ArrayList<String>();
		String symbole = N.symbole;
		String uneCoord = N.getCasesNavire().get(0);
		int yMin = this.getGrid1().minYCasesNavire(N);
		int x = this.getGrid1().stringToXY(uneCoord)[0];
		int yMax = yMin + N.taille - 1;
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

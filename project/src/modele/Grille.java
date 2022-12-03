package modele;
import java.util.ArrayList;
import java.util.Collections;


public class Grille {
	
	int nLine;
	int nCol;
	String alphCol;
	ArrayList<String> caseValide;
	private String[][] cases;  


	public Grille (String alphabet, int l, int c){
		this.nLine = l;
		this.nCol = c;
		this.alphCol = alphabet;
		this.caseValide = new ArrayList<String>();
		this.cases = new String[nLine][nCol];
		for (int i= 0; i< nLine; i++) {
			for (int j=0; j< nCol; j++) {
				this.cases[i][j] = "  ";
				this.caseValide.add(this.xyToString(j,i));
			}
		}
	}

	public String getCase(String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		return this.cases[x][y];
	}
	
	public boolean estCaseValide(String coord) { return this.caseValide.contains(coord); }
	
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
	
	public String[][] getCases() { return this.cases; }
	
	public String getAlphaCol() { return this.alphCol; }

	public void setCases(String[][] desCases) { this.cases = desCases; }

	public void setCase(String symbole, String coord) {
		int x = this.stringToXY(coord)[0];
		int y = this.stringToXY(coord)[1];
		this.cases[x][y] = symbole;
	}

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

	public boolean estCaseVide(String coord) { return this.getCase(coord) == "  "; }

	public boolean estCaseVideTouchee(String coord) { return this.getCase(coord) == "tt"; }
	
	public boolean estCaseNavireTouchee(String coord) { return this.getCase(coord) == "xx"; }

	public boolean estCaseTouchee(String coord) { return this.getCase(coord) == "xx" || this.getCase(coord) == "tt"; }
	
	public boolean estCaseNavire(String coord) { return this.getCase(coord) != "  " && this.getCase(coord) != "tt"; }
	
	public int minXCasesNavire(Navire N) {
		ArrayList<Integer> xTab = new ArrayList<Integer>();
		for (String coord: N.getCasesNavire()) {
			int x = stringToXY(coord)[0];
			xTab.add(x);
		}
		return Collections.min(xTab);
	}
	
	public int minYCasesNavire(Navire N) {
		ArrayList<Integer> yTab = new ArrayList<Integer>();
		for (String coord: N.getCasesNavire()) {
			int y = stringToXY(coord)[1];
			yTab.add(y);
		}
		return Collections.min(yTab);
	}
}

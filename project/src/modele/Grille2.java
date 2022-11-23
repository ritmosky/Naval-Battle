package modele;



public class Grille2 extends Grille {
	public Grille2(String alpha, int l, int c) { 
		super(alpha, l, c); 
	}

	public String masquerCase(String coord) {
		if (this.estCaseVide(coord) == false && this.estCaseNavireTouchee(coord) == false && this.estCaseVideTouchee(coord) == false) { 
			return "  ";
		}
		return this.getCase(coord);
	}
}

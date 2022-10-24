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
}

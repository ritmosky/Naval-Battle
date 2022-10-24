package navalBattle;
import java.util.ArrayList;


public class Navire {

	int taille;
	int puissance; 
	String symbole;
	String disposition; // horizontale ou verticale
	
	private ArrayList<String> cases;
	private ArrayList<String> casesTouchees;
	

	// ------------------------ CONSTRUCTORS ------------------------ //
	
	
	Navire() {};
	
	Navire (int t, int p, String s){
		this.taille = t;
		this.puissance = p;
		this.symbole = s;
		this.disposition = "";
		this.cases = new ArrayList<String>();
		this.casesTouchees = new ArrayList<String>();
	}
	
	
	// ------------------------ GETTERS & SETTERS ------------------------ //
	
	
	//public int getTaille() { return this.taille; }
	
	//public int getPuissance() { return this.puissance; }
	
	//public String getSymbole() { return this.symbole; }
	
	//public String getDisposition() { return this.disposition; }
	
	public ArrayList<String> getCasesNavire() { return this.cases; }
	
	public ArrayList<String> getCasesTouchees() { return this.casesTouchees; }
	
	public void setDisposition(String disposition) { this.disposition = disposition; }
	
	public void setCasesNavire(ArrayList<String> newcases) { this.cases = newcases; }
	
	// ------------------------ METHODS ------------------------ //
	
	
	public boolean estCoule() { return this.taille == this.casesTouchees.size(); }
	
	public boolean estDestroyer() { return false; }
	
	public boolean estSousMarin() { return false; }
	
	public void addCasesNavire(String coord) { this.cases.add(coord); }
	
	public void addCasesTouchees(String coord) { this.casesTouchees.add(coord); }
}



class Cuirasse extends Navire {
	
	
	// ------------------------ CONSTRUCTOR ------------------------ //

	
	Cuirasse(){
		super();
		this.taille = Integer.parseInt(Controller.CUIRASSE.get("taille"));
		this.puissance = Integer.parseInt(Controller.CUIRASSE.get("puissance"));
		this.symbole = Controller.CUIRASSE.get("symbole");
	}
}



class Croiseur extends Navire {
	
	
	// ------------------------ CONSTRUCTOR ------------------------ //

	
	Croiseur(){
		super();
		this.taille = Integer.parseInt(Controller.CROISEUR.get("taille"));
		this.puissance = Integer.parseInt(Controller.CROISEUR.get("puissance"));
		this.symbole = Controller.CROISEUR.get("symbole");
	}
}



class Destroyer extends Navire {
	
	boolean premierTirEffectue;
	
	
	// ------------------------ CONSTRUCTOR ------------------------ //
	
	
	Destroyer(){
		super();
		this.taille = Integer.parseInt(Controller.DESTROYER.get("taille"));
		this.puissance = Integer.parseInt(Controller.DESTROYER.get("puissance"));
		this.symbole = Controller.DESTROYER.get("symbole");
		this.premierTirEffectue = false;
	}
	
	
	// ------------------------ METHODS ------------------------ //

	
	@Override
	public boolean estDestroyer() { return true; }
	
	public void premierTir() { this.premierTirEffectue = true; }
}



class SousMarin extends Navire {
	
	
	// ------------------------ CONSTRUCTOR ------------------------ //

	
	SousMarin(){
		super();
		this.taille = Integer.parseInt(Controller.SOUSMARIN.get("taille"));
		this.puissance = Integer.parseInt(Controller.SOUSMARIN.get("puissance"));
		this.symbole = Controller.SOUSMARIN.get("symbole");
	}
	
	
	// ------------------------ METHODS ------------------------ //

	
	@Override
	public boolean estSousMarin() { return true; }
}
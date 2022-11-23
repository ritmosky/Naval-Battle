package modele;
import java.util.ArrayList;


public class Navire {

	int taille;
	int puissance; 
	String symbole;
	String disposition;
	String name;
	private ArrayList<String> cases;
	private ArrayList<String> casesTouchees;
	
	public Navire (int t, int p, String s){
		this.taille = t;
		this.puissance = p;
		this.symbole = s;
		this.disposition = "";
		this.cases = new ArrayList<String>();
		this.casesTouchees = new ArrayList<String>();
	}
	
		
	public ArrayList<String> getCasesNavire() { return this.cases; }
	
	public ArrayList<String> getCasesTouchees() { return this.casesTouchees; }
	
	public void setDisposition(String disposition) { this.disposition = disposition; }
	
	public String getDisposition() { return this.disposition; }
	
	public String getSymbole() { return this.symbole; }
	
	public String getName() { return this.name; }
	
	public void setCasesNavire(ArrayList<String> newcases) { this.cases = newcases; }
	
	public void setCasesNavireTouchees(ArrayList<String> newcases) { this.casesTouchees = newcases; }
	
	
	
	public boolean estCoule() { 
		if (this.casesTouchees == null) {
			return false;
		}
		return this.taille == this.casesTouchees.size(); 
	}
				
	public void addCasesTouchees(String coord) { this.casesTouchees.add(coord); }
	
	public boolean getPremierTirEffectue() { return false;}
	
	public void premierTir() {}
}

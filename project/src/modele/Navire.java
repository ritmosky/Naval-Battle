package modele;
import java.util.ArrayList;


/** 
 * La classe implémente la classe mère de tous les types de Navires.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Navire {

	int taille;
	int puissance; 
	String symbole;
	String disposition;
	String name;
	private ArrayList<String> cases;
	private ArrayList<String> casesTouchees;
	
	/**
	 * 
	 */
	public Navire (int t, int p, String s){
		this.taille = t;
		this.puissance = p;
		this.symbole = s;
		this.disposition = "";
		this.cases = new ArrayList<String>();
		this.casesTouchees = new ArrayList<String>();
	}
	
	/**
	 * 
	 */
	public ArrayList<String> getCasesNavire() { return this.cases; }
	
	/**
	 * 
	 */
	public ArrayList<String> getCasesTouchees() { return this.casesTouchees; }
	
	/**
	 * 
	 */
	public void setDisposition(String disposition) { this.disposition = disposition; }
	
	/**
	 * 
	 */
	public String getDisposition() { return this.disposition; }
	
	/**
	 * 
	 */
	public int getPuissance() { return this.puissance; }
	
	/**
	 * 
	 */
	public String getSymbole() { return this.symbole; }
	
	/**
	 * 
	 */
	public String getName() { return this.name; }
	
	/**
	 * 
	 */
	public void setCasesNavire(ArrayList<String> newcases) { this.cases = newcases; }
	
	/**
	 * 
	 */
	public void setCasesNavireTouchees(ArrayList<String> newcases) { this.casesTouchees = newcases; }
	
	/**
	 * La fonction retourne un booléen suivant que le navire est coulé ou non.
	 * @return boolean
	 */
	public boolean estCoule() { 
		if (this.casesTouchees == null) { return false; }
		return this.taille == this.casesTouchees.size(); 
	}
				
	/**
	 * La fonction permet d'ajouter des cases touchées par un tir ennemi.
	 * @param coord coordonnée de la case
	 * @return void
	 */
	public void addCasesTouchees(String coord) { this.casesTouchees.add(coord); }
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 * @return void
	 */
	public void tirFusee() {}
	
	/**
	 * La fonction renvoie un booleen suivant que le premier tir de fusée (par le Destroyer) est effectué ou pas.
	 * @return boolean
	 */
	public boolean getTirFusee() { return false;}
}

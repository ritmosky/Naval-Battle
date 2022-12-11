package modele;
import java.util.ArrayList;


/** 
 * La classe implémente la classe mère de tous les types de Navires.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public abstract class Navire {

	int taille;
	int puissance; 
	String symbole;
	String disposition;
	String name;
	private ArrayList<String> cases;
	private ArrayList<String> casesTouchees;
	
	/** 
	 * Constructeur
	 * @param t taille du navire
	 * @param p puissance du navire
	 * @param s symbole du navire
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
	 * retourne les cases du navires
	 * @return retourne les cases du navire
	 */
	public ArrayList<String> getCasesNavire() { return this.cases; }
	
	/**
	 * retourne les cases impactées du navire
	 * @return retourne les cases impactées du navire
	 */
	public ArrayList<String> getCasesTouchees() { return this.casesTouchees; }
	
	/**
	 * permet de changer la disposition du navire
	 * @param disposition nouvelle disposition
	 */
	public void setDisposition(String disposition) { this.disposition = disposition; }
	
	/**
	 * retourne la disposition du navire
	 * @return retourne la disposition du navire
	 */
	public String getDisposition() { return this.disposition; }
	
	/**
	 * retourne la puissance du navire
	 * @return retourne la puissance du navire
	 */
	public int getPuissance() { return this.puissance; }
	
	/**
	 * retourne le symbole du navire
	 * @return retourne le symbole du navire
	 */
	public String getSymbole() { return this.symbole; }
	
	/**
	 * retourne le nom du navire
	 * @return retourne le nom du navire
	 */
	public String getName() { return this.name; }
	
	/**
	 * permet de mettre à jour les cases du navire
	 * @param newcases les nouvelles cases du navire
	 */
	public void setCasesNavire(ArrayList<String> newcases) { this.cases = newcases; }
	
	/**
	 * permet de mettre à jour les cases du navire touchées par un tir
	 * @param newcases les nouvelles cases touchées par un tir
	 */
	public void setCasesNavireTouchees(ArrayList<String> newcases) { this.casesTouchees = newcases; }
	
	/**
	 * La fonction retourne un booléen suivant que le navire est coulé ou non.
	 * @return retourne un booléen indiquant si le navire est coulé ou pas
	 */
	public boolean estCoule() { 
		if (this.casesTouchees == null) { return false; }
		return this.taille == this.casesTouchees.size(); 
	}
				
	/**
	 * La fonction permet d'ajouter des cases touchées par un tir ennemi.
	 * @param coord coordonnée de la case
	 */
	public void addCasesTouchees(String coord) { this.casesTouchees.add(coord); }
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 */
	public abstract void tirFusee();
	
	/**
	 * La fonction renvoie un booleen suivant que le premier tir de fusée (par le Destroyer) est effectué ou pas.
	 * @return retourne un booléen suivant que le tir de fusée a été fait ou pas
	 */
	public boolean getTirFusee() { return false;}
}

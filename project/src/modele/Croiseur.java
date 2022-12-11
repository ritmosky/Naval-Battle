package modele;

/** 
 * La classe repésente les navires de type Croiseur.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Croiseur extends Navire {
	/**
	 * 
	 */
	public Croiseur (int t, int p, String s){
		super(t,p,s);
		this.name = "CROISEUR";
	}
}

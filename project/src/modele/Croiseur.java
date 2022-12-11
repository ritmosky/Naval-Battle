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
	 * Constructeur
	 * @param t taille du navire
	 * @param p puissance du navire
	 * @param s symbole du navire
	 */
	public Croiseur (int t, int p, String s){
		super(t,p,s);
		this.name = "CROISEUR";
	}
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 */
	@Override
	public void tirFusee() {}
}

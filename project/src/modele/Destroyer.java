package modele;

/** 
 * La classe repésente les navires de type Destroyer.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Destroyer extends Navire {
	
	boolean fusee;

	/** 
	 * Constructeur
	 * @param t taille du navire
	 * @param p puissance du navire
	 * @param s symbole du navire
	 */
	public Destroyer (int t, int p, String s){
		super(t,p,s);
		this.fusee = false;
		this.name = "DESTROYER";
	}
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 */
	@Override
	public void tirFusee() { this.fusee = true; }
	
	/**
	 * La fonction renvoie un booleen suivant que le premier tir de fusée (par le Destroyer) est effectué ou pas.
	 * @return retourne un booléen suivant que le tir de fusée a été effectué ou pas
	 */
	@Override
	public boolean getTirFusee() { return this.fusee; }
}

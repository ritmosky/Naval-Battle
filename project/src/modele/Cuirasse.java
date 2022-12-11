package modele;

/** 
 * La classe repésente les navires de type Cuirasse.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class Cuirasse extends Navire {
	
	/** 
	 * Constructeur
	 * @param t taille du navire
	 * @param p puissance du navire
	 * @param s symbole du navire
	 */
	public Cuirasse (int t, int p, String s){
		super(t,p,s);
		this.name = "CUIRASSE";
	}
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 */
	@Override
	public void tirFusee() {}
}

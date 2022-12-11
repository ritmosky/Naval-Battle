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
	 * 
	 */
	public Destroyer (int t, int p, String s){
		super(t,p,s);
		this.fusee = false;
		this.name = "DESTROYER";
	}
	
	/**
	 * La fonction permet de préciser que le premier tir de fusée a été utilisé.
	 * Elle sera redéfinie dans la classe Destroyer.
	 * @return void
	 */
	@Override
	public void tirFusee() { this.fusee = true; }
	
	/**
	 * La fonction renvoie un booleen suivant que le premier tir de fusée (par le Destroyer) est effectué ou pas.
	 * @return boolean
	 */
	@Override
	public boolean getTirFusee() { return this.fusee; }
}

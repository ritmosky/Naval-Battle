package modele;


/** 
 * La classe SousMarin repésente les navires de type Sous Marin.
 * @author OUEDRAOGO Taoufiq
 * @author FONDELOT Timothee
 * @author NSONGO David
 * @author TAKOUGNADI Junior
 */
public class SousMarin extends Navire {
	public SousMarin (int t, int p, String s){
		super(t,p,s);
		this.name = "SOUSMARIN";
	}
}
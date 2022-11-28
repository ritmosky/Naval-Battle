package modele;

public class Destroyer extends Navire {
	
	boolean fusee;

	public Destroyer (int t, int p, String s){
		super(t,p,s);
		this.fusee = false;
		this.name = "DESTROYER";
	}
	@Override
	public void tirFusee() { this.fusee = true; }
	@Override
	public boolean getTirFusee() { return this.fusee; }
}

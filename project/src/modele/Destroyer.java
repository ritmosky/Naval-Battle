package modele;

public class Destroyer extends Navire {
	
	boolean premierTirEffectue;

	public Destroyer (int t, int p, String s){
		super(t,p,s);
		this.name = "DESTROYER";
	}
	
	@Override
	public void premierTir() { this.premierTirEffectue = true; }
	
	@Override
	public boolean getPremierTirEffectue() { return this.premierTirEffectue; }
}

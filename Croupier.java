import java.io.Serializable;
import java.util.Scanner;

public class Croupier extends Joueur implements Serializable{

	public Croupier(String nom) {
		super(nom);
		this.nom = "croupier";
	}
	
	
	public boolean hit_stand() {
		
			if (this.getMain().getscore()>16) {
				hit = false;
			}
			else {
				System.out.println("Le croupier pioche");
			}
		
		return hit;
	}
		
}

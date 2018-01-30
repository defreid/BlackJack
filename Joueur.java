import java.io.Serializable;
import java.util.Scanner;

public class Joueur implements Serializable{
	
		protected String nom;
		private Main main;
		protected boolean hit;
		private boolean elimine;
		private int nbHit;
		
		public Joueur(String nom) {
			this.nom = nom;
			this.main = new Main();
			this.hit = true;
			this.nbHit = 0;
		}

		public int getNbHit() {
			return nbHit;
		}

		public void setNbHit(int nbHit) {
			this.nbHit = nbHit;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public Main getMain() {
			return main;
		}

		public void setMain(Main main) {
			this.main = main;
		}
		
		
		public boolean isHit() {
			
			return hit;
		}

		public void setHit(boolean hit) {
			this.hit = hit;
		}
		
		public void setStand() {
			this.hit = false;
			System.out.println(this.hit);
		}

		public boolean hit_stand(int choix) {
			
			if(this.hit = true) {
				
					if(choix == 2) {
						hit = false;
					}
				
			}
		
			return hit;
		}
		
		
		
}

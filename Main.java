import java.io.Serializable;
import java.util.ArrayList;

public class Main extends Paquet implements Serializable{
	
	private ArrayList<Carte> hand;
	private int valeur;
	private boolean blackjack;
	
	
	public Main() {
		this.blackjack = false;
		this.hand = new ArrayList<Carte>();
	}
	
	
	//Méthode d'ajout de carte à la main
	public void ajouterCarte (Carte carte) {
		hand.add(carte);
	}
	
	public Carte getcarte(int i) {
		Carte carteC = null;
		
		carteC = hand.get(i);
		
		return carteC;
	}
	
	//Méthode pour compter les points de la main
	public int getscore() {
		int score = 0;
		int scorepond = 0;
		boolean as = false;
		
		for (Carte carte : hand ) {
			if(score > 21 && carte.getNom() == Nom.As) {
				carte.setValeur(1);
			}
			score += carte.getValeur();
			if (carte.getNom() == Nom.As) {
				as = true;
			}
		}
		
			if ((score > 21) && (as)) {
				score = score-10;
			}		
		
		if (hand.size() == 2 && score == 21) {
			for (Carte carte : hand ) {
				if(carte.getNom() == Nom.As)
					this.blackjack = true;
			}
		}
		
		return score;
	}

	public ArrayList<Carte> getHand() {
		return hand;
	}
	
	/*public Carte piocher(Paquet deck) {
		Carte carte = deck.distribuer();
		this.ajouterCarte(carte);
		return carte;
	}*/


	@Override
	public String toString() {
		return "Main [hand=" + hand + " score :"+this.getscore()+  "]";
	}
	
	
	
	
	
	
	
	


}

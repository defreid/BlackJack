import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientImpl extends UnicastRemoteObject implements Client, Unreferenced {
	
	
	
	protected ClientImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void notification(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(nom+"\n");
	}
	
	@Override
	public void unreferenced() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) throws RemoteException, MalformedURLException {
		
		
		/*// Démarre le rmiregistry
		LocateRegistry.createRegistry(1100);
		// Crée et installe un gestionnaire de sécurité
		 // inutile si on ne télécharge pas les classes des stubs et parametres
		// System.setSecurityManager(new RMISecurityManager());
		ClientImpl cli = new ClientImpl();
		Naming.rebind("ClientImpl", cli);
		System.out.println("Client déclaré");*/
		System.out.println("Nom du joueur?");
		Scanner sc = new Scanner(System.in);	
		String choix = sc.nextLine();
		ArrayList<Carte> main = new ArrayList<Carte>();
		

		/*
		// Démarre le rmiregistry
		LocateRegistry.createRegistry(1100);
		// Crée et installe un gestionnaire de sécurité
		// inutile si on ne télécharge pas les classes des stubs et parametres
		// System.setSecurityManager(new RMISecurityManager());
		ClientImpl client = new ClientImpl();
		Naming.rebind("Client", client);	
		*/
		
		
		try {
		// Récupération d'un proxy sur l'objet
			Serveur serveur = (Serveur) Naming.lookup("//localhost/Serveur");
			System.out.println(serveur.connexionJoueur(choix));
			System.out.println(serveur.attenteJoueur());
			//System.out.println("affichage main");
			System.out.println(serveur.affichageMains());
			boolean hit = true;
			while (hit == true && serveur.joueur(choix).getMain().getscore() < 21 ) {
				int score = serveur.joueur(choix).getMain().getscore();
				//System.out.println(score);
				System.out.println("Hit(1) ou Stand(2)?");
				sc = new Scanner(System.in);	
				int hit_stand = sc.nextInt();
				Joueur joueur = serveur.joueur(choix);
				
				if (hit_stand == 1) {
					/*System.out.println(serveur.hit_or_stand(serveur.joueur(choix)));
					//Méthode afficher main
					System.out.println(serveur.afficherMain(serveur.joueur(choix)));*/
	    			
	    			//System.out.println(joueur.getMain().piocher(serveur.deck()));
	    			//serveur.joueur(choix).getMain().ajouterCarte(carte);
					//serveur.deck().distribuer(joueur.getMain());
					System.out.println(serveur.distribuerCarte(joueur));
	    			//main = joueur.getMain().getHand();
					
					//System.out.println(joueur.getMain().toString());
					//System.out.println(main.toString());
				}
				else {
					hit = false;
					//serveur.setHitStand(serveur.joueur(choix));
					System.out.println("Vous vous couchez");
					//Méthode afficher main
					//System.out.println(serveur.afficherMain(serveur.joueur(choix)));*/
				}
				
				//Avant ca récupérer le jeu et arrêter si 21

				try {
					// Récupération d'un proxy sur l'objet
						//Serveur serveur = (Serveur) Naming.lookup("//localhost/Serveur");
						//Joueur j = serveur.joueur(choix);
					//System.out.println(serveur.getscore(serveur.joueur(choix)));


						// Appel d'une méthode sur l'objet distant
						} catch (Exception e) {
						e.printStackTrace();
						}
				
			}
			if (serveur.joueur(choix).getMain().getscore() > 21) {
				hit = false;
				System.out.println("Score > 21");
			}
			

			// Appel d'une méthode sur l'objet distant
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		
		//Avant ca récupérer le jeu et arrêter si 21

		/*try {
			// Récupération d'un proxy sur l'objet
				Serveur serveur = (Serveur) Naming.lookup("//localhost/Serveur");
				//Joueur j = serveur.joueur(choix);
				System.out.println(serveur.joueur(choix).getNom());
				while (serveur.isHit(serveur.joueur(choix))) {
					System.out.println("Hit : entrer 1 \nStand : entrer 2 \n");
					int hit_stand = sc.nextInt();
					
					if (hit_stand == 1) {
						System.out.println(serveur.distribuerCarte(serveur.joueur(choix)));
						serveur.distribuerCarte(serveur.joueur(choix));
						//Méthode afficher main
					}
					else {
						serveur.setHit(serveur.joueur(choix), false);
						System.out.println("Vous vous couchez");
						//Méthode afficher main
					}
				}

				// Appel d'une méthode sur l'objet distant
				} catch (Exception e) {
				e.printStackTrace();
				}*/

	}




	
}

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ServerImpl extends UnicastRemoteObject implements Serveur, Unreferenced {

	private ArrayList<Joueur> listeJoueur;
	private Croupier croupier;
	private Paquet deck;
	

	public ServerImpl() throws RemoteException {
		super();
		this.listeJoueur = new ArrayList<Joueur>();
		this.croupier = new Croupier("Croupier");
		deck = new Paquet();
		deck.melanger();
		deck.melanger();
	}

	@Override
	public void unreferenced() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized String connexionJoueur(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		int i = 1;
		String nomJoueurs= "Connexion à la partie réussie, en attente de joueurs supplémentaires \n" ;
		
		System.out.println(nom+" vient de se connecter au serveur");
		listeJoueur.add(new Joueur(nom));

		return nomJoueurs;
	}
	
	@Override
	public synchronized String attenteJoueur() throws RemoteException {
		// TODO Auto-generated method stub
		
		String messageJoueurs = "La partie va débuter";
		 while(listeJoueur.size() < 2) {
	            try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		 
		notify();
		
		return messageJoueurs;
	}
	
	
	public synchronized String demarrerPartie() throws RemoteException{
		String messageClient = null;
		int i=1;
		

		
		messageClient = "La partie débute...\n";
		System.out.println("Liste des joueurs :");
		for (Joueur joueur : listeJoueur ) {
			System.out.println("Joueur "+i+" : "+joueur.getNom());
			messageClient = messageClient+"Joueur "+i+" : "+joueur.getNom()+"\n";
			i++;
		}
		
		
		return messageClient;
	}
	
	public synchronized String distribution() throws RemoteException {
		String affichageClient = "";
		System.out.println("---------------Distribution des cartes------------------------------------\n");
		
		for (Joueur joueur : listeJoueur ) {
			Carte carte = deck.distribuer(joueur.getMain());
			//Carte carte = joueur.getMain().piocher(deck);
			System.out.println("Le joueur "+joueur.getNom()+" obtient un "+carte.toString());
			affichageClient = affichageClient +"Le joueur "+joueur.getNom()+" obtient un "+carte.toString()+"\n";
		}
		
		Carte carte = deck.distribuer(croupier.getMain());
		//Carte carte = croupier.getMain().piocher(deck);
		System.out.println("Le croupier obtient un "+carte.toString());
		affichageClient = affichageClient +"Le croupier obtient un "+carte.toString()+"\n";
		
		for (Joueur joueur : listeJoueur ) {
			Carte carte2 = deck.distribuer(joueur.getMain());
			//Carte carte2 = joueur.getMain().piocher(deck);
			System.out.println("Le joueur "+joueur.getNom()+" obtient un "+carte2.toString());
			affichageClient = affichageClient +"Le joueur "+joueur.getNom()+" obtient un "+carte.toString()+"\n";
		}
		
		deck.distribuer(croupier.getMain());
		System.out.println("Le croupier pioche une deuxième carte ");
		affichageClient = affichageClient+"Le croupier pioche une deuxième carte \n";

		
		for (Joueur joueur : listeJoueur ) {
			System.out.println(joueur.getNom()+" || "+joueur.getMain().toString());
			affichageClient = affichageClient +joueur.getNom()+" || "+joueur.getMain().toString()+"\n";
		}
		
		System.out.println("---------------Fin distribution------------------------------------\n");

		return affichageClient;
	}
	
	@Override
	public synchronized String affichageMains() throws RemoteException {
		//System.out.println("Début affichage mains");
		
		String affichageClient = "";
		int t = listeJoueur.size()-1;
		Joueur dDistrib = listeJoueur.get(t);
		
		 while(dDistrib.getMain().getHand().size() < 2) {
	            try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		 
		notify();
		
		for (Joueur joueur : listeJoueur ) {
			affichageClient = affichageClient +"Le joueur "+joueur.getNom()+" obtient  "+joueur.getMain().getHand().get(0)+"\n";
		}
		
		
		affichageClient = affichageClient +"Le croupier obtient un "+croupier.getMain().getcarte(0).toString()+"\n";
		
		for (Joueur joueur : listeJoueur ) {
			affichageClient = affichageClient +"Le joueur "+joueur.getNom()+" obtient un "+joueur.getMain().getHand().get(1)+"\n";
		}
		
		affichageClient = affichageClient+"Le croupier pioche une deuxième carte \n";

		
		for (Joueur joueur : listeJoueur ) {
			affichageClient = affichageClient +joueur.getNom()+" || "+joueur.getMain().toString()+"\n";
		}
		
	
		return affichageClient;
	}
	
	public Joueur joueur(String mot) throws RemoteException {
		// TODO Auto-generated method stub
		int i=0;
		Joueur j = null;
		
		for (Joueur joueur : listeJoueur ) {
			//System.out.println(listeJoueur.get(i)+" nom: "+listeJoueur.get(i).getNom());
			if(listeJoueur.get(i).getNom().equals(mot)) {
				j = listeJoueur.get(i);  
			}
				i++;
		}
		//System.out.println(j.getNom());
		//System.out.println(mot);
		return j;
	}
	
	
	public synchronized boolean isHit(Joueur j) throws RemoteException {
		
		boolean b = j.isHit();
		
		return b;
	}
	
	@Override
	public synchronized String distribuerCarte(Joueur j) throws RemoteException {
		// TODO Auto-generated method stub
		String affichageClient = "";
		Carte carte = deck.distribuer(j.getMain());
		//Carte carte = j.getMain().piocher(deck);
		System.out.println("Distrib appelé");
		System.out.println("Main "+j.getMain().toString());
		return "Vous obtenez un "+carte.toString()+"\n"+"Votre main: "+j.getMain().toString();
	}
	

	
	
/*	public synchronized void setHitStand(Joueur j) throws RemoteException {
		
		j.setStand();
		//System.out.println(j.isHit());
	}*/
	
	
	public synchronized String afficherMain(Joueur j) throws RemoteException {
		// TODO Auto-generated method stub
		String mainJoueur = "";
		mainJoueur = j.getMain().toString();
		
		System.out.println(j.getMain().toString());
		return mainJoueur;
	}
	
	public synchronized int getscore(Joueur j) {
		
		return j.getMain().getscore();
	}
	 
	public synchronized void hitt(Joueur j) {
		int i = j.getNbHit();
		i++;
		j.setNbHit(i);
		System.out.println(i);
	}
	
	@Override
	public synchronized Paquet deck() throws RemoteException {
		// TODO Auto-generated method stub
		return this.deck;
	}
	
	 public static void main(String args[]) throws Exception {
		// Démarre le rmiregistry
		LocateRegistry.createRegistry(1099);
		// Crée et installe un gestionnaire de sécurité
		 // inutile si on ne télécharge pas les classes des stubs et parametres
		// System.setSecurityManager(new RMISecurityManager());
		ServerImpl obj = new ServerImpl();
		Naming.rebind("Serveur", obj);
		System.out.println("Partie créee, en attente de joueurs");
		
		obj.attenteJoueur();
		obj.demarrerPartie();
		obj.distribution();
		//obj.distribuerCarte(obj.listeJoueur.get(0));
		//obj.distribuerCarte(obj.listeJoueur.get(0));
		//obj.afficherMain(obj.listeJoueur.get(0));
		//System.out.println(obj.listeJoueur.get(0).getMain().toString());
	 }

	
}

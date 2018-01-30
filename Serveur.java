import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Serveur extends Remote{

	Paquet deck() throws java.rmi.RemoteException;
	String connexionJoueur(String nom) throws java.rmi.RemoteException;
	boolean isHit (Joueur j) throws java.rmi.RemoteException;
	Joueur joueur(String nom) throws java.rmi.RemoteException;
	String distribuerCarte(Joueur j) throws java.rmi.RemoteException;
	String demarrerPartie() throws java.rmi.RemoteException;
	String attenteJoueur() throws RemoteException;
	String affichageMains() throws RemoteException;
	//void setHitStand(Joueur j) throws RemoteException;
	String afficherMain(Joueur j) throws RemoteException;
	//String hit_or_stand(Joueur j) throws RemoteException;
	//void hitt(Joueur j) throws RemoteException;
	//int getscore(Joueur j) throws RemoteException;
	
}

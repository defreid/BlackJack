import java.rmi.Remote;

public interface Client extends Remote{

	void notification(String nom) throws java.rmi.RemoteException;
}

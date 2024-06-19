import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface CityRemote extends Remote {
    void addInhabitant(String name, String dateOfBirth, String maritalStatus) throws RemoteException;
    InhabitantRemote findInhabitant(String name) throws RemoteException;
    Set<InhabitantRemote> getInhabitants() throws RemoteException;
    String getName() throws RemoteException;
}
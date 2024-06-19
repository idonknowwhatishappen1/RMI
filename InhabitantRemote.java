import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InhabitantRemote extends Remote {
    String getName() throws RemoteException;
    String getDateOfBirth() throws RemoteException;
    String getMaritalStatus() throws RemoteException;
    //void setMaritalStatus(String maritalStatus) throws RemoteException;
}
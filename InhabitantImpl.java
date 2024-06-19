import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InhabitantImpl extends UnicastRemoteObject implements InhabitantRemote {
    private final String name;
    private final String dateOfBirth;
    private final String maritalStatus;

    public InhabitantImpl(String name, String dateOfBirth, String maritalStatus) throws RemoteException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDateOfBirth() throws RemoteException {
        return dateOfBirth;
    }

    @Override
    public String getMaritalStatus() throws RemoteException {
        return maritalStatus;
    }

//    @Override
//    public void setMaritalStatus(String maritalStatus) throws RemoteException {
//        this.maritalStatus = maritalStatus;
//    }
}
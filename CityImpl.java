import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class CityImpl extends UnicastRemoteObject implements CityRemote {
    private final String name;
    private final Set<InhabitantRemote> inhabitants;

    public CityImpl(String name) throws RemoteException {
        this.name = name;
        this.inhabitants = new HashSet<>();
    }

    @Override
    public void addInhabitant(String name, String dateOfBirth, String maritalStatus) throws RemoteException {
        InhabitantRemote inhabitant = new InhabitantImpl(name, dateOfBirth, maritalStatus);
        inhabitants.add(inhabitant);
    }

    @Override
    public InhabitantRemote findInhabitant(String name) throws RemoteException {
        for (InhabitantRemote inhabitant : inhabitants) {
            if (inhabitant.getName().equals(name)) {
                return inhabitant;
            }
        }
        return null;
    }

    @Override
    public Set<InhabitantRemote> getInhabitants() throws RemoteException {
        return inhabitants;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
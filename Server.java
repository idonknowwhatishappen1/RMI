import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            // Create instances of CityImpl
            CityRemote frankfurtServer = new CityImpl("Frankfurt");
            CityRemote berlinServer = new CityImpl("Berlin");

            // Add inhabitants to the city instances
            frankfurtServer.addInhabitant("Alice", "01/01/1990", "Single");
            frankfurtServer.addInhabitant("Bob", "02/02/1990", "Married");
            berlinServer.addInhabitant("Charlie", "03/03/1990", "Single");
            berlinServer.addInhabitant("David", "04/04/1990", "Married");

            // Bind the city instances to the RMI registry
            Naming.rebind("rmi://localhost/FrankfurtServer", frankfurtServer);
            Naming.rebind("rmi://localhost/BerlinServer", berlinServer);

            System.out.println("City servers are ready.");
        } catch (RemoteException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

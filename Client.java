import java.rmi.Naming;
import java.util.Scanner;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        try {
            // Lookup city servers
            CityRemote frankfurtServer = (CityRemote) Naming.lookup("rmi://localhost/FrankfurtServer");
            CityRemote berlinServer = (CityRemote) Naming.lookup("rmi://localhost/BerlinServer");

            Scanner scanner = new Scanner(System.in);

            // Main loop
            while (true) {
                System.out.println("Choose a city: Frankfurt or Berlin (or type 'exit' to quit)");
                String cityChoice = scanner.nextLine();

                if (cityChoice.equalsIgnoreCase("exit")) {
                    break;
                }

                // Select the city server based on user input
                CityRemote selectedCityServer;
                if (cityChoice.equalsIgnoreCase("Frankfurt")) {
                    selectedCityServer = frankfurtServer;
                } else if (cityChoice.equalsIgnoreCase("Berlin")) {
                    selectedCityServer = berlinServer;
                } else {
                    System.out.println("Invalid city choice. Please try again.");
                    continue;
                }

                System.out.println("Choose the method you want to use:");
                System.out.println("1. Search Inhabitant");
                System.out.println("2. Add Inhabitant");
                System.out.println("3. Get All Dates of Birth");
                System.out.println("4. Get Marital Status");
                System.out.println("5. Change Marital Status");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Enter name: ");
                        String name = scanner.nextLine();
                        InhabitantRemote inhabitant = selectedCityServer.findInhabitant(name);
                        if (inhabitant != null) {
                            System.out.println(inhabitant.getName() + ": " + inhabitant.getDateOfBirth() + " ; "  + inhabitant.getMaritalStatus());
                        } else {
                            System.out.println("Inhabitant not found.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter date of birth (DD/MM/YYYY): ");
                        String dob = scanner.nextLine();
                        System.out.println("Enter marital status: ");
                        String ms = scanner.nextLine();

                        // Add inhabitant to the selected city server
                        selectedCityServer.addInhabitant(name, dob, ms);
                        System.out.println("Inhabitant added successfully.");
                        break;
                    case 3:
                        Set<InhabitantRemote> inhabitants = selectedCityServer.getInhabitants();
                        for (InhabitantRemote inhabitantItem : inhabitants) {
                            System.out.println(inhabitantItem.getName() + ": " + inhabitantItem.getDateOfBirth());
                        }
                        break;
                    case 4:
                        System.out.println("Enter name: ");
                        name = scanner.nextLine();
                        inhabitant = selectedCityServer.findInhabitant(name);
                        if (inhabitant != null) {
                            System.out.println(inhabitant.getName() + ": " + inhabitant.getMaritalStatus());
                        } else {
                            System.out.println("Inhabitant not found.");
                        }
                        break;
                    case 5:
                        System.out.println("Enter name: ");
                        name = scanner.nextLine();
                        System.out.println("Enter new marital status: ");
                        ms = scanner.nextLine();
                        selectedCityServer.setMaritalStatus(name, ms);
                        System.out.println("Marital status updated successfully.");
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Zoo zoo = new Zoo("My Zoo");

        zoo.addAnimal(new Mammal("Leo", "Lion", 5));
        zoo.addAnimal(new Bird("Polly", "Parrot", 2));
        zoo.addKeeper(new ZooKeeper("John", 1));

        while (true) {
            System.out.println("\n1. Show animals");
            System.out.println("2. Find animal");
            System.out.println("3. Filter by species");
            System.out.println("4. Sort by age");
            System.out.println("5. Compare animals");
            System.out.println("6. Animal sounds");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                zoo.showAnimals();
            } else if (choice == 2) {
                System.out.print("Name: ");
                String name = scan.nextLine();
                Animal a = zoo.findAnimal(name);
                System.out.println(a != null ? a : "Not found");
            } else if (choice == 3) {
                System.out.print("Species: ");
                String species = scan.nextLine();
                ArrayList<Animal> filtered = zoo.filterBySpecies(species);
                for (Animal a : filtered) {
                    System.out.println(a);
                }
            } else if (choice == 4) {
                ArrayList<Animal> sorted = zoo.sortByAge();
                for (Animal a : sorted) {
                    System.out.println(a);
                }
            } else if (choice == 5) {
                System.out.print("First animal: ");
                String name1 = scan.nextLine();
                System.out.print("Second animal: ");
                String name2 = scan.nextLine();

                Animal a1 = zoo.findAnimal(name1);
                Animal a2 = zoo.findAnimal(name2);

                if (a1 != null && a2 != null) {
                    System.out.println("Equal? " + a1.equals(a2));
                }
            } else if (choice == 6) {
                for (Animal a : zoo.filterBySpecies("Lion")) {
                    System.out.println(a.getName() + ": " + a.getSound());
                }
                for (Animal a : zoo.filterBySpecies("Parrot")) {
                    System.out.println(a.getName() + ": " + a.getSound());
                }
            } else if (choice == 7) {
                System.out.println("Bye!");
                break;
            }
        }

        scan.close();
    }
}
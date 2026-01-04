import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo zoo = new Zoo("My Zoo");


        zoo.addAnimal(new Mammal("Leo", "Lion", 5, "Good", true));
        zoo.addAnimal(new Mammal("Manny", "Elephant", 10, "Excellent", false));
        zoo.addAnimal(new Bird("Polly", "Parrot", 2, "Good", true));


        zoo.addAnimal(new Mammal("Simba", "Lion", 3, "Fair", true));
        zoo.addKeeper(new ZooKeeper("John", 5, 1));
        zoo.addKeeper(new ZooKeeper("Anna", 3, 2));

        while (true) {
            System.out.println("\n===== ZOO MENU =====");
            System.out.println("1. Show all animals");
            System.out.println("2. Show all keepers");
            System.out.println("3. Find animal by name");
            System.out.println("4. Filter animals by species");
            System.out.println("5. Sort animals by age");
            System.out.println("6. Compare two animals");
            System.out.println("7. Animal sounds");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка

            if (choice == 1) {
                zoo.showAllAnimals();
            }
            else if (choice == 2) {
                zoo.showAllKeepers();
            }
            else if (choice == 3) {
                System.out.print("Enter animal name: ");
                String name = scanner.nextLine();
                Animal found = zoo.findAnimalByName(name);
                if (found != null) {
                    System.out.println("Found: " + found);
                } else {
                    System.out.println("Not found.");
                }
            }
            else if (choice == 4) {
                System.out.print("Enter species: ");
                String species = scanner.nextLine();
                ArrayList<Animal> filtered = zoo.filterBySpecies(species);
                System.out.println("Animals of species '" + species + "':");
                for (Animal a : filtered) {
                    System.out.println(a);
                }
            }
            else if (choice == 5) {
                ArrayList<Animal> sorted = zoo.sortAnimalsByAge();
                System.out.println("Animals sorted by age:");
                for (Animal a : sorted) {
                    System.out.println(a);
                }
            }
            else if (choice == 6) {
                System.out.print("Enter first animal name: ");
                String name1 = scanner.nextLine();
                System.out.print("Enter second animal name: ");
                String name2 = scanner.nextLine();

                Animal a1 = zoo.findAnimalByName(name1);
                Animal a2 = zoo.findAnimalByName(name2);

                if (a1 != null && a2 != null) {
                    System.out.println("Animal 1: " + a1);
                    System.out.println("Animal 2: " + a2);
                    System.out.println("Are equal? " + a1.equals(a2));
                } else {
                    System.out.println("One or both not found.");
                }
            }
            else if (choice == 7) {
                System.out.println("Animal sounds:");
                for (Animal a : zoo.filterBySpecies("Lion")) {
                    a.makeSound();
                }
                for (Animal a : zoo.filterBySpecies("Parrot")) {
                    a.makeSound();
                }
            }
            else if (choice == 8) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Wrong choice!");
            }
        }

        scanner.close();
    }
}
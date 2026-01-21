import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Zoo zoo = new Zoo();

        zoo.addAnimal(new Mammal("Leo", "Lion", 5));
        zoo.addAnimal(new Bird("Polly", "Parrot", 2));
        zoo.addAnimal(new Mammal("Simba", "Lion", 3));

        System.out.println("=== ZOO SYSTEM ===");

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Show all");
            System.out.println("2. Find by name");
            System.out.println("3. Show lions");
            System.out.println("4. Sort by age");
            System.out.println("5. Compare animals");
            System.out.println("6. Animal sounds");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("All animals:");
                    zoo.showAll();
                    break;

                case 2:
                    System.out.print("Animal name: ");
                    String name = scan.nextLine();
                    Animal found = zoo.findByName(name);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case 3:
                    System.out.println("All lions:");
                    for (Animal a : zoo.getBySpecies("Lion")) {
                        System.out.println(a);
                    }
                    break;

                case 4:
                    System.out.println("Sorted by age:");
                    for (Animal a : zoo.sortByAge()) {
                        System.out.println(a);
                    }
                    break;

                case 5:
                    System.out.print("First animal name: ");
                    String n1 = scan.nextLine();
                    System.out.print("Second animal name: ");
                    String n2 = scan.nextLine();

                    Animal a1 = zoo.findByName(n1);
                    Animal a2 = zoo.findByName(n2);

                    if (a1 != null && a2 != null) {
                        System.out.println("Animals equal? " + a1.equals(a2));
                    }
                    break;

                case 6:
                    System.out.println("Animal sounds:");
                    for (Animal a : zoo.getBySpecies("Lion")) {
                        System.out.println(a.getName() + " says: " + a.getSound());
                    }
                    for (Animal a : zoo.getBySpecies("Parrot")) {
                        System.out.println(a.getName() + " says: " + a.getSound());
                    }
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        scan.close();
    }
}
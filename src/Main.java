import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DB db = new DB();

        try {
            // Создаем таблицы при запуске
            db.createTables();

            // Добавляем начальные данные
            db.insertAnimal(new Mammal("Leo", "Lion", 5));
            db.insertAnimal(new Bird("Polly", "Parrot", 2));
            db.insertAnimal(new Mammal("Simba", "Lion", 3));
            db.insertZooKeeper(new ZooKeeper("John Smith", 110));

            System.out.println("=== ZOO DATABASE SYSTEM ===");

            boolean running = true;
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Show all animals");
                System.out.println("2. Find animal by name");
                System.out.println("3. Show animals by species");
                System.out.println("4. Add new animal");
                System.out.println("5. Update animal age");
                System.out.println("6. Delete animal");
                System.out.println("7. Add zookeeper");
                System.out.println("8. Show all zookeepers");
                System.out.println("9. Animal sounds");
                System.out.println("10. Exit");
                System.out.print("Choice: ");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("All animals:");
                        for (Animal a : db.getAllAnimals()) {
                            System.out.println(a);
                        }
                        break;

                    case 2:
                        System.out.print("Animal name: ");
                        String name = scan.nextLine();
                        Animal found = db.findAnimal(name);
                        if (found != null) {
                            System.out.println("Found: " + found);
                            System.out.println("Sound: " + found.getSound());
                        } else {
                            System.out.println("Not found");
                        }
                        break;

                    case 3:
                        System.out.print("Species: ");
                        String species = scan.nextLine();
                        System.out.println("Animals of species '" + species + "':");
                        // Нужно добавить метод в DB для поиска по виду
                        for (Animal a : db.getAllAnimals()) {
                            if (a.getSpecies().equalsIgnoreCase(species)) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Animal type (1-Mammal, 2-Bird): ");
                        int typeChoice = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Name: ");
                        String newName = scan.nextLine();
                        System.out.print("Species: ");
                        String newSpecies = scan.nextLine();
                        System.out.print("Age: ");
                        int newAge = scan.nextInt();
                        scan.nextLine();

                        Animal newAnimal;
                        if (typeChoice == 1) {
                            newAnimal = new Mammal(newName, newSpecies, newAge);
                        } else {
                            newAnimal = new Bird(newName, newSpecies, newAge);
                        }
                        db.insertAnimal(newAnimal);
                        System.out.println("Animal added successfully!");
                        break;

                    case 5:
                        System.out.print("Animal name: ");
                        String updateName = scan.nextLine();
                        System.out.print("New age: ");
                        int updateAge = scan.nextInt();
                        scan.nextLine();
                        db.updateAnimalAge(updateName, updateAge);
                        break;

                    case 6:
                        System.out.print("Animal name to delete: ");
                        String deleteName = scan.nextLine();
                        db.deleteAnimal(deleteName);
                        break;

                    case 7:
                        System.out.print("Zookeeper name: ");
                        String zkName = scan.nextLine();
                        System.out.print("Employee ID: ");
                        int zkId = scan.nextInt();
                        scan.nextLine();
                        db.insertZooKeeper(new ZooKeeper(zkName, zkId));
                        System.out.println("Zookeeper added!");
                        break;

                    case 8:
                        System.out.println("All zookeepers:");
                        for (ZooKeeper zk : db.getAllZooKeepers()) {
                            System.out.println(zk);
                        }
                        break;

                    case 9:
                        System.out.println("Animal sounds:");
                        for (Animal a : db.getAllAnimals()) {
                            System.out.println(a.getName() + " (" + a.getSpecies() + ") says: " + a.getSound());
                        }
                        break;

                    case 10:
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}
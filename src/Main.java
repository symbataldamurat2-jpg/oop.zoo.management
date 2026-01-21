import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DB db = new DB();

        try {
            // Создаем таблицы при запуске
            db.createTables();

            // Добавляем начальные данные (с проверкой, чтобы избежать дублирования)
            if (!db.animalExists("Leo")) {
                db.insertAnimal(new Mammal("Leo", "Lion", 5));
            }
            if (!db.animalExists("Polly")) {
                db.insertAnimal(new Bird("Polly", "Parrot", 2));
            }
            if (!db.animalExists("Simba")) {
                db.insertAnimal(new Mammal("Simba", "Lion", 3));
            }

            // Проверяем, существует ли уже смотритель с ID 110
            if (!db.zookeeperIdExists(110)) {
                db.insertZooKeeper(new ZooKeeper("John Smith", 110));
            }

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
                        // ИСПРАВЛЕНО: используем метод getAnimalsBySpecies
                        for (Animal a : db.getAnimalsBySpecies(species)) {
                            System.out.println(a);
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

                        // Проверка возраста
                        if (newAge < 0) {
                            System.out.println("Error: Age cannot be negative!");
                            break;
                        }

                        // Проверка, существует ли уже животное с таким именем
                        if (db.animalExists(newName)) {
                            System.out.println("Error: Animal with name '" + newName + "' already exists!");
                            break;
                        }

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

                        // Проверка существования
                        if (!db.animalExists(updateName)) {
                            System.out.println("Error: Animal not found!");
                            break;
                        }

                        System.out.print("New age: ");
                        int updateAge = scan.nextInt();
                        scan.nextLine();

                        if (updateAge < 0) {
                            System.out.println("Error: Age cannot be negative!");
                            break;
                        }

                        db.updateAnimalAge(updateName, updateAge);
                        System.out.println("Age updated successfully!");
                        break;

                    case 6:
                        System.out.print("Animal name to delete: ");
                        String deleteName = scan.nextLine();

                        // Проверка существования
                        if (!db.animalExists(deleteName)) {
                            System.out.println("Error: Animal not found!");
                            break;
                        }

                        System.out.print("Are you sure you want to delete '" + deleteName + "'? (y/n): ");
                        String confirm = scan.nextLine();
                        if (confirm.equalsIgnoreCase("y")) {
                            db.deleteAnimal(deleteName);
                            System.out.println("Animal deleted successfully!");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                        break;

                    case 7:
                        System.out.print("Zookeeper name: ");
                        String zkName = scan.nextLine();
                        System.out.print("Employee ID: ");
                        int zkId = scan.nextInt();
                        scan.nextLine();

                        // Проверка ID
                        if (zkId <= 0) {
                            System.out.println("Error: Employee ID must be positive!");
                            break;
                        }

                        // Проверка, существует ли уже такой ID
                        if (db.zookeeperIdExists(zkId)) {
                            System.out.println("Error: Employee ID " + zkId + " already exists!");
                            System.out.println("Please use a different ID.");
                            break;
                        }

                        db.insertZooKeeper(new ZooKeeper(zkName, zkId));
                        System.out.println("Zookeeper added successfully!");
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
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}
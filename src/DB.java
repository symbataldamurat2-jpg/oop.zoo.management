import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JAVA";
    private static final String USER = "postgres";
    private static final String PASS = "010203";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public void createTables() throws SQLException {
        String createAnimalTable = """
            CREATE TABLE IF NOT EXISTS Animal (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                species VARCHAR(100) NOT NULL,
                age INT NOT NULL,
                animal_type VARCHAR(50) NOT NULL
            )
            """;

        String createZooKeeperTable = """
            CREATE TABLE IF NOT EXISTS ZooKeeper (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                employee_id INT UNIQUE NOT NULL
            )
            """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createAnimalTable);
            stmt.execute(createZooKeeperTable);
            System.out.println("Tables created successfully!");
        }
    }


    public void insertAnimal(Animal animal) throws SQLException {
        String type = (animal instanceof Mammal) ? "Mammal" : "Bird";
        String sql = "INSERT INTO Animal (name, species, age, animal_type) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getSpecies());
            pstmt.setInt(3, animal.getAge());
            pstmt.setString(4, type);
            pstmt.executeUpdate();
            System.out.println("Animal inserted: " + animal.getName());
        }
    }


    public ArrayList<Animal> getAllAnimals() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animal";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                String type = rs.getString("animal_type");

                Animal animal;
                if (type.equals("Mammal")) {
                    animal = new Mammal(name, species, age);
                } else {
                    animal = new Bird(name, species, age);
                }
                animals.add(animal);
            }
        }
        return animals;
    }


    public Animal findAnimal(String name) throws SQLException {
        String sql = "SELECT * FROM Animal WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String animalName = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                String type = rs.getString("animal_type");

                if (type.equals("Mammal")) {
                    return new Mammal(animalName, species, age);
                } else {
                    return new Bird(animalName, species, age);
                }
            }
        }
        return null;
    }


    public ArrayList<Animal> getAnimalsBySpecies(String species) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animal WHERE LOWER(species) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, species);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String animalSpecies = rs.getString("species");
                int age = rs.getInt("age");
                String type = rs.getString("animal_type");

                Animal animal;
                if (type.equals("Mammal")) {
                    animal = new Mammal(name, animalSpecies, age);
                } else {
                    animal = new Bird(name, animalSpecies, age);
                }
                animals.add(animal);
            }
        }
        return animals;
    }


    public void updateAnimalAge(String name, int newAge) throws SQLException {
        String sql = "UPDATE Animal SET age = ? WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAge);
            pstmt.setString(2, name);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) updated");
        }
    }


    public void deleteAnimal(String name) throws SQLException {
        String sql = "DELETE FROM Animal WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) deleted");
        }
    }


    public boolean animalExists(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Animal WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


    public void insertZooKeeper(ZooKeeper keeper) throws SQLException {
        String sql = "INSERT INTO ZooKeeper (name, employee_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, keeper.getName());
            pstmt.setInt(2, keeper.getId());
            pstmt.executeUpdate();
            System.out.println("ZooKeeper inserted: " + keeper.getName());
        }
    }


    public ArrayList<ZooKeeper> getAllZooKeepers() throws SQLException {
        ArrayList<ZooKeeper> keepers = new ArrayList<>();
        String sql = "SELECT * FROM ZooKeeper";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int employeeId = rs.getInt("employee_id");
                keepers.add(new ZooKeeper(name, employeeId));
            }
        }
        return keepers;
    }


    public boolean zookeeperIdExists(int employeeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ZooKeeper WHERE employee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        DB db = new DB();

        try {
            // Создаем таблицы
            db.createTables();

            // Вставляем животных
            db.insertAnimal(new Mammal("Leo", "Lion", 5));
            db.insertAnimal(new Bird("Polly", "Parrot", 2));
            db.insertAnimal(new Mammal("Simba", "Lion", 3));

            // Вставляем смотрителя
            db.insertZooKeeper(new ZooKeeper("John Doe", 101));

            // Демонстрация операций
            System.out.println("\n=== Testing CRUD Operations ===");

            // READ
            System.out.println("\nAll animals:");
            for (Animal a : db.getAllAnimals()) {
                System.out.println(a + " - Sound: " + a.getSound());
            }

            // FIND
            System.out.println("\nFinding 'Leo':");
            Animal found = db.findAnimal("Leo");
            if (found != null) {
                System.out.println("Found: " + found);
            }

            // Поиск по виду
            System.out.println("\nAll lions:");
            for (Animal a : db.getAnimalsBySpecies("Lion")) {
                System.out.println(a);
            }

            // UPDATE
            System.out.println("\nUpdating Leo's age to 6:");
            db.updateAnimalAge("Leo", 6);

            // DELETE
            System.out.println("\nDeleting 'Polly':");
            db.deleteAnimal("Polly");

            // Проверяем результат
            System.out.println("\nAnimals after operations:");
            for (Animal a : db.getAllAnimals()) {
                System.out.println(a);
            }

            // Смотрители
            System.out.println("\nAll zookeepers:");
            for (ZooKeeper zk : db.getAllZooKeepers()) {
                System.out.println(zk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
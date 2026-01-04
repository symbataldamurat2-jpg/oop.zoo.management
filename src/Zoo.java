import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Zoo {
    private String name;
    private ArrayList<Animal> animals;
    private ArrayList<ZooKeeper> keepers;

    public Zoo(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
        this.keepers = new ArrayList<>();
    }


    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addKeeper(ZooKeeper keeper) {
        keepers.add(keeper);
    }


    public ArrayList<Animal> filterBySpecies(String species) {
        ArrayList<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }
        return result;
    }


    public Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }


    public ArrayList<Animal> sortAnimalsByAge() {
        ArrayList<Animal> sorted = new ArrayList<>(animals);
        Collections.sort(sorted, new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                return Integer.compare(a1.getAge(), a2.getAge());
            }
        });
        return sorted;
    }


    public void showAllAnimals() {
        System.out.println("=== Animals in " + name + " ===");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }


    public void showAllKeepers() {
        System.out.println("=== Keepers in " + name + " ===");
        for (ZooKeeper keeper : keepers) {
            System.out.println(keeper);
        }
    }

    public String getName() {
        return name;
    }
}
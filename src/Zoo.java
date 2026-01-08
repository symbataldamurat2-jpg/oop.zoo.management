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
        for (Animal a : animals) {
            if (a.getSpecies().equals(species)) {
                result.add(a);
            }
        }
        return result;
    }

    public Animal findAnimal(String name) {
        for (Animal a : animals) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Animal> sortByAge() {
        ArrayList<Animal> sorted = new ArrayList<>(animals);
        Collections.sort(sorted, Comparator.comparingInt(Animal::getAge));
        return sorted;
    }

    public void showAnimals() {
        for (Animal a : animals) {
            System.out.println(a);
        }
    }

    public void showKeepers() {
        for (ZooKeeper k : keepers) {
            System.out.println(k);
        }
    }
}
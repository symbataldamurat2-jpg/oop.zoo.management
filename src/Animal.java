import java.util.Objects;

public abstract class Animal {
    private String name;
    private String species;
    private int age;
    private String health;

    public Animal(String name, String species, int age, String health) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.health = health;
    }


    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getHealth() { return health; }


    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setAge(int age) { this.age = age; }
    public void setHealth(String health) { this.health = health; }


    public abstract void makeSound();


    @Override
    public String toString() {
        return name + " (" + species + "), age: " + age + ", health: " + health;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return age == animal.age &&
                name.equals(animal.name) &&
                species.equals(animal.species);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, species, age);
    }
}
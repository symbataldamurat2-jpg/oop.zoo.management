import java.util.Objects;

public abstract class Animal{
    private String name;
    private String species;
    private int age;

    public Animal(String name, String species, int age){
        this.name=name;
        this.species=species;
        this.age=age;
    }

    public String getName(){
        return name;
    }
    public String getSpecies(){
        return species;
    }
    public int getAge(){
        return age;
    }
    public abstract String getSound();

    @Override
    public String toString(){
        return name+"-"+species+","+age+"years";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return age == animal.age && name.equals(animal.name);
    }
    @Override
    public int hashCode(){
    return Objects.hash(name,age);
    }
}

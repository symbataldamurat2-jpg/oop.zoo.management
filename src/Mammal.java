public class Mammal extends Animal {
    private boolean hasFur;

    public Mammal(String name, String species, int age, String health, boolean hasFur) {
        super(name, species, age, health);
        this.hasFur = hasFur;
    }

    public boolean getHasFur() {
        return hasFur;
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Roar!");
    }

    @Override
    public String toString() {
        return super.toString() + ", has fur: " + hasFur;
    }
}
public class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, String species, int age, String health, boolean canFly) {
        super(name, species, age, health);
        this.canFly = canFly;
    }

    public boolean getCanFly() {
        return canFly;
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Tweet!");
    }

    @Override
    public String toString() {
        return super.toString() + ", can fly: " + canFly;
    }
}
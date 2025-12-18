public class Main {
    public static void main (String[] args){
        Animal animal1=new Animal("Lion", "predator",5);
        Animal animal2=new Animal("Lion", "predator",5);

        ZooKeeper keeper = new ZooKeeper("Sam", 10);
        Zoo zoo= new Zoo("Сentral zoo");

        zoo.displayZooInfo();
        animal1.displayAnimalInfo();
        keeper.displayZooKeeperInfo();

        if (animal1.getName().equals(animal2.getName())){
            System.out.println("animals have the same name");
        }else {
            System.out.println("animals are different");
        }
    }
}
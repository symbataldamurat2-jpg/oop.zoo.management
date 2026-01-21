public class Mammal extends Animal{
    public Mammal(String name, String species, int age){
        super(name, species, age);
    }
    @Override
    public String getSound(){
        return "Roar!";
    }
}
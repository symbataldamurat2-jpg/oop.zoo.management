import java.util.ArrayList;
public class Zoo{
    private ArrayList<Animal> animals;
    public Zoo(){
        animals=new ArrayList<>();
    }
    public void addAnimal(Animal a){
        animals.add(a);
    }
    public ArrayList<Animal> getBySpecies(String species){
        ArrayList<Animal> result= new ArrayList<>();
        for (Animal a:animals){
            if(a.getSpecies().equals(species)){
                result.add(a);
            }
        }
        return result;
    }
    public Animal findByName(String name){
        for(Animal a:animals){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }
    public ArrayList<Animal> sortByAge(){
        ArrayList<Animal> sorted=new ArrayList<>(animals);
        for(int i=0;i< sorted.size()-1;i++){
            for(int j=i+1;j<sorted.size();j++){
                if (sorted.get(i).getAge()>sorted.get(j).getAge()){
                    Animal temp=sorted.get(i);
                    sorted.set(i,sorted.get(j));
                    sorted.set(j,temp);
                }
            }
        }
        return sorted;
    }
    public void showAll(){
        for(Animal a:animals){
            System.out.println(a);
        }
    }

}
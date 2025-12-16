public class ZooKeeper {
    private String name;
    private int experience;

    public ZooKeeper (String name, int experience){
        this.name=name;
        this.experience=experience;
    }
    public String getName(){
        return name;
    }
    public int getExperience(){
        return experience;
    }
    public void displayZooKeeperInfo(){
        System.out.println("Zookeeper: " + name + ", Experience: " + experience + "years ");
    }
}
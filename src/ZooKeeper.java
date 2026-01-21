public class ZooKeeper{
    private String name;
    private int id;

    public ZooKeeper(String name, int id){
        this.name=name;
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString(){
        return name+"(ID:"+id+")";
    }
}
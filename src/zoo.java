public class zoo {
    private String zooname;
    public zoo (String zooname){
        this.zooname=zooname;
    }
    public String getZooName (){
        return zooname;
    }
    public void displayZooInfo(){
        System.out.println("Zoo: " + zooname);
    }
}
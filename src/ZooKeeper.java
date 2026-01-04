import java.util.Objects;

public class ZooKeeper {
    private String name;
    private int experience;
    private int id;

    public ZooKeeper(String name, int experience, int id) {
        this.name = name;
        this.experience = experience;
        this.id = id;
    }

    public String getName() { return name; }
    public int getExperience() { return experience; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return name + ", experience: " + experience + " years, ID: " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ZooKeeper keeper = (ZooKeeper) obj;
        return id == keeper.id && name.equals(keeper.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
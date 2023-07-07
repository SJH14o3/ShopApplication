package source.database;

public class Warehouse {
    public final String name;
    public final String manager;
    public final String address;
    public final int id;

    public Warehouse(String name, String manager, String address, int id) {
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.id = id;
    }
}

package comportamentali.iterator;

public class Player {

    private String name;
    private String role;

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, role);
    }
}

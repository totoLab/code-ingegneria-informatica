package strutturali.flyweight;

public class EntityInstance {

    private final Entity entityType;

    private int x, y;
    private boolean isHostile;

    public EntityInstance(Entity entityType) {
        this.entityType = entityType;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHostility(boolean isHostile) {
        this.isHostile = isHostile;
    }

    public void render() {
        entityType.render(x, y, isHostile);
    }
}

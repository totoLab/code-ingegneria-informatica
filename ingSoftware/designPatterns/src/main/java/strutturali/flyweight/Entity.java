package strutturali.flyweight;

public abstract class Entity {

    protected final int armorLevel;
    protected final boolean hasSword;

    public Entity(boolean hasSword, int armorLevel) {
        this.hasSword = hasSword;
        this.armorLevel = armorLevel;
    }

    abstract void render(int x, int y, boolean isHostile);
}
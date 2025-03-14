package strutturali.flyweight;

public class Ghost extends Entity {

    public Ghost(boolean hasSword, int baseArmorLevel) {
        super(hasSword, baseArmorLevel);
    }

    @Override
    public void render(int x, int y, boolean isHostile) {
        System.out.println("Rendering ghost at (" + x + "," + y + ")");
        System.out.println("  Has sword: " + hasSword);
        System.out.println("  Base armor: " + armorLevel);
        System.out.println("  Is hostile: " + isHostile);
    }
}
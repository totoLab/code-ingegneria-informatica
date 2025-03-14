package strutturali.flyweight;

public class Zombie extends Entity {

    public Zombie(boolean hasSword, int baseArmorClass) {
        super(hasSword, baseArmorClass);
    }

    @Override
    public void render(int x, int y, boolean isHostile) {
        System.out.println("Rendering zombie at (" + x + "," + y + ")");
        System.out.println("  Has sword: " + hasSword);
        System.out.println("  Base armor: " + armorLevel);
        System.out.println("  Is hostile: " + isHostile);
    }
}
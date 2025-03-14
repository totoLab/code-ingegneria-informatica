package strutturali.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        
        EntityFactory.getInstance().add("zombie", new Zombie(true, 2));
        EntityFactory.getInstance().add("ghost", new Ghost(false, 5));

        List<EntityInstance> spawnedEntities = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            Entity zombieType = EntityFactory.getInstance().getEntity("zombie");
            EntityInstance zombie = new EntityInstance(zombieType);
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            zombie.move(x, y);
            zombie.setHostility(true);

            spawnedEntities.add(zombie);
        }

        
        for (int i = 0; i < 5; i++) {
            Entity ghostType = EntityFactory.getInstance().getEntity("ghost");
            EntityInstance ghost = new EntityInstance(ghostType);
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            ghost.move(x, y);

            boolean isHostile = rand.nextBoolean();
            ghost.setHostility(isHostile);

            spawnedEntities.add(ghost);
        }

        System.out.println("Rendering first 3 entities:");
        for (int i = 0; i < 10; i++) {
            spawnedEntities.get(i).render();
            System.out.println();
        }
    }
}
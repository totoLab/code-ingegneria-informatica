package strutturali.flyweight;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
    private static EntityFactory instance = new EntityFactory();
    private Map<String, Entity> entities = new HashMap<String, Entity>();

    private EntityFactory() {}

    public static EntityFactory getInstance() {
        return instance;
    }

    public Entity getEntity(String name) {
        return entities.get(name);
    }

    public void add(String name, Entity entity) {
        entities.put(name, entity);
    }
}
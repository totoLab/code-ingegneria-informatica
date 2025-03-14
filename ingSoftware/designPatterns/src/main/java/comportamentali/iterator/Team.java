package comportamentali.iterator;

import java.util.ArrayList;
import java.util.List;

public class Team implements TeamAggregate {

    private final List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public Iterator createIterator() {
        return new TeamIterator(this);
    }

    @Override
    public Iterator createRoleIterator(String role) {
        return new TeamIterator(this, role);
    }
}

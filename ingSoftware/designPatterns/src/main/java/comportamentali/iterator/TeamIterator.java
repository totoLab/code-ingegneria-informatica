package comportamentali.iterator;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class TeamIterator implements Iterator {

    private int current = -1;
    private List<Player> players;
    private String role;

    public TeamIterator(Team team) {
        players = new ArrayList<>(team.getPlayers());
    }

    public TeamIterator(Team team, String role) {
        players = team.getPlayers().stream().filter(o -> o.getRole().equals(role)).toList();
    }

    @Override
    public Player next() {
        current++;
        return players.get(current);
    }

    @Override
    public boolean hasNext() {
        return current + 1 < players.size();
    }
}

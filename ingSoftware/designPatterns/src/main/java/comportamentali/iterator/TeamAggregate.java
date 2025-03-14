package comportamentali.iterator;

public interface TeamAggregate {

    Iterator createIterator();
    Iterator createRoleIterator(String role);
}

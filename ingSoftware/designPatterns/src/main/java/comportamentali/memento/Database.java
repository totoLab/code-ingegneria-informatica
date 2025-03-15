package comportamentali.memento;

import java.util.*;

public class Database {

    private String name;
    private List<String[]> rows;

    public Database(String name) {
        this.name = name;
        rows = new ArrayList<>();

    }

    private class Memento implements MementoWide, MementoNarrow {

        String name;
        List<String[]> rows;
        Date date;

        public Memento(String name, List<String[]> rows) {
            this.name = name;
            this.rows = new ArrayList<>(rows);
            this.date = new Date();
        }

        @Override
        public List<String[]> getRows() {
            return new ArrayList<>(rows);
        }

        @Override
        public void setRows(List<String[]> rows) {
            rows = new ArrayList<>(rows);
        }
    }

    void add(String[] row) {
        rows.add(row);
    }

    MementoNarrow save() {
        MementoWide memento = new Memento(this.name, this.rows);
        return (MementoNarrow) memento;
    }

    void restore(MementoNarrow memento) {
        this.rows = new ArrayList<>(((MementoWide) memento).getRows());
    };

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append("\n");
        for (String[] row : rows) {
            result.append(Arrays.toString(row));
            result.append("\n");
        }
        return result.toString();
    }
}

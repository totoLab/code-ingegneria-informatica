package strutturali.adapter;

public class Position implements Pos {

    private PosFloat x;
    private PosFloat y;

    class PosFloat {
        float value;

        public PosFloat(float value) {
            if (value < 0) throw new IllegalArgumentException();
            this.value = value;
        }

        public float getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof PosFloat)) return false;
            PosFloat posFloat = (PosFloat) obj;
            return value == posFloat.value;
        }
    }

    public Position(float x, float y) {
        this.x = new PosFloat(x);
        this.y = new PosFloat(y);
    }

    public PosFloat getX() {
        return x;
    }

    public PosFloat getY() {
        return y;
    }

    public void printPosition() {
        System.out.printf("(%.2f, %.2f)\n", x.getValue(), y.getValue());
    }
}

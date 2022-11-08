package esercitazioni.matrici;

public interface MatriceQuadrata {
    
    int getOrdine();
    int getEl(int row, int col);
    void setEl(int newEl, int row, int col);

    void addWithThis(MatriceQuadrata m);
    MatriceQuadrata add(MatriceQuadrata m);

    void mulNumWithThis(int num);
    MatriceQuadrata mul(int num);

    void mulWithThis(MatriceQuadrata m);
    MatriceQuadrata mul(MatriceQuadrata m);

    boolean puntoDiSella();
    MatriceQuadrata newInsMatriceQuadrata(MatriceQuadrata m);
}
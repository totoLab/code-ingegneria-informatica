package threadLifeCicle;

public class ElementoSella extends Thread {
	// esercizio 2.3
	// individua se esiste un elemento che è conteporaneamente max riga e min col
	// data una matrice n x m si utilizzino n thread per determinare il massimo e m per i minimi
	// main con matrice di test e stampa la posizione del primo trovato
	
	// variante: trovare TUTTI i punti di sella
	
	enum Op {min, max};
	
	private int resultIndex;
	final private int[][] matrix;
	final private int index;
	final private Op op;
	
	public ElementoSella(final int[][] matrix, int index, Op op) {
		this.resultIndex = -1;
		this.matrix = matrix;
		this.index = index;
		this.op = op;
	}
	
	private void minInCol(int colIndex) {
		if (matrix.length == 1) {
			this.resultIndex = 0;
		}
		
		int min = matrix[0][colIndex];
		this.resultIndex = 0;
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][colIndex] < min) {
				min = matrix[i][colIndex];
				this.resultIndex = i;
			}
		}
	}
	
	private void maxInRow(int rowIndex) {
		int[] row = matrix[rowIndex];
		if (row.length == 1) {
			this.resultIndex = 0;
		}
		
		int max = row[0];
		this.resultIndex = 0;
		for (int i = 1; i < row.length; i++) {
			if (row[i] > max) {
				max = row[i];
				this.resultIndex = i; 
			}
		}
	}
	
	@Override
	public void run() {
		switch (op) {
		case min: {
			minInCol(this.index);
			break;
		}
		case max:
			maxInRow(index);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		}
	}
	
	public int getResultIndex() throws InterruptedException {
		this.join();
		return this.resultIndex;
	}
	
	private static void find(int[][] matrix) {
		int n = matrix.length;
		ElementoSella[] threads = new ElementoSella[n];
		for (int i = 0; i < matrix.length; i++) {
			threads[i] = new ElementoSella(matrix, i, Op.max);
			threads[i].start();
		}
		
		int[] maxPerRow = new int[matrix.length];
		for (int i = 0; i < threads.length; i++) {
			
			int value = -1;
			try {
				 value = threads[i].getResultIndex();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				maxPerRow[i] = value;
			}
			
		}
		
		System.out.println("Massimi riga:");
		for (int i = 0; i < maxPerRow.length; i++) {
			System.out.print("(" + i + ", " + maxPerRow[i] + ") ");
		}
		System.out.println("\n");
		
		int m = maxPerRow.length;
		threads = new ElementoSella[m];
		for (int i = 0; i < m; i++) {
			threads[i] = new ElementoSella(matrix, maxPerRow[i], Op.min);
			threads[i].start();
		}
		
		System.out.println("Punto di sella: ");
		for (int i = 0; i < maxPerRow.length; i++) {
			int column = -1;
			try {
				column = threads[i].getResultIndex();
			} catch (InterruptedException e) {
				System.err.println("ops");
				e.printStackTrace();
			} finally {
				if (column == i)
					System.out.println("(" + i + ", " + maxPerRow[i] + ")");
			}
		}		
	}
	
	public static void main(String[] args) {
		// int[][] m = new int[3][3];
		
		int[][] matrix = { 
				{5, 5, 3, 6},
				{4, 1, 1, 4},
				{6, 9, 10, 5}
		};
		
		find(matrix);
		System.out.println("Done");
	}
}

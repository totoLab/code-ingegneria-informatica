package esercitazioni.matrici;

public class Test {

	public static void main(String[] args) {
		MatriceQuadrata m1 = new MatriceQuadrataArray(3);
		int[] a = {7, 3, 5, 9, 1, 0, 13, 19, 22};
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m1.setEl(a[index], i, j);
				index++;
			}
		}
		System.out.println(m1);
		
		MatriceQuadrata m2 = new MatriceQuadrataArray(3);
		int[] b = {12, 30, 1, 4, 4, 22, 3, 14, 17};
		index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m2.setEl(b[index], i, j);
				index++;
			}
		}
		System.out.println(m2);
		
		System.out.println(m1.add(m2));		
		System.out.println(m1.mul(2));
		System.out.println(m1.mul(m2)); // to fix: operating on original matrix messes up elements used for vector product
	}

}

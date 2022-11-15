package esercitazioni.matrici;

import java.util.Iterator;

public class ApplicazioneMatrice {

	
	public static void main(String[] args) {
		
		//MatriceQuadrata m1 = new MatriceQuadrataSparsa(3);
		//MatriceQuadrata m1 = new MatriceQuadrataArrayDiArray(3);
		MatriceQuadrata m1 = new MatriceQuadrataSparsa(3);
		m1.setEl(0,0,2);
		m1.setEl(1,1,2);
		m1.setEl(2,2,2);
		m1.setEl(2,2,7);
		m1.setEl(2,1,0);
		m1.setEl(1,2,5);
		
		System.out.println("m1:");
		System.out.println(m1);
		
		Iterator it = ((MatriceQuadrataSparsa) m1).iterator();
		while(it.hasNext()) {
			int val = (int)it.next();
			System.out.println(val);
			if(val>0) {
				it.remove();
			}
		}
		
		System.out.println("---------------------");
		System.out.println("m1:");
		System.out.println(m1);
		System.out.println("---------------------");
		
		//MatriceQuadrata m2 = new MatriceQuadrataArrayDiArray(3);//new MatriceQuadrataSparsa(3);
		MatriceQuadrata m2 = new MatriceQuadrataArrayDiArray(3);
		m2.setEl(0,0,2);
		m2.setEl(0,1,2);
		m2.setEl(2,2,2);
		m2.setEl(2,2,7);
		m2.setEl(2,1,0);
		m2.setEl(1,1,5);
		
		System.out.println("m2:");
		System.out.println(m2);
		
		MatriceQuadrata m3 = m1.add(m2);
		System.out.println("m3:");
		System.out.println(m3);
		
		MatriceQuadrata m4 = m1.mul(3);
		System.out.println("m4:");
		System.out.println(m4);
		
		
		
		//MatriceQuadrata m2 = new MatriceQuadrataArrayDiArray(3);//new MatriceQuadrataSparsa(3);
		MatriceQuadrata m5 = new MatriceQuadrataArrayDiArray(4);
		m5.setEl(0,0,2);
		m5.setEl(0,1,17);
		m5.setEl(0,2,19);
		m5.setEl(0,3,22);

		m5.setEl(1,0,3);
		m5.setEl(1,1,20);
		m5.setEl(1,2,7);
		m5.setEl(1,3,9);
		
		m5.setEl(2,0,25);
		m5.setEl(2,1,23);
		m5.setEl(2,2,32);
		m5.setEl(2,3,40);
		
		m5.setEl(3,0,13);
		m5.setEl(3,1,21);
		m5.setEl(3,2,27);
		m5.setEl(3,3,39);
		
		
		m5.puntoDiSella();
	}

}

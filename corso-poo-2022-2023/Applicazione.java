public class Applicazione{

	public static void main(String args[]){

			Punto p = new Punto();

			System.out.println(p);

			Punto nuovo = p;

			p.sposta(-4,6);

			System.out.println(nuovo);

	}

}
package terminale;

public class Terminale {
	private static java.util.Scanner keyboard = new java.util.Scanner(System.in);

	public static String richiediStringa(String prompt)
	{	stampaNoCR(prompt);
		String v = keyboard.nextLine();
		return v;
	}

	public static int richiediInt(String prompt)
	{	stampaNoCR(prompt);
		int v = keyboard.nextInt();
		keyboard.nextLine();
		return v;
	}

	public static long richiediLong(String prompt)
	{	stampaNoCR(prompt);
		long v = keyboard.nextLong();
		keyboard.nextLine();
		return v;
	}

	public static float richiediFloat(String prompt)
	{	stampaNoCR(prompt);
		float v = keyboard.nextFloat();
		keyboard.nextLine();
		return v;
	}

	public static double richiediDouble(String prompt)
	{	stampaNoCR(prompt);
		double v = keyboard.nextDouble();
		keyboard.nextLine();
		return v;
	}

	public static void stampaLineaVuota()
	{	System.out.println();
	}

	public static void stampaNoCR(Object s)
	{	System.out.print(s);
	}

	public static void stampa(Object s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(int s)
	{	System.out.print(s);
	}

	public static void stampa(int s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(long s)
	{	System.out.print(s);
	}

	public static void stampa(long s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(float s)
	{	System.out.print(s);
	}

	public static void stampa(float s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(double s)
	{	System.out.print(s);
	}

	public static void stampa(double s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(char s)
	{	System.out.print(s);
	}

	public static void stampa(char s)
	{	System.out.println(s);
	}

	public static void stampaNoCR(boolean s)
	{	System.out.print(s);
	}

	public static void stampa(boolean s)
	{	System.out.println(s);
	}
}


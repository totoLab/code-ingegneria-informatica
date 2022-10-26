public class Punto{

	private double x,y;

	public Punto(double xx, double yy){
		x=xx;
		y=yy;
	}

	public Punto(){
		this(0,0);
	}

	public Punto(Punto p){
		this.x=p.x;
		this.y=p.y;
	}



	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void sposta(double x, double y){
		this.x=x;
		this.y=y;
	}

	public double distanza(Punto p){
	    return Math.sqrt( (p.x-this.x)*(p.getX()-this.x)+ (p.y-this.y)*(p.y-this.y));
	}

	public String toString(){
		return "<"+x+","+y+">";
	}

}
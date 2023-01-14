package tracceesame.appello02092022;

public abstract class AbstractLidoBalneare implements LidoBalneare {
	
	int file, ombrelloniPerFila;
	
	public AbstractLidoBalneare(int file, int ombrelloniPerFila) {
		this.file = file;
		this.ombrelloniPerFila = ombrelloniPerFila;
	}
	
	@Override
	public int numeroFileOmbrelloni() {
		return file;
	}
	
	@Override
	public int numeroOmbrelloniPerFila() {
		return ombrelloniPerFila;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

public class GiocoDellaVita{
	private char [][]mappa;
	private char [][]nuovaMappa;
	private int n, m;
	public GiocoDellaVita( int n, int m ){
		//pre: i valori di n e m sono ok
		this.n=n; this.m=m;
		mappa=new char[n][m];
		nuovaMappa=new char[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) mappa[i][j]='.';
	}
	public void aggiungiOrganismo( int i, int j ){
		if( i<0 || i>=n || j<0 || j>=m )
            throw new IllegalArgumentException();
		mappa[i][j]='*';
	}//aggiungiOrganismo

    public void configuraRandom(){
		for( int i=0; i<n; ++i )
           for( int j=0; j<m; ++j )
               if( Math.random()<0.5 ) mappa[i][j]='*'; //oppure: mappa[i][j]=Math.random()<0.5?'*':'.'; con if implicito
               else mappa[i][j]='.';
   }//configuraRandom

   private int vicini( int i, int j ){
   		int cont=0;
   		if( i>0 && mappa[i-1][j]=='*' ) cont++; //NORD
   		if( i>0 && j<m-1 && mappa[i-1][j+1]=='*' ) cont++; //NE
   		if( j<m-1 && mappa[i][j+1]=='*' ) cont++; //EST
   		if( i<n-1 && j<m-1 && mappa[i+1][j+1]=='*' ) cont++; //SE
   		if( i<n-1 && mappa[i+1][j]=='*' ) cont++; //SUD
   		if( i<n-1 && j>0 && mappa[i+1][j-1]=='*' ) cont++; //SO
   		if( j>0 && mappa[i][j-1]=='*' ) cont++; //OVEST
   		if( i>0 && j>0 && mappa[i-1][j-1]=='*' ) cont++; //NO
   		return cont;
   	}//vicini

   	public void prossimaGenerazione(){
		for( int i=0; i<n; i++ )
			for( int j=0; j<m; j++ ){
				int v=vicini( i, j );
				if( mappa[i][j]=='*' )
					nuovaMappa[i][j]=( v==2 || v==3 ) ? '*' : '.';
				else
					nuovaMappa[i][j]=( v==3 ) ? '*' : '.';
			}
		char[][] tmp=mappa;
		mappa=nuovaMappa;
		nuovaMappa=tmp;
	}//prossimaGenerazione

	public String toString(){
		String s="";
		for( int i=0; i<n; i++ ){
	      for( int j=0; j<m; j++ ) s=s+mappa[i][j];
	      s=s+'\n';
		}
		return s;
	}//toString

	public static void main( String[] args ){//test
		GiocoDellaVita gol=new GiocoDellaVita(5,7);
		gol.configuraRandom();
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
	}//main

}//GiocoDellaVita



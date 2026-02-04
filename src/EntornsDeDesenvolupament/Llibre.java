package EntornsDeDesenvolupament;

public class Llibre {
	int    identificador;
	String titol;
	String autor;
	String tematica;
	
	
	public Llibre(int id, String titol, String autor, String tematica) {
		this.identificador  =  id;
		this.titol          =  titol;
		this.autor          =  autor;
		this.tematica       =  tematica;
	}
	
	public String getTitol() {
		return this.titol;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public String getTematica() {
		return this.tematica;
	}
	
	public int getID() {
		return this.identificador;
	}
	
}

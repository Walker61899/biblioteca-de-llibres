package EntornsDeDesenvolupament;

public enum Filtre {
	TITOL ("Títol"),
	AUTOR ("Autor"),
	TEMATICA("Temàtica");
	
	private final String NOM;
	
	Filtre(String nom) {
		this.NOM = nom;
	}
	
	public String getNom() {
		return NOM;
	}
}


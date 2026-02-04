package EntornsDeDesenvolupament;


public class Reserva {

	private Llibre llibreReservat;
	private String usuari;
	
	
	public Reserva(String usuari, Llibre llibre) {
		this.usuari = usuari;
		this.llibreReservat = llibre;
	}
	
	public Llibre getLlibresReservats() {
		return this.llibreReservat;
	}
	
	public String getUsuari() {
		return this.usuari;
	}

	public Llibre getLlibreReservat() {
		return llibreReservat;
	}

	public void setLlibreReservats(Llibre llibreReservats) {
		this.llibreReservat = llibreReservats;
	}
}

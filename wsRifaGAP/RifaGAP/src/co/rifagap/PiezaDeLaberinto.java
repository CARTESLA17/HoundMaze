package co.rifagap;

public class PiezaDeLaberinto {
	
	private char representacion;
	private boolean piezaHabilitada=true;
	private boolean arriba=true;
	private boolean abajo=true;
	private boolean derecha=true;
	private boolean izquierda=true;	
	private Coordenadas coordenadas = new Coordenadas();

	public PiezaDeLaberinto() {
		super();
	}

	public char getRepresentacion() {
		return representacion;
	}

	public void setRepresentacion(char representacion) {
		this.representacion = representacion;
	}

	public boolean isPiezaHabilitada() {
		return piezaHabilitada;
	}

	public void setPiezaHabilitada(boolean piezaHabilitada) {
		this.piezaHabilitada = piezaHabilitada;
	}

	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public void setIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}
	
}

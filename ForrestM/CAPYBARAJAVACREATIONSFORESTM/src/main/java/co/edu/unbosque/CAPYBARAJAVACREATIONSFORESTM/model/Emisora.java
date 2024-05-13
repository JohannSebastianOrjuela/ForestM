package co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Emisora {

	@Id
	private String nombre_emisora;
	private String modo_transmision;
	private String tipo_musica;

	public String getNombre_emisora() {
		return nombre_emisora;
	}

	public void setNombre_emisora(String nombre_emisora) {
		this.nombre_emisora = nombre_emisora;
	}

	public String getModo_transmision() {
		return modo_transmision;
	}

	public void setModo_transmision(String modo_transmision) {
		this.modo_transmision = modo_transmision;
	}

	public String getTipo_musica() {
		return tipo_musica;
	}

	public void setTipo_musica(String tipo_musica) {
		this.tipo_musica = tipo_musica;
	}

}

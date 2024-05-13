package co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class PistasMusicales {
	@Id
	private String nombreCancion;
	private String generoMusical;
	private String nombreArtista;
	private String nombreArchivo;
	
	public String getNombreCancion() {
		return nombreCancion;
	}
	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}
	public String getGeneroMusical() {
		return generoMusical;
	}
	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}
	public String getNombreArtista() {
		return nombreArtista;
	}
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
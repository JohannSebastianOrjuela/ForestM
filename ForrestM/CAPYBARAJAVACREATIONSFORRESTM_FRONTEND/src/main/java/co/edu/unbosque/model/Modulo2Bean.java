package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import co.edu.unbosque.model.JSON.TestJSONPistaMusical;
import co.edu.unbosque.model.dto.PistasMusicales;

@ManagedBean
@ViewScoped
public class Modulo2Bean {
	private String nombreCancion;
	private String generoMusical;
	private String nombreArtista;
	private String nombreArchivo;
	private ArrayList<PistasMusicales> lista;

	
	

	public ArrayList<PistasMusicales> getLista() {
		return lista;
	}
	public void setLista(ArrayList<PistasMusicales> lista) {
		this.lista = lista;
	}
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

	public String agregarPistaMusical() {

		PistasMusicales pistaMusical = new PistasMusicales();
		pistaMusical.setNombreCancion(this.nombreCancion);
		pistaMusical.setGeneroMusical(this.generoMusical);
		pistaMusical.setNombreArtista(this.nombreArtista);
		pistaMusical.setNombreArchivo(this.nombreArchivo);
		
		int respuesta = 0;
		try {
			respuesta = TestJSONPistaMusical.postJSON(pistaMusical);
			
			if (respuesta == 200) {
				return "validacionCancionRegistrada";
			} else {
				return "error";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	public String listarPistasMusicales() {
		try {
			this.lista = TestJSONPistaMusical.getJSON();
			return "tabla";
		} catch (Exception e) {
			return "error";
		}
	}
	public String eliminarPistaMusical() {
		try {
			TestJSONPistaMusical.deleteJSON(nombreCancion);
			return "validacionCancionEliminada";
		} catch (Exception e) {
			return "error";
		}
	}
}

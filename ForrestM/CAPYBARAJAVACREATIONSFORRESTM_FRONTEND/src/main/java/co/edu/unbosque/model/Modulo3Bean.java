package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.JSON.TestJSONPistaMusical;
import co.edu.unbosque.model.JSON.TestJSONProgramacionDelDia;
import co.edu.unbosque.model.dto.PistasMusicales;
import co.edu.unbosque.model.dto.ProgramacionDelDia;

@ManagedBean
@ViewScoped
public class Modulo3Bean {
	private ArrayList<PistasMusicales> lista;
	private List<String> listaSeleccionada; 
	private String fecha;

	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ArrayList<PistasMusicales> getLista() {
		return lista;
	}
	public void setLista(ArrayList<PistasMusicales> lista) {
		this.lista = lista;
	}
	public List<String> getListaSeleccionada() {
		return listaSeleccionada;
	}

	public void setListaSeleccionada(List<String> listaSeleccionada) {
		this.listaSeleccionada = listaSeleccionada;
	}
	@PostConstruct
	public void init() {
	    listaSeleccionada = new ArrayList<>();
	    try {
			lista = TestJSONPistaMusical.getJSON();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}


	
	public List<PistasMusicales> obtenerPistas(){
		List<PistasMusicales> pista = null;
		try {
			pista =  TestJSONPistaMusical.getJSON();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return pista;
	}
	public String guardarProgramacionDelDia() {

	    ProgramacionDelDia programacion = new ProgramacionDelDia();
	    programacion.setFecha(this.fecha);
	    programacion.setPistas(this.listaSeleccionada); 

	    int respuesta = 0;
	    try {
	        respuesta = TestJSONProgramacionDelDia.postJSON(programacion);
	        System.out.println(respuesta);
	        if (respuesta == 200) {
	            return "validacionParrillaRegistrada";
	        } else {
	            return "error";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	public String cargarPistas() {
		try {
			this.lista = TestJSONPistaMusical.getJSON();
			return "NewFile.xhtml";
		} catch (Exception e) {
			return "error";
		}
	}
	public String eliminarProgramacion() {
		int respuesta;
		try {
			respuesta = TestJSONProgramacionDelDia.deleteJSON(fecha);
			
		System.out.println(respuesta);	
		return "validacionParrillaEliminada";
		} catch (Exception e) {
			return "error";
		}
	}
}

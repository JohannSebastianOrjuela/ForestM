package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.JSON.TestJSONEmisora;
import co.edu.unbosque.model.dto.Emisora;

@ManagedBean
@ViewScoped
public class Modulo1Bean {

	private String nombre_emisora;
	private String modo_transmision;
	private String tipo_musica;
	private ArrayList<Emisora> emisoraC;

	public String modulo1R() throws IOException, ParseException {
	    emisoraC = TestJSONEmisora.getJSON();
    	this.nombre_emisora = emisoraC.get(0).getNombre_emisora();
    	this.modo_transmision = emisoraC.get(0).getModo_transmision();
    	this.tipo_musica = emisoraC.get(0).getTipo_musica();
		
		
		return "modulo1.xhtml";
	}
	
	
	public String agregarEmisora() throws IOException, ParseException {
		
	    emisoraC = TestJSONEmisora.getJSON();
	    
	    if(emisoraC.size() == 0) {
		
		Emisora emisora = new Emisora();
		emisora.setNombre_emisora(this.nombre_emisora);
		emisora.setModo_transmision(this.modo_transmision);
		emisora.setTipo_musica(this.tipo_musica);

		int respuesta = 0;

		try {

			respuesta = TestJSONEmisora.postJSON(emisora);

			if (respuesta == 200) {

				return "validacionEmisoraResgistrada";

			} else {

				return "errorEmisora";
			}
		} catch (Exception e) {

			e.printStackTrace();

			return "errorEmisora";
		}
	    }else {
	    	this.nombre_emisora = emisoraC.get(0).getNombre_emisora();
	    	this.modo_transmision = emisoraC.get(0).getModo_transmision();
	    	this.tipo_musica = emisoraC.get(0).getTipo_musica();
	    	return "validacionEmisoraResgistrada";
	    }
	}



	public String modificarEmisora() {
		try {
			Emisora emisora = new Emisora();
			emisora.setNombre_emisora(this.nombre_emisora);
			emisora.setModo_transmision(this.modo_transmision);
			emisora.setTipo_musica(this.tipo_musica);

			int respuesta = TestJSONEmisora.postJSON(emisora);

			if (respuesta == 200) {
				return "validacionEmisoraRegistrada";
			} else {
				return "errorEmisora";
			}
		} catch (Exception e) {
			return "errorEmisora";
		}
	}

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

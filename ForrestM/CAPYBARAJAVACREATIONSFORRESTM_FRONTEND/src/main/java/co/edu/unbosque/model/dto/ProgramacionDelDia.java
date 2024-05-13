package co.edu.unbosque.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ProgramacionDelDia {
	
    private String fecha;
    private List<String> pistas = new ArrayList<>(); 
    
	
	
	public List<String> getPistas() {
		return pistas;
	}
	public void setPistas(List<String> pistas) {
		this.pistas = pistas;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}

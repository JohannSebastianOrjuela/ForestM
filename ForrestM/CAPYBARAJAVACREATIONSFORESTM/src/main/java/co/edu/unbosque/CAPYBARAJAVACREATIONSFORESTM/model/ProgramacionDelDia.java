package co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class ProgramacionDelDia {
    @Id
    private String fecha; 

    @ElementCollection
    private List<String> pistas;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) { 
        this.fecha = fecha;
    }

	public List<String> getPistas() {
		return pistas;
	}

	public void setPistas(List<String> pistas) {
		this.pistas = pistas;
	}

   
}

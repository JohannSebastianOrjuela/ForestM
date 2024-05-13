package co.edu.unbosque.model;

import javazoom.jl.player.Player;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.JSON.TestJSONProgramacionDelDia;
import co.edu.unbosque.model.dto.ProgramacionDelDia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
public class Modulo4Bean {
    private List<String> archivosAudio; 
    private int indiceAudioActual = 0; 
    private Player reproductor; 
    private boolean estaReproduciendo = false; 
    private List<ProgramacionDelDia> programaciones; 
    private String fechaSeleccionada;

    @PostConstruct
    public void init() {
        cargarProgramaciones();
    }
    
    public String redirigir() {
        cargarProgramaciones();
    	return"modulo4";
    }
     
    public void cargarProgramaciones() {
        try {
            this.programaciones = TestJSONProgramacionDelDia.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void reproducirProgramacion(String fecha) {
        for (ProgramacionDelDia programacion : programaciones) {
            if (programacion.getFecha().equals(fecha)) {
                this.archivosAudio = programacion.getPistas();
                this.indiceAudioActual = 0;
                reproducir();
                break;
            }
        }
    }
    
    public void siguiente() {
        if (estaReproduciendo) {
            detener();
        }
        if (indiceAudioActual < archivosAudio.size() - 1) {
            indiceAudioActual++;
        }
        reproducir();
    }

    public void anterior() {
        if (estaReproduciendo) {
            detener();
        }
        if (indiceAudioActual > 0) {
            indiceAudioActual--;
        }
        reproducir();
    }

    public void reproducir() {
        if (estaReproduciendo) {
            detener();
        }
        if (archivosAudio != null && indiceAudioActual < archivosAudio.size()) {
            String archivoAudioActual = archivosAudio.get(indiceAudioActual);
            try {
                FileInputStream fis = new FileInputStream(archivoAudioActual);
                if (reproductor != null) {
                    reproductor.close();
                }
                reproductor = new Player(fis);
                new Thread(() -> {
                    try {
                        reproductor.play();
                        estaReproduciendo = false;
                        if (indiceAudioActual < archivosAudio.size() - 1) { 
                            indiceAudioActual++;
                            reproducir();
                        }
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }).start();
                estaReproduciendo = true;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }



    public void detener() {
        if (estaReproduciendo) {
            reproductor.close();
            estaReproduciendo = false;
        }
    }

	public List<String> getArchivosAudio() {
		return archivosAudio;
	}

	public void setArchivosAudio(List<String> archivosAudio) {
		this.archivosAudio = archivosAudio;
	}

	public int getIndiceAudioActual() {
		return indiceAudioActual;
	}

	public void setIndiceAudioActual(int indiceAudioActual) {
		this.indiceAudioActual = indiceAudioActual;
	}

	public Player getReproductor() {
		return reproductor;
	}

	public void setReproductor(Player reproductor) {
		this.reproductor = reproductor;
	}

	public boolean isEstaReproduciendo() {
		return estaReproduciendo;
	}

	public void setEstaReproduciendo(boolean estaReproduciendo) {
		this.estaReproduciendo = estaReproduciendo;
	}

	public List<ProgramacionDelDia> getProgramaciones() {
		return programaciones;
	}

	public void setProgramaciones(List<ProgramacionDelDia> programaciones) {
		this.programaciones = programaciones;
	}

	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

}
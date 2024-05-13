package co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.dao.PistasMusicalesDAO;
import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model.PistasMusicales;

//a
@RestController 
@RequestMapping("pistasMusicales")
public class PistasMusicalesAPI {
	
	@Autowired 
	private PistasMusicalesDAO pistasMusicalesDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody PistasMusicales pistasMusicales) {
		pistasMusicalesDAO.save(pistasMusicales);
	}
	
	@GetMapping("/listar")
	public List<PistasMusicales> listar(){
		return pistasMusicalesDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{nombreCancion}")
	public void eliminar(@PathVariable("nombreCancion") String nombreCancion) {
		pistasMusicalesDAO.deleteById(nombreCancion);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody PistasMusicales pistasMusicales) {
		pistasMusicalesDAO.save(pistasMusicales);
	}
	
	
}
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

import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.dao.EmisoraDAO;
import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model.Emisora;

@RestController 
@RequestMapping("emisora")
public class EmisoraAPI {
	
	@Autowired 
	private EmisoraDAO emisoraDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Emisora emisora) {
		emisoraDAO.save(emisora);
	}
	
	@GetMapping("/listar")
	public List<Emisora> listar(){
		return emisoraDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{nombreEmisora}")
	public void eliminar(@PathVariable("nombreEmisora") String nombreEmisora) {
		emisoraDAO.deleteById(nombreEmisora);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Emisora emisora) {
		emisoraDAO.save(emisora);
	}
}

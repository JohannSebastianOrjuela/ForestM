package co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.dao.ProgramacionDelDiaDAO;
import co.edu.unbosque.CAPYBARAJAVACREATIONSFORESTM.model.ProgramacionDelDia;

@RestController
@RequestMapping("programacionDelDia")
public class ProgramacionDelDiaAPI {
	
	@Autowired
	private ProgramacionDelDiaDAO programacionDelDiaDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody ProgramacionDelDia programacion) {
		programacionDelDiaDAO.save(programacion);
	}
	
	@GetMapping("/listar")
	public List<ProgramacionDelDia> listar(){
		return programacionDelDiaDAO.findAll();
	}
}

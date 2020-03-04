package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.service.EntrenadorService;

@RestController
@RequestMapping("/api/entrenador/")
public class EntrenadorResource {

	@Autowired
	private EntrenadorService entrenadorService;
	
	@GetMapping
	public ResponseEntity<List<EntrenadorPayload>> findAll(){
		return new ResponseEntity<>(this.entrenadorService.findAll(),HttpStatus.OK);
	}
}
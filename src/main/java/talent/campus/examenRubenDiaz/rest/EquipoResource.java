package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.service.EquipoService;

@RestController
@RequestMapping("/api/equipo/")
public class EquipoResource {
	
	@Autowired
	private EquipoService equipoService;
	
	@GetMapping
	public ResponseEntity<List<EquipoPayload>> findAll(){
		return new ResponseEntity<>(this.equipoService.findAll(),HttpStatus.OK);
	}

}

package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.PartidoPayload;
import talent.campus.examenRubenDiaz.service.PartidoService;

@RestController
@RequestMapping("/api/partido/")
public class PartidoResource {
	
	@Autowired
	private PartidoService partidoService;
	
	@GetMapping
	public ResponseEntity<List<PartidoPayload>> findAll(){
		return new ResponseEntity<>(this.partidoService.findAll(),HttpStatus.OK);
	}

}
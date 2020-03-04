package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.service.EquipoService;
import talent.campus.examenRubenDiaz.utils.HttpHeaderUtils;

@RestController
@RequestMapping("/api/equipo/")
public class EquipoResource {
	
	@Autowired
	private EquipoService equipoService;
	
	@GetMapping
	public ResponseEntity<List<EquipoPayload>> findAll(){
		return new ResponseEntity<>(this.equipoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/id/{id}/")
	public ResponseEntity<EquipoPayload> findById(@PathVariable("id")Integer id){
		return new ResponseEntity<>(this.equipoService.findEquipoById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EquipoPayload> create(@RequestBody EquipoPayload request){
		EquipoPayload equipoPayload = this.equipoService.create(request);
		return new ResponseEntity<>(equipoPayload, HttpHeaderUtils.locationHeader(equipoPayload.getId()),HttpStatus.CREATED );
	}

}

package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.service.EntrenadorService;
import talent.campus.examenRubenDiaz.utils.HttpHeaderUtils;


@RestController
@RequestMapping("/api/entrenador/")
public class EntrenadorResource {

	@Autowired
	private EntrenadorService entrenadorService;
	
	@GetMapping
	public ResponseEntity<List<EntrenadorPayload>> findAll(){
		return new ResponseEntity<>(this.entrenadorService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/id/{id}/")
	public ResponseEntity<EntrenadorPayload> findById(@PathVariable("id")Integer id){
		return new ResponseEntity<>(this.entrenadorService.findEntrenadorById(id),HttpStatus.OK);
	}
	
	@GetMapping(path = "/filter/")
	public ResponseEntity<List<EntrenadorPayload>> getByFullName(@RequestParam(name="nombre")String nombre,
																@RequestParam(name="apellidos")String apellidos){
		return new ResponseEntity<>(this.entrenadorService.findByFullName(nombre, apellidos),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EntrenadorPayload> create(@RequestBody EntrenadorPayload request){
		EntrenadorPayload entrenadorPayload = this.entrenadorService.create(request);
		return new ResponseEntity<>(entrenadorPayload, HttpHeaderUtils.locationHeader(entrenadorPayload.getIdEntrenador()),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}/")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id){
		this.entrenadorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}

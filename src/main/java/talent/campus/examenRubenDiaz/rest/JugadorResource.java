package talent.campus.examenRubenDiaz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import talent.campus.examenRubenDiaz.dto.JugadorPayload;
import talent.campus.examenRubenDiaz.service.JugadorService;
import talent.campus.examenRubenDiaz.utils.HttpHeaderUtils;

@RestController
@RequestMapping("/api/jugador/")
public class JugadorResource {
	@Autowired
	private JugadorService jugadorService;

	@GetMapping
	public ResponseEntity<List<JugadorPayload>> findAll() {
		return new ResponseEntity<>(this.jugadorService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}/")
	public ResponseEntity<JugadorPayload> findById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(this.jugadorService.findJugadorById(id), HttpStatus.OK);
	}
	
	@GetMapping(path ="/filter/")
	public ResponseEntity<List<JugadorPayload>> getByEquipoAndEdad(@RequestParam(name="id")Integer id,
																@RequestParam(name="edad")Integer edad){
		return new ResponseEntity<>(this.jugadorService.findByEquipoAndEdad(id, edad), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<JugadorPayload> create(@RequestBody JugadorPayload request){
		JugadorPayload jugadorPayload = this.jugadorService.create(request);
		return new ResponseEntity<>(jugadorPayload, HttpHeaderUtils.locationHeader(jugadorPayload.getId()),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{id}/")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id){
		this.jugadorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path="/{id}/")
	public ResponseEntity<JugadorPayload> update(@PathVariable("id")Integer id, @RequestBody JugadorPayload request){
		return new ResponseEntity<>(this.jugadorService.update(id, request),HttpStatus.OK);
	}
	
	@PutMapping(path="/equipo/{id}/")
	public ResponseEntity<JugadorPayload> updateEquipo(@PathVariable("id")Integer id, @RequestBody JugadorPayload request){
		return new ResponseEntity<>(this.jugadorService.updateEquipo(id, request),HttpStatus.OK);
	}
	
}

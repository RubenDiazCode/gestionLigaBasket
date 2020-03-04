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
	
	@PostMapping
	public ResponseEntity<JugadorPayload> create(@RequestBody JugadorPayload request){
		JugadorPayload jugadorPayload = this.jugadorService.create(request);
		return new ResponseEntity<>(jugadorPayload, HttpHeaderUtils.locationHeader(jugadorPayload.getId()),HttpStatus.CREATED);
	}
	
}

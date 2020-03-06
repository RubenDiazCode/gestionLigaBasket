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

import talent.campus.examenRubenDiaz.dto.PartidoPayload;
import talent.campus.examenRubenDiaz.service.PartidoService;
import talent.campus.examenRubenDiaz.utils.HttpHeaderUtils;

@RestController
@RequestMapping("/api/partido/")
public class PartidoResource {

	@Autowired
	private PartidoService partidoService;

	@GetMapping
	public ResponseEntity<List<PartidoPayload>> findAll() {
		return new ResponseEntity<>(this.partidoService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}/")
	public ResponseEntity<PartidoPayload> findById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(this.partidoService.findPartidoById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PartidoPayload> create(@RequestBody PartidoPayload request){
		PartidoPayload partidoPayload = this.partidoService.create(request);
		return new ResponseEntity<>(partidoPayload, HttpHeaderUtils.locationHeader(partidoPayload.getId()),HttpStatus.CREATED );
	}

}

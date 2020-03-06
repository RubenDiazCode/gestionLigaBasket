package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.JugadorPayload;
import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.model.Jugador;
import talent.campus.examenRubenDiaz.repository.EquipoRepository;
import talent.campus.examenRubenDiaz.repository.JugadorRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class JugadorService {
	@Autowired
	private JugadorRepository jugadorRepository;
	@Autowired
	private EquipoRepository equipoRepository;
	@Autowired
	private EquipoService equipoService;
	

	public JugadorPayload toJugadorPayload(Jugador jugador) {
		JugadorPayload jugadorPayload = new JugadorPayload();
		jugadorPayload.setId(jugador.getId());
		jugadorPayload.setNombre(jugador.getNombre());
		jugadorPayload.setApellidos(jugador.getApellidos());
		jugadorPayload.setEdad(jugador.getEdad());
		if(jugador.getEquipo()==null) {
			jugadorPayload.setEquipo(null);
		}else {
			jugadorPayload.setEquipo(this.equipoService.toEquipoPayload(jugador.getEquipo()));
		}
		
		return jugadorPayload;
	}

	private void saveJugador(JugadorPayload request, Jugador jugador) {
		jugador.setNombre(request.getNombre());
		jugador.setApellidos(request.getApellidos());
		jugador.setEdad(request.getEdad());
	}

	private Jugador toJugador(JugadorPayload request) {
		Jugador jugador = new Jugador();
		this.saveJugador(request, jugador);
		return jugador;
	}
	

	public List<JugadorPayload> findAll() {
		return this.jugadorRepository.findAll().stream().map(jugador -> this.toJugadorPayload(jugador))
				.collect(Collectors.toList());
	}

	private Jugador findById(Integer id) {
		if (id == null)
			throw ExceptionFactoryUtils.badRequestException("Id cannot be null");
		Optional<Jugador> jugadorOptional = this.jugadorRepository.findById(id);
		if (jugadorOptional.isPresent())
			return jugadorOptional.get();
		throw ExceptionFactoryUtils.resourceNotFoundException("Jugador no encontrado");
	}

	public JugadorPayload findJugadorById(Integer id) {
		Jugador jugador = this.findById(id);
		return this.toJugadorPayload(jugador);
	}

	public JugadorPayload create(JugadorPayload request) {
		if (this.jugadorRepository.existsByNombreAndApellidos(request.getNombre(), request.getApellidos()))
			throw ExceptionFactoryUtils.internalErrorException("El jugador ya existe");
		Jugador jugador = this.jugadorRepository.save(this.toJugador(request));
		return this.toJugadorPayload(jugador);
	}
	
	public void deleteById(Integer id) {
		Jugador jugador = this.findById(id);
		this.jugadorRepository.delete(jugador);
	}
	
	public JugadorPayload update(Integer id, JugadorPayload request) {
		Jugador jugador = this.findById(id);
		this.saveJugador(request, jugador);
		Jugador jugadorResult = this.jugadorRepository.save(jugador);
		return this.toJugadorPayload(jugadorResult);
	}
	
	public JugadorPayload updateEquipo(Integer idEquipo, JugadorPayload request) {
		Equipo equipo = new Equipo();
		Jugador jugador = this.findById(request.getId());
		Optional<Equipo> equipoOptional = this.equipoRepository.findById(idEquipo);
		if (equipoOptional.isPresent())
			equipo= equipoOptional.get();
		jugador.setEquipo(equipo);
		this.saveJugador(request, jugador);
		Jugador jugadorResult = this.jugadorRepository.save(jugador);
		return this.toJugadorPayload(jugadorResult);
		
	}
	
	//ejercicio 3
	public List<JugadorPayload> findByEquipoAndEdad(Integer id, Integer edad){
		return this.jugadorRepository.findByEquipoAndEdad(id, edad).stream()
				.map(jugador -> this.toJugadorPayload(jugador))
				.collect(Collectors.toList());
	}

}

package talent.campus.examenRubenDiaz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.dto.JugadorPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.model.Jugador;
import talent.campus.examenRubenDiaz.repository.EntrenadorRepository;
import talent.campus.examenRubenDiaz.repository.EquipoRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class EquipoService {

	@Autowired
	private EquipoRepository equipoRepository;
	@Autowired
	private EntrenadorRepository entrenadorRepository;
	@Autowired
	private EntrenadorService entrenadorService;

	public EquipoPayload toEquipoPayload(Equipo equipo) {
		EquipoPayload equipoPayload = new EquipoPayload();
		equipoPayload.setId(equipo.getId());
		equipoPayload.setNombre(equipo.getNombre());
		equipoPayload.setJugadores(castToListJugadorPayload(equipo.getJugadores()));
		if(equipo.getEntrenador()==null) {
			equipoPayload.setEntrenador(null);
		}else {	
			equipoPayload.setEntrenador(entrenadorService.toEntrenadorPayload(equipo.getEntrenador()));
		}
		return equipoPayload;
	}
	
	
	
	
	
	private void saveEquipo(EquipoPayload request, Equipo equipo) {
		equipo.setNombre(request.getNombre());
		equipo.setAnyoFundacion(request.getAnyoFundacion());
	
	}
	
	private Equipo toEquipo(EquipoPayload request) {
		Equipo equipo = new Equipo();
		this.saveEquipo(request, equipo);
		return equipo;
	}
	
	public List<JugadorPayload> castToListJugadorPayload(List<Jugador> request){
		List<JugadorPayload> listaJugadores = new ArrayList<>();
		for(Jugador j: request) {
			JugadorPayload jugadorPayload = new JugadorPayload();
			jugadorPayload.setId(j.getId());
			jugadorPayload.setNombre(j.getNombre());
			jugadorPayload.setApellidos(j.getApellidos());
			jugadorPayload.setEdad(j.getEdad());
			listaJugadores.add(jugadorPayload);
		}
		return listaJugadores;
	}
	public List<Jugador> castToListJugador(List<JugadorPayload> request){
		List<Jugador> listaJugadores = new ArrayList<>();
		for(JugadorPayload j: request) {
			Jugador jugador = new Jugador();
			jugador.setId(j.getId());
			jugador.setNombre(j.getNombre());
			jugador.setApellidos(j.getApellidos());
			jugador.setEdad(j.getEdad());
			jugador.setEquipo(toEquipo(j.getEquipo()));
			listaJugadores.add(jugador);
		}
		return listaJugadores;
	}


	public List<EquipoPayload> findAll() {
		return this.equipoRepository.findAll().stream().map(equipo -> this.toEquipoPayload(equipo))
				.collect(Collectors.toList());
	}

	private Equipo findById(Integer id) {
		if (id == null)
			throw ExceptionFactoryUtils.badRequestException("Id cannot be null");
		Optional<Equipo> equipoOptional = this.equipoRepository.findById(id);
		if (equipoOptional.isPresent())
			return equipoOptional.get();
		throw ExceptionFactoryUtils.resourceNotFoundException("Equipo no encontrado");
	}
	
	public EquipoPayload findEquipoById(Integer id) {
		Equipo equipo = this.findById(id);
		return this.toEquipoPayload(equipo);
	}
	
	public EquipoPayload create(EquipoPayload request) {
		if(this.equipoRepository.existsByNombre(request.getNombre()))
			throw ExceptionFactoryUtils.internalErrorException("El equipo ya existe");
		
		Equipo equipo = this.equipoRepository.save(this.toEquipo(request));
		return this.toEquipoPayload(equipo);
	}
	
	public void deleteById(Integer id) {
		Equipo equipo = this.findById(id);
		this.equipoRepository.delete(equipo);
	}
	
	public EquipoPayload update(Integer id, EquipoPayload request) {
		Equipo equipo = this.findById(id);
		this.saveEquipo(request, equipo);
		Equipo equipoResult = this.equipoRepository.save(equipo);
		return this.toEquipoPayload(equipoResult);
	}
	
	public EquipoPayload updateEntrenador(Integer idEntrenador, EquipoPayload request) {
		Entrenador entrenador = new Entrenador();
		Equipo equipo = this.findById(request.getId());
		Optional<Entrenador> entrenadorOptional = this.entrenadorRepository.findById(idEntrenador);
		if(entrenadorOptional.isPresent())
			entrenador = entrenadorOptional.get();
		equipo.setEntrenador(entrenador);
		this.saveEquipo(request, equipo);
		Equipo equipoResult = this.equipoRepository.save(equipo);
		return this.toEquipoPayload(equipoResult);
		
	}
	
	//ejercicio 3
	public List<EquipoPayload> findByNombreJugador(String nombre){
		List<EquipoPayload> lista = this.equipoRepository.findByNombreJugador(nombre).stream().map(equipo -> this.toEquipoPayload(equipo))
				.collect(Collectors.toList());
		return lista;
	}
	
	


}

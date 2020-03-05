package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.repository.EntrenadorRepository;
import talent.campus.examenRubenDiaz.repository.EquipoRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class EntrenadorService {

	@Autowired
	private EntrenadorRepository entrenadorRepository;

	public EntrenadorPayload toEntrenadorPayload(Entrenador entrenador) {
	
		EntrenadorPayload entrenadorPayload = new EntrenadorPayload();
		entrenadorPayload.setIdEntrenador(entrenador.getIdEntrenador());
		entrenadorPayload.setNombre(entrenador.getNombre());
		entrenadorPayload.setApellidos(entrenador.getApellidos());
		entrenadorPayload.setEdad(entrenador.getEdad());
	//	entrenadorPayload.setEquipoPayload(castToEquipoPayload(entrenador.getEquipo()));
		return entrenadorPayload;
	}

	private Entrenador toEntrenador(EntrenadorPayload request) {
		Entrenador entrenador = new Entrenador();
		this.saveEntrenador(request, entrenador);
		return entrenador;
	}
	
	private EquipoPayload castToEquipoPayload(Equipo equipo) {
		EquipoPayload equipoPayload = new EquipoPayload();
		equipoPayload.setId(equipo.getId());
		equipoPayload.setNombre(equipo.getNombre());
		equipoPayload.setAnyoFundacion(equipo.getAnyoFundacion());
		
		return equipoPayload;
	}

	
	public List<EntrenadorPayload> findAll() {
		return this.entrenadorRepository.findAll().stream().map(entrenador -> this.toEntrenadorPayload(entrenador))
				.collect(Collectors.toList());
	}

	private Entrenador findById(Integer id) {
		if (id == null) {
			throw ExceptionFactoryUtils.badRequestException("Id cannot be null");
		}
		Optional<Entrenador> entrenadorOptional = this.entrenadorRepository.findById(id);
		if (entrenadorOptional.isPresent())
			return entrenadorOptional.get();

		throw ExceptionFactoryUtils.resourceNotFoundException("Entrenador no encontrado");
	}

	public EntrenadorPayload findEntrenadorById(Integer id) {
		Entrenador entrenador = this.findById(id);
		return this.toEntrenadorPayload(entrenador);
	}

	public List<EntrenadorPayload> findByFullName(String nombre, String apellidos) {
		return this.entrenadorRepository.findByFullName(nombre, apellidos).stream()
				.map(entrenador -> this.toEntrenadorPayload(entrenador)).collect(Collectors.toList());
	}

	
	private void saveEntrenador(EntrenadorPayload request, Entrenador entrenador) {
		entrenador.setNombre(request.getNombre());
		entrenador.setApellidos(request.getApellidos());
		entrenador.setEdad(request.getEdad());
	}

	public EntrenadorPayload create(EntrenadorPayload request) {
		if (this.entrenadorRepository.existsByNombreAndApellidos(request.getNombre(), request.getApellidos()))
			throw ExceptionFactoryUtils.internalErrorException("El entrenador ya existe");

		Entrenador entrenador = this.entrenadorRepository.save(this.toEntrenador(request));
		return this.toEntrenadorPayload(entrenador);

	}

	public void deleteById(Integer id) {
		Entrenador entrenador = this.findById(id);
		this.entrenadorRepository.delete(entrenador);
	}
	
	public EntrenadorPayload update(Integer id, EntrenadorPayload request) {
		Entrenador entrenador = this.findById(id);
		this.saveEntrenador(request, entrenador);
		Entrenador entrenadorResult = this.entrenadorRepository.save(entrenador);
		return  this.toEntrenadorPayload(entrenadorResult);
	}
	
	
}

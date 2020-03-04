package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.repository.EquipoRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class EquipoService {

	@Autowired
	private EquipoRepository equipoRepository;

	public EquipoPayload toEquipoPayload(Equipo equipo) {
		EquipoPayload equipoPayload = new EquipoPayload();
		equipoPayload.setId(equipo.getId());
		equipoPayload.setNombre(equipo.getNombre());
		equipoPayload.setAnyoFundacion(equipo.getAnyoFundacion());
	//	equipoPayload.setEntrenador(castToEntrenadorPayload(equipo.getEntrenador()));
		return equipoPayload;

	}
	private void saveEquipo(EquipoPayload request, Equipo equipo) {
		equipo.setNombre(request.getNombre());
		equipo.setAnyoFundacion(request.getAnyoFundacion());
		//equipo.setEntrenador(request.getEntrenador());
	}
	
	private Equipo toEquipo(EquipoPayload request) {
		Equipo equipo = new Equipo();
		this.saveEquipo(request, equipo);
		return equipo;
	}

	public EntrenadorPayload castToEntrenadorPayload(Entrenador entrenador) {
		EntrenadorPayload entrenadorPayload = new EntrenadorPayload();
		entrenadorPayload.setIdEntrenador(entrenador.getIdEntrenador());
		entrenadorPayload.setNombre(entrenador.getNombre());
		entrenadorPayload.setApellidos(entrenador.getApellidos());
		entrenadorPayload.setEdad(entrenador.getEdad());
		// entrenadorPayload.setEquipoPayload(toEquipoPayload(entrenador.getEquipo()));

		return entrenadorPayload;
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

}

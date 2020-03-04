package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.repository.EquipoRepository;

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
		equipoPayload.setEntrenador(castToEntrenadorPayload(equipo.getEntrenador()));
		return equipoPayload;

	}
	
	public EntrenadorPayload castToEntrenadorPayload(Entrenador entrenador) {
		EntrenadorPayload entrenadorPayload = new EntrenadorPayload();
		entrenadorPayload.setIdEntrenador(entrenador.getIdEntrenador());
		entrenadorPayload.setNombre(entrenador.getNombre());
		entrenadorPayload.setApellidos(entrenador.getApellidos());
		entrenadorPayload.setEdad(entrenador.getEdad());
		//entrenadorPayload.setEquipoPayload(toEquipoPayload(entrenador.getEquipo()));
		
		return entrenadorPayload;
	}
	
	public List<EquipoPayload> findAll(){
		return this.equipoRepository.findAll().stream()
				.map(equipo -> this.toEquipoPayload(equipo))
				.collect(Collectors.toList());
	}
	
}

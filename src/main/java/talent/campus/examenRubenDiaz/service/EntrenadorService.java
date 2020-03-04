package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.repository.EntrenadorRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class EntrenadorService {

	@Autowired
	private EntrenadorRepository entrenadorRepository;

	private EntrenadorPayload toEntrenadorPayload(Entrenador entrenador) {
		EntrenadorPayload entrenadorPayload = new EntrenadorPayload();
		entrenadorPayload.setIdEntrenador(entrenador.getIdEntrenador());
		entrenadorPayload.setNombre(entrenador.getNombre());
		entrenadorPayload.setApellidos(entrenador.getApellidos());
		entrenadorPayload.setEdad(entrenador.getEdad());
		return entrenadorPayload;
	}
	
	public List<EntrenadorPayload> findAll(){
		return this.entrenadorRepository.findAll().stream()
				.map(entrenador -> this.toEntrenadorPayload(entrenador))
				.collect(Collectors.toList());
	}
	
	private Entrenador findById(Integer id) {
		if (id == null) {
			throw ExceptionFactoryUtils.badRequestException("Id cannot be null");
		}
		Optional<Entrenador> entrenadorOptional = this.entrenadorRepository.findById(id);
		if(entrenadorOptional.isPresent())
			return entrenadorOptional.get();
		
		throw ExceptionFactoryUtils.resourceNotFoundException("Entrenador no encontrado");
	}
	
	public EntrenadorPayload findEntrenadorById(Integer id) {
		Entrenador entrenador = this.findById(id);
		return this.toEntrenadorPayload(entrenador);
	}
}

package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.dto.PartidoPayload;
import talent.campus.examenRubenDiaz.model.Partido;
import talent.campus.examenRubenDiaz.repository.PartidoRepository;
import talent.campus.examenRubenDiaz.utils.ExceptionFactoryUtils;

@Service
@Transactional
public class PartidoService {

	@Autowired
	private PartidoRepository partidoRepository;
	@Autowired
	private EquipoService equipoService;

	public PartidoPayload toPartidoPayload(Partido partido) {
		PartidoPayload partidoPayload = new PartidoPayload();
		partidoPayload.setId(partido.getId());
		partidoPayload.setEquipoLocal(this.equipoService.toEquipoPayload(partido.getEquipoLocal()));
		partidoPayload.setEquipoVisitante(this.equipoService.toEquipoPayload(partido.getEquipoVisitante()));
		partidoPayload.setFecha(partido.getFecha());
		partidoPayload.setPuntuacionEquipoLocal(partido.getPuntuacionEquipoLocal());
		partidoPayload.setPuntuacionEquipoVisitante(partido.getPuntuacionEquipoVisitante());
		return partidoPayload;
	}

	public List<PartidoPayload> findAll() {
		return this.partidoRepository.findAll().stream().map(partido -> this.toPartidoPayload(partido))
				.collect(Collectors.toList());
	}

	private Partido findById(Integer id) {
		if (id == null)
			throw ExceptionFactoryUtils.badRequestException("Id cannot be null");
		Optional<Partido> partidoOptional = this.partidoRepository.findById(id);
		if (partidoOptional.isPresent())
			return partidoOptional.get();

		throw ExceptionFactoryUtils.resourceNotFoundException("Partido no encontrado");

	}

	public PartidoPayload findPartidoById(Integer id) {
		Partido partido = this.findById(id);
		return this.toPartidoPayload(partido);
	}

	public PartidoPayload create(PartidoPayload request) {
		saveEquiposPartido(request);
		Partido partido = this.partidoRepository.save(this.toPartido(request));
		return this.toPartidoPayload(partido);
	}

	private void savePartido(PartidoPayload request, Partido partido) {
		partido.setFecha(request.getFecha());
		partido.setEquipoLocal(this.equipoService.findById(request.getEquipoLocal().getId()));
		partido.setEquipoVisitante(this.equipoService.findById(request.getEquipoVisitante().getId()));
		partido.setPuntuacionEquipoLocal(request.getPuntuacionEquipoLocal());
		partido.setPuntuacionEquipoVisitante(request.getPuntuacionEquipoVisitante());
	}

	private Partido toPartido(PartidoPayload request) {
		Partido partido = new Partido();
		this.savePartido(request, partido);
		return partido;
	}

	public PartidoPayload update(Integer id, PartidoPayload request) {
		Partido partido = this.findById(id);
		saveEquiposPartido(request);
		this.savePartido(request, partido);
		Partido partidoResult = this.partidoRepository.save(partido);
		return this.toPartidoPayload(partidoResult);
	}

	private void saveEquiposPartido(PartidoPayload request) {
		EquipoPayload equipoLocal = this.equipoService.findEquipoById(request.getEquipoLocal().getId());
		EquipoPayload equipoVisitante = this.equipoService.findEquipoById(request.getEquipoVisitante().getId());
		request.setEquipoLocal(equipoLocal);
		request.setEquipoVisitante(equipoVisitante);
	}

}

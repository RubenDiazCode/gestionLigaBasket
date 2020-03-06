package talent.campus.examenRubenDiaz.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talent.campus.examenRubenDiaz.dto.PartidoPayload;
import talent.campus.examenRubenDiaz.model.Partido;
import talent.campus.examenRubenDiaz.repository.PartidoRepository;

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
	
	public List<PartidoPayload> findAll(){
		return this.partidoRepository.findAll().stream().map(partido -> this.toPartidoPayload(partido))
				.collect(Collectors.toList());
	}

}

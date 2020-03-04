package talent.campus.examenRubenDiaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.campus.examenRubenDiaz.dto.EquipoPayload;
import talent.campus.examenRubenDiaz.model.Equipo;


public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

Boolean existsByNombre (String nombre);
	
}

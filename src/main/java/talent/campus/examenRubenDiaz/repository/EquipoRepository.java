package talent.campus.examenRubenDiaz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import talent.campus.examenRubenDiaz.model.Equipo;
import talent.campus.examenRubenDiaz.model.Jugador;


public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

Boolean existsByNombre (String nombre);

 
	
}

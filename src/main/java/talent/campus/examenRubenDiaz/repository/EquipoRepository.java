package talent.campus.examenRubenDiaz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import talent.campus.examenRubenDiaz.model.Equipo;


public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

Boolean existsByNombre (String nombre);

@Query("SELECT e FROM Equipo e JOIN e.jugadores j WHERE j.nombre LIKE CONCAT('%', :nombre ,'%')")
List<Equipo> findByNombreJugador(@Param("nombre")String nombre);


 
	
}

package talent.campus.examenRubenDiaz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import talent.campus.examenRubenDiaz.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

	Boolean existsByNombreAndApellidos(String nombre, String apellidos);

	@Query("SELECT j FROM Jugador j JOIN j.equipo eq WHERE eq.id = :id AND j.edad > :edad")
	List<Jugador> findByEquipoAndEdad(@Param("id") Integer id, @Param("edad") Integer edad);

}

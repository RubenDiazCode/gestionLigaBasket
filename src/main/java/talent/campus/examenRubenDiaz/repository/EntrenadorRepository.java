package talent.campus.examenRubenDiaz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import talent.campus.examenRubenDiaz.model.Entrenador;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
	@Override
	Optional<Entrenador> findById(Integer id);

	@Query("SELECT e FROM Entrenador e WHERE e.nombre LIKE :nombre AND e.apellidos LIKE :apellidos")
	List<Entrenador> findByFullName(@Param("nombre") String nombre,
			@Param("apellidos") String apellidos);
}

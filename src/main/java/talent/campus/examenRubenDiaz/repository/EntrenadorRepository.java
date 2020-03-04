package talent.campus.examenRubenDiaz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.campus.examenRubenDiaz.model.Entrenador;

public interface EntrenadorRepository extends JpaRepository<Entrenador,Integer>{
@Override
Optional<Entrenador> findById(Integer id);
}

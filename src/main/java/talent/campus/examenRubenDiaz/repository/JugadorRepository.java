package talent.campus.examenRubenDiaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.campus.examenRubenDiaz.model.Jugador;

public interface JugadorRepository  extends JpaRepository<Jugador, Integer>{

Boolean existsByNombreAndApellidos (String nombre, String apellidos);

}

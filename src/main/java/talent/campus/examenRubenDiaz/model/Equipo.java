package talent.campus.examenRubenDiaz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipo", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "anyo_fundacion")
	private Integer anyoFundacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entrenador_id_entrenador")
	private Entrenador entrenador;

	@OneToMany(mappedBy = "equipo")
	private List<Jugador> jugadores = new ArrayList<>();

	@OneToMany(mappedBy = "equipoLocal")
	private List<Partido> partidosLocal = new ArrayList<>();

	@OneToMany(mappedBy = "equipoVisitante")
	private List<Partido> partidosVisitante = new ArrayList<>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAnyoFundacion() {
		return this.anyoFundacion;
	}

	public void setAnyoFundacion(Integer anyoFundacion) {
		this.anyoFundacion = anyoFundacion;
	}

	public Entrenador getEntrenador() {
		return this.entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Jugador> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Partido> getPartidosLocal() {
		return this.partidosLocal;
	}

	public void setPartidosLocal(List<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public List<Partido> getPartidosVisitante() {
		return this.partidosVisitante;
	}

	public void setPartidosVisitante(List<Partido> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}

}

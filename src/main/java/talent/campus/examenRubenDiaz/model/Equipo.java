package talent.campus.examenRubenDiaz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne
	@JoinColumn(name = "entrenador_id_entrenador")
	private Entrenador entrenador;

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

}

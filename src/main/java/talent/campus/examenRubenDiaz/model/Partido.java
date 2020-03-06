package talent.campus.examenRubenDiaz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "partido")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partido", updatable = false, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_equipo_local")
	private Equipo equipoLocal;

	@ManyToOne
	@JoinColumn(name = "id_equipo_visitante")
	private Equipo equipoVisitante;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "puntuacion_equipo_local")
	private int puntuacionEquipoLocal;

	@Column(name = "puntuacion_equipo_visitante")
	private int puntuacionEquipoVisitante;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Equipo getEquipoLocal() {
		return this.equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return this.equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacionEquipoLocal() {
		return this.puntuacionEquipoLocal;
	}

	public void setPuntuacionEquipoLocal(int puntuacionEquipoLocal) {
		this.puntuacionEquipoLocal = puntuacionEquipoLocal;
	}

	public int getPuntuacionEquipoVisitante() {
		return this.puntuacionEquipoVisitante;
	}

	public void setPuntuacionEquipoVisitante(int puntuacionEquipoVisitante) {
		this.puntuacionEquipoVisitante = puntuacionEquipoVisitante;
	}

}

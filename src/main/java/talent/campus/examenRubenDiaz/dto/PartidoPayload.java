package talent.campus.examenRubenDiaz.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PartidoPayload {
	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "equipo_local")
	private EquipoPayload equipoLocal;

	@JsonProperty(value = "equipo_visitante")
	private EquipoPayload equipoVisitante;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value = "fecha")
	private Date fecha;

	@JsonProperty(value = "puntuacion_equipo_local")
	private Integer puntuacionEquipoLocal;

	@JsonProperty(value = "puntuacion_equipo_visitante")
	private Integer puntuacionEquipoVisitante;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipoPayload getEquipoLocal() {
		return this.equipoLocal;
	}

	public void setEquipoLocal(EquipoPayload equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public EquipoPayload getEquipoVisitante() {
		return this.equipoVisitante;
	}

	public void setEquipoVisitante(EquipoPayload equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getPuntuacionEquipoLocal() {
		return this.puntuacionEquipoLocal;
	}

	public void setPuntuacionEquipoLocal(Integer puntuacionEquipoLocal) {
		this.puntuacionEquipoLocal = puntuacionEquipoLocal;
	}

	public Integer getPuntuacionEquipoVisitante() {
		return this.puntuacionEquipoVisitante;
	}

	public void setPuntuacionEquipoVisitante(Integer puntuacionEquipoVisitante) {
		this.puntuacionEquipoVisitante = puntuacionEquipoVisitante;
	}

}

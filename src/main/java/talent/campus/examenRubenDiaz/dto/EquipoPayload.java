package talent.campus.examenRubenDiaz.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EquipoPayload {

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "anyo_fundacion")
	private Integer anyoFundacion;

	@JsonProperty(value = "entrenador")
	private EntrenadorPayload entrenador;

	@JsonProperty(value = "jugadores")
	private List<JugadorPayload> jugadores;

	@JsonProperty(value = "partidos_local")
	private List<PartidoPayload> partidosLocal;

	@JsonProperty(value = "partidos_visitante")
	private List<PartidoPayload> partidosVisitante;

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

	public EntrenadorPayload getEntrenador() {
		return this.entrenador;
	}

	public void setEntrenador(EntrenadorPayload entrenador) {
		this.entrenador = entrenador;
	}

	public List<JugadorPayload> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<JugadorPayload> jugadores) {
		this.jugadores = jugadores;
	}

	public List<PartidoPayload> getPartidosLocal() {
		return this.partidosLocal;
	}

	public void setPartidosLocal(List<PartidoPayload> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public List<PartidoPayload> getPartidosVisitante() {
		return this.partidosVisitante;
	}

	public void setPartidosVisitante(List<PartidoPayload> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}

}

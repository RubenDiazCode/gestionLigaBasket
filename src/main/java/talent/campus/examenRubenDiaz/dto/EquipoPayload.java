package talent.campus.examenRubenDiaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import talent.campus.examenRubenDiaz.model.Entrenador;

public class EquipoPayload {

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "anyo_fundacion")
	private Integer anyoFundacion;
	
//	@JsonProperty(value = "entrenador")
//	private EntrenadorPayload entrenador;

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

//	public EntrenadorPayload getEntrenador() {
//		return this.entrenador;
//	}
//
//	public void setEntrenador(EntrenadorPayload entrenador) {
//		this.entrenador = entrenador;
//	}
//	
	
}

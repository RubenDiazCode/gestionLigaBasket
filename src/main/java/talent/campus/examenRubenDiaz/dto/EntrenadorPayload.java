package talent.campus.examenRubenDiaz.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntrenadorPayload {

	@JsonProperty(value = "id")
	private Integer id;

	@NotNull
	@JsonProperty(value = "nombre")
	private String nombre;

	@NotNull
	@JsonProperty(value = "apellidos")
	private String apellidos;

	@NotNull
	@JsonProperty(value = "edad")
	private Integer edad;

	public Integer getIdEntrenador() {
		return this.id;
	}

	public void setIdEntrenador(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

}

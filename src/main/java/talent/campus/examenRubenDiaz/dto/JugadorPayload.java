package talent.campus.examenRubenDiaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JugadorPayload {
	
	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "apellidos")
	private String apellidos;
	
	@JsonProperty(value = "edad")
	private Integer edad;
	
	@JsonProperty(value = "equipo")
	private EquipoPayload equipo;

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

	public EquipoPayload getEquipo() {
		return this.equipo;
	}

	public void setEquipo(EquipoPayload equipo) {
		this.equipo = equipo;
	}
	
	

}

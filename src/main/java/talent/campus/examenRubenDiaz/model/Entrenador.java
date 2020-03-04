package talent.campus.examenRubenDiaz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrenador")
public class Entrenador {

	public Entrenador() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entrenador", updatable = false, nullable = false)
	private Integer idEntrenador;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "edad")
	private Integer edad;
	
//	@OneToOne(mappedBy= "entrenador")
//	private Equipo equipo;


	public Integer getIdEntrenador() {
		return this.idEntrenador;
	}

	public void setIdEntrenador(Integer idEntrenador) {
		this.idEntrenador = idEntrenador;
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
	
//	public Equipo getEquipo() {
//		return this.equipo;
//	}
//	
//	public void setEquipo(Equipo equipo) {
//		this.equipo = equipo;
//	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre+ " - "+this.apellidos;
	}

}

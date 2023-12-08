package examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_OBJETO")
public class Objeto {

	@Id
	@SequenceGenerator(name="PRODUCTOGEN", sequenceName="PRODUCTO_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTOGEN")
	private long id;
	@Column(length = 20) 
	private String nombre;
	
	
	
	public Objeto() {
		super();
	}
	
	
	
	public Objeto(String nombre) {
		super();
		this.nombre = nombre;
	}



	public Objeto(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Objeto -> iD : " + id + ", Nombre : " + nombre;
	}
	
	
}

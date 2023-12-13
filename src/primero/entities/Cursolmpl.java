package primero.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import primero.interfaces.Curso;

public class Cursolmpl implements Curso{
	private static final Logger logger = LogManager.getLogger(Cursolmpl.class);
	
	private String nombre;
	private long codigo;
	private int cupo = 20;
	
	
	
	
	//CONSTRUCTOR
	Cursolmpl(String nombre, long codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}

	
	//METODOS DE INTERFAZ
	@Override
	public String obtenerNombre() {
		logger.info("El nombre del curso es: "+nombre);
		return nombre;
		// TODO Auto-generated method stub
		
	}

	@Override
	public long obtenerCodigo() {
		logger.info("El codigo del curso es: "+codigo);
		return codigo;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int obtenerNumeroDeCupos() {
		logger.info("El numero de cupos del curso es: "+ codigo);
		return cupo;
		// TODO Auto-generated method stub
		
	}

	
	//GETTERS y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	
	
}

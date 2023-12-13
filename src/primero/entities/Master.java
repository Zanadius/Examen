package primero.entities;

public class Master extends Cursolmpl {
	
	private boolean oficial;

	
	//CONSTRUCTOR
	public Master(String nombre, long codigo) {
		super(nombre, codigo);
		// TODO Auto-generated constructor stub
	}


	//GETTERS y SETTERS
	public boolean isOficial() {
		return oficial;
	}



	public void setOficial(boolean oficial) {
		this.oficial = oficial;
	}


	@Override
	public String toString() {
		return "Master [oficial=" + oficial + "]";
	}
	
	
}

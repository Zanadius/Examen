package examen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.DAOs.ObjetoDAO;
import examen.entities.Objeto;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Objeto.class);

	public static void main(String[] args) {

		ObjetoDAO em = new ObjetoDAO("hibernateMysql");

		Objeto test = new Objeto("test");

		em.loggerTest();


	}
}

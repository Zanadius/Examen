package primero;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.utils.Utilidades;
import primero.DAOs.MasterDAO;
import primero.entities.Master;

public class PrimeroMain {
	private static final Logger logger = LogManager.getLogger(PrimeroMain.class);
	private MasterDAO m = new MasterDAO();
	private Map<Long, Master> mapaM = new HashMap<Long, Master>();

	public static void main(String[] arg) {

		PrimeroMain main = new PrimeroMain();
		main.menu();

	}

	/**
	 * Inicia el menú
	 */
	public void menu() {
		int opcion = 0;

		do {
			opcion = Utilidades.pintarMenu(
					"1. Añadir Master.;2. Eliminar Master.;3. Mostrar Masters disponibles.;4. Salir.",
					"Elija una opción", ";");
			switch (opcion) {
			// Añadir Master
			case 1:
				m.crearVariosMaster(Utilidades.pideDatoNumerico("¿Cuantos Master desea añadir?"), mapaM);
				break;
			// Eliminar Master por codigo
			case 2:
				m.eliminarMaster(Utilidades.pideDatoLong("Introduce el Codigo del Master que deseas Eliminar"), mapaM);
				break;
			// Mostrar Master disponible
			case 3:
				m.mostrarMasters(mapaM);
				break;
			case 4:
				logger.info("Adios.");
			}
		} while (opcion != 4);
	}

}

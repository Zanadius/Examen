package primero.DAOs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.utils.Utilidades;
import primero.entities.Master;

public class MasterDAO {
	private static final Logger logger = LogManager.getLogger(MasterDAO.class);

	/**
	 * Crea un master
	 * 
	 * @return Objeto Master
	 */
	public Master crearMaster() {
		Master m = new Master(Utilidades.pideDatoTexto("Introduzca el nombre del Master:"),
				Utilidades.pideDatoLong("Introduzca el codigo del Master:"));
		logger.trace(
				"Maestro ingresado: " + m.getNombre() + " | Codigo: " + m.getCodigo() + " | Cupos: " + m.getCupo());
		return m;

	}

	/**
	 * Crea los objetos Master que necesite el usuario.
	 * 
	 * @param Cantidad de Objetos Master a añadir
	 * @param Mapa     en el que serán añadidos
	 */
	public void crearVariosMaster(int cantidad, Map<Long, Master> map) {
		for (int i = 0; i < cantidad; i++) {
			Master nuevoM = crearMaster();
			map.put(nuevoM.getCodigo(), nuevoM);
		}
	}

	public void eliminarMaster(long codigo, Map<Long, Master> map) {

		if (map.containsKey(codigo)) {
			logger.info("Has eliminado el master: " + map.get(codigo).getNombre() + " con el codigo " + codigo);
			map.remove(codigo);
		} else {
			logger.info("El Codigo del Master solicitado no se encuentra en el mapa");
		}
	}

	public void mostrarMasters(Map<Long, Master> map) {
		if (!map.isEmpty()) {
			logger.info("Estos son los Master disponibles:");
			map.forEach((key, value) -> logger.info("Codigo: " + key.longValue() + " Nombre: " + value.getNombre()));
		}else {
			logger.info("No se encontraron Masters.");
		}
	}
}

package segundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.entities.Objeto;
import primero.entities.Master;

public class SegundoMain {
	
	private static final Logger logger = LogManager.getLogger(Objeto.class);

	public static void main(String[] args) {
		
		SegundoMain main = new SegundoMain ();
		
		try {
			main.desarrollo("C:\\workspace\\ExamenCarlosBolaños\\Ficheros\\FicheroEjercicio2.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void desarrollo(String ruta) throws IOException {

		File archivo = new File(ruta);
		FileReader fileReader = new FileReader(archivo);
		BufferedReader reader = new BufferedReader(fileReader);
		Map<Integer, String> lineas = new HashMap<Integer, String>();

		int i = 0;
		String linea;
		while ((linea = reader.readLine()) != null) {
			logger.info("prebucle");
			i++;
			if (linea.contains("Java")) {
				String datos = linea.toString();
				lineas.put(i, datos);
			}
		}
		
		lineas.forEach((key, value) -> logger.info("Linea: " + key.intValue() + " Contenido: " + value.toString()));
		
		lineas.forEach((key, value) -> logger.info("Sumatoria: " + (key.intValue()+value.toString().length())));
		

	}
}

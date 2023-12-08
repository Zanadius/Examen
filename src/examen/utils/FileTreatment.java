package examen.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.curso.java.ddbb.ejercicios.biblioteca.entities.Usuario;
import examen.entities.Objeto;


public class FileTreatment {
	private static final Logger logger = LogManager.getLogger(Objeto.class);

	
	public List<Objeto> importarFichero(String ruta) throws IOException {

		File archivo = new File(ruta);
		FileReader fileReader = new FileReader(archivo);
		BufferedReader reader = new BufferedReader(fileReader);
		List<Objeto> listaObjetos = new ArrayList<Objeto>();

		String linea;
		Date fecha = null;
		reader.readLine();
		while ((linea = reader.readLine()) != null) {

			String[] datos = linea.toString().split(";");

			try {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				fecha = formato.parse(datos[2]);

				System.out.println("Fecha en formato Date: " + fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Objeto producto = new Objeto(datos[0]);
			listaObjetos.add(producto);
		}
		for (Objeto estudiante : listaObjetos) {
			logger.debug(estudiante);
		}
		logger.debug("La lista contiene " + listaObjetos.size() + " Objetos");

		return listaObjetos;
	}
	
	public void exportarListado(List<Objeto> listado, String rutaArchivo) {
		String cabecera = "ID;NOMBRE;APELLIDOS;FECHA\n";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
			writer.write(cabecera);
			for (Objeto objeto : listado) {

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				String fechaFinal = formato.format(objeto.getFechaAlta());

				StringBuilder registro = new StringBuilder(objeto.getId() + ";" + objeto.getNombre() + ";"
						 + fechaFinal + "\n");
				writer.write(registro.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

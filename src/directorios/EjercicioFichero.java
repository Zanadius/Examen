package directorios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;

import exceptions.NoResultsException;
import objetos.Alumno;
import objetos.Biblioteca;
import objetos.Direcciones;
import utils.UtilsDataBase;

public class EjercicioFichero {

	private static final Logger Logger = LogManager.getLogger(Test.class);
	private Connection conexion;

	public static void main(String[] args) {

		String dirR = "C:\\workspace\\test\\Recursos\\Ejercicio Directorios";

//		EjercicioFichero.totalJPGs(dirR);
//		EjercicioFichero.mostrarArchivosAnio(dirR);
//		EjercicioFichero.modificarNombres2024(dirR);

		EjercicioFichero main = new EjercicioFichero();

		try {
			main.leerFicheroYCrearLista("C:\\workspace\\test\\Recursos\\alumnos.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			main.insertarDireccionesDeArchivo("C:\\workspace\\test\\Recursos\\bibliotecas.txt");
		} catch (IOException | SQLException | NoResultsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 1er punto:
	public static int conteoJPGs(String directorio) {
		File dir1 = new File(directorio);
		int i = 0;
		for (File file : dir1.listFiles()) {
			if (file.isDirectory()) {
				Logger.debug("Has entrado al directorio " + file.getName());
				i += EjercicioFichero.conteoJPGs(file.getAbsolutePath());
			} else if (file.isFile()) {
				if (file.getName().endsWith(".jpg")) {
					Logger.debug(file);
					i++;
				}
			}
		}
		return i;
	}

	public static void totalJPGs(String directorio) {
		Logger.debug("Hay un total de " + EjercicioFichero.conteoJPGs(directorio));
	}

	// 2do punto:
	public static void mostrarArchivosAnio(String directorio) {
		File dir1 = new File(directorio);
		for (File file : dir1.listFiles()) {
			if (file.isDirectory()) {
				Logger.debug("Has entrado al directorio " + file.getName());
				EjercicioFichero.mostrarArchivosAnio(file.getAbsolutePath());
			} else if (file.isFile()) {
				LocalDate fecha = LocalDate.now();
				if (file.getName().contains(Integer.toString(fecha.getYear()))) {
					Logger.debug(file.getName());
				}
			}
		}
	}

	// 3er punto
	public static void modificarNombres2024(String directorio) {
		File dir1 = new File(directorio);
		for (File file : dir1.listFiles()) {
			if (file.isDirectory()) {
				Logger.debug("Has entrado al directorio " + file.getName());
				EjercicioFichero.modificarNombres2024(file.getAbsolutePath());
			} else if (file.isFile()) {
				LocalDate fecha = LocalDate.now();

				if (file.getName().contains(Integer.toString(fecha.getYear() + 1))
						&& file.getName().contains(Integer.toString(fecha.getMonthValue()))) {

					Logger.debug("Fichero a renombrar: " + file.getName());
					File rename = new File(file.getAbsolutePath() + ".done");
					if (file.renameTo(rename)) {
						Logger.debug("Se ha cambiado el nombre con exito");
					} else {
						Logger.debug("No se ha modificado el archivo");
					}
					Logger.debug("Nuevo nombre: " + file.getName());

				}
			}
		}
	}

	public List<Alumno> leerFicheroYCrearLista(String ruta) throws IOException {

		String sb = new String();
		File archivo = new File(ruta);
		FileReader fileReader = new FileReader(archivo);
		BufferedReader reader = new BufferedReader(fileReader);
		List<Alumno> listaAlumnos = new ArrayList<Alumno>();

		String linea;
		while ((linea = reader.readLine()) != null) {
			sb = linea;
			String[] datos = sb.toString().split("\\|");
			Alumno estudiante = new Alumno(datos[2], datos[0], datos[1], Double.parseDouble(datos[3]),
					Integer.parseInt(datos[4]));
			listaAlumnos.add(estudiante);
		}
		for (Alumno estudiante : listaAlumnos) {
			Logger.debug(estudiante);
		}
		Logger.debug("La lista contiene " + listaAlumnos.size() + " Alumnos");

		return listaAlumnos;
	}

	public void insertarDireccionesDeArchivo(String ruta) throws IOException, SQLException, NoResultsException {

		String sb = new String();
		File archivo = new File(ruta);
		FileReader fileReader = new FileReader(archivo);
		BufferedReader reader = new BufferedReader(fileReader);
		List<Biblioteca> listaAlumnos = new ArrayList<Biblioteca>();

		String linea;
		while ((linea = reader.readLine()) != null) {
			sb = linea;
			String[] datos = sb.toString().split("\\|");
			Logger.debug("Ingresando biblioteca: " + sb + " a la base de datos");
			
			
			String query = "INSERT " + "INTO TB_DIRECCION ((tipo_direccion,direccion,ciudad,provincia,cod_postal) "
					+ "VALUES (?,?,?,?,?)";
			Logger.debug(query);

			PreparedStatement ps = this.getConexion().prepareStatement(query);
			ps.setString(1, datos[0]);
			ps.setString(2, datos[1]);
			ps.setString(3, datos[2]);
			ps.setString(4, datos[3]);
			ps.setInt(5, Integer.parseInt(datos[4]));
			
			Direcciones newDir = new Direcciones (datos[0],datos[1],datos[2],datos[3],Integer.parseInt(datos[4]));
			
			int insertados = ps.executeUpdate();

			if (insertados == 0) {
				Logger.warn("No se ha realizado el insert de libro correctamente");
				throw new NoResultsException("No se ha insertado ningún libro");
				// throw new NullPointerException("Lo he creado yo");
			} else {
				Logger.info("Insert de libro realizado correctamente");
			}
		}
	}


	public EjercicioFichero() {
		super();
		try {
			this.conexion = UtilsDataBase.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
	}

	public Connection getConexion() {
		return conexion;
	}

}

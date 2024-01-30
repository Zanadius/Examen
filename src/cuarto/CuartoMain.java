package cuarto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import examen.utils.Utilidades;

public class CuartoMain {

	public static void main(String[] args) {

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaFinal = null; 
		
		try {
			fechaFinal = formato.parse("31-12-2023");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Contrato c1 = new Contrato("Cliente1", new Date(), fechaFinal, 1000);
		
		try {
			fechaFinal = formato.parse("31-12-2024");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Contrato c2 = new Contrato("Cliente2", new Date(), fechaFinal, 1000);
		
		try {
			fechaFinal = formato.parse("31-12-2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Contrato c3 = new Contrato("Cliente3", new Date(), fechaFinal, 1000);

		ContratoDAO dao = new ContratoDAO("hibernateMysql");

		List <Contrato> contratos = new ArrayList<Contrato>();
		
		contratos.add(c1);
		contratos.add(c2);
		contratos.add(c3);
		
		dao.insertarContratos(contratos);
		
		dao.sacarContratosBBDD();

		dao.busquedaNombre(Utilidades.pideDatoTexto("Introduce el nombre del contrato: "));

		dao.contratosImporteMayorA(Utilidades
				.pideDatoNumerico("Introduzca importe, aparecerán los contratos con un valor de importe mayor"));

	}

}

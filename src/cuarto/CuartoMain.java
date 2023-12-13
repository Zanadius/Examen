package cuarto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import examen.utils.Utilidades;

public class CuartoMain {

	public static void main(String[] args) {

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String fechaFinal = formato.format("31-12-2023");

		Contrato c1 = new Contrato("Cliente1", new Date(), new Date(), 1000);
		Contrato c2 = new Contrato("Cliente2", new Date(), new Date(), 1000);
		Contrato c3 = new Contrato("Cliente3", new Date(), new Date(), 1000);

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

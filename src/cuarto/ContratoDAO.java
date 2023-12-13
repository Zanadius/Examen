package cuarto;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ContratoDAO {
	private EntityManager em;
	
	private static final Logger logger = LogManager.getLogger(ContratoDAO.class);

	public ContratoDAO(String persitenceUnitName) {
		em = JpaUtil.getEM(persitenceUnitName);
	}
	
	public void insertarContrato (Contrato contrato) {
		 em.getTransaction().begin();
		 try {
			 em.persist(contrato);
			 
			 em.getTransaction().commit();
		 }catch(Exception e) {
			 em.getTransaction().rollback();
		 } 
	}
	
	public void insertarContratos(List<Contrato> contratos) {
		em.getTransaction().begin();
		try {

			for (Contrato producto : contratos) {
				em.persist(producto);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

	}
	
	public List<Contrato> sacarContratosBBDD() {
		List <Contrato> contratos =em.createQuery("from Objeto", Contrato.class).getResultList();
		logger.info("Contratos encontrados en la base de datos:");
		contratos.forEach(contrato -> logger.info(contratos));
		return contratos;
	}
	
	public List<Contrato> busquedaNombre (String name){
		List<Contrato> contratos;
		
		Query query = em.createQuery(
				"from Contrato c where c.nombreCliente=?1", 
				Contrato.class);
		
		
		query.setParameter(1, name);
		contratos = query.getResultList();
		
		contratos.forEach(contrato -> logger.info(contratos));
		
		return contratos;
	}
	
	public List<Contrato> contratosImporteMayorA (int importe){
		List<Contrato> contratos;
		
		Query query = em.createQuery(
				"from Contrato c where c.importe>?1", 
				Contrato.class);
		
		
		query.setParameter(1, importe);
		contratos = query.getResultList();
		
		contratos.forEach(contrato -> logger.info(contratos));
		
		return contratos;
	}
}

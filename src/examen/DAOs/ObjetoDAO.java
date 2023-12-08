package examen.DAOs;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import examen.entities.Objeto;
import examen.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ObjetoDAO {
	private EntityManager em;
	
	private static final Logger logger = LogManager.getLogger(Objeto.class);

	public ObjetoDAO(String persitenceUnitName) {
		em = JpaUtil.getEM(persitenceUnitName);
	}

	
	public void insertarUsuario (Objeto objeto) {
		 em.getTransaction().begin();
		 try {
			 em.persist(objeto);
			 
			 em.getTransaction().commit();
		 }catch(Exception e) {
			 em.getTransaction().rollback();
		 }
	     
	}
	
	public void insertarObjetos(List<Objeto> Objetos) {
		em.getTransaction().begin();
		try {

			for (Objeto producto : Objetos) {
				em.persist(producto);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

	}
	
	public List<Objeto> sacarObjetosBBDD() {
		return em.createQuery("from Objeto", Objeto.class).getResultList();
	}
	
	public List<Objeto> objetoNombre (String name){
		List<Objeto> usuarios;
		
		Query query = em.createQuery(
				"from Objeto ue where ue.nombre=?1", 
				Objeto.class);
		
		
		query.setParameter(1, name);
		usuarios = query.getResultList();
		
		return usuarios;
	}
	
	
	public void borrarUsuarioPorNombre (String nombre) {
		 em.getTransaction().begin();
		 try {
			List<Objeto> objetos = objetoNombre(nombre);
           
           for (Objeto objeto : objetos) {
           	em.remove(objeto);
			}
           
           em.getTransaction().commit();
		 }catch(Exception e) {
			 em.getTransaction().rollback();
		 }
	     
	}
	
	public void modificarUsuarioPorDni (String dni, Objeto objeto) {
		
		TypedQuery<Objeto> query = em.createQuery(
				"from UserEntity where dni=?1",
				Objeto.class);
		query.setParameter(1, dni);
		
		try {
			Objeto ob = query.getSingleResult();
			em.getTransaction().begin();
			//user.setId(userModified.getId());
			ob.setNombre(objeto.getNombre());

			em.merge(ob);
			
			em.getTransaction().commit();
		}catch (NoResultException nre) {
			System.out.println("Dni "+dni+ " no encontrado");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		
	}
	
	public List<Object[]> obtenerTotalesPorTipo() {
		logger.debug("Empezando analisis de precio medio del producto y cantidades por tipo");

		em.getTransaction().begin();

		List<Object[]> resultados = em
				.createQuery("SELECT tipo, SUM(unidades), "		
				+ "(ROUND(AVG(precio),2) " //Media
				+ "FROM Producto p GROUP BY p.tipo", Object[].class) //Agrupar
				.getResultList();

		logger.trace("Tipo | Unidades | Precio medio");
		resultados.forEach(resultado -> logger
				.trace(resultado[0].toString() + ";" + resultado[1].toString() + ";" + resultado[2].toString()));

		em.close();

		return resultados;
	}
}

package he.hibernate.basics;

import java.util.Scanner;

import he.hibernate.basics.entities.Cliente;
import he.hibernate.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id: ");
        Long id = scanner.nextLong();
        
        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

//        Cliente cliente2 = em.find(Cliente.class, id);
//        System.out.println(cliente2);

        em.close();
    }
}

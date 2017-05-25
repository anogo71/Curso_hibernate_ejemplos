package com.openwebinars.hibernate.colecciones.set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manejo de colecciones (Set)
 * www.openwebinars.net
 * @LuisMLopezMag
 */
public class App {
	public static void main(String[] args) {
		
		
		
		//Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Colecciones");

		//Generamos un EntityManager
		EntityManager em = emf.createEntityManager();

		//Iniciamos una transacción
		em.getTransaction().begin();
		
		Person person = new Person("Pepe");
		em.persist( person );
		
		person.getPhones().add("954123456");
		person.getPhones().add("600123456");
		person.getPhones().add("954123456"); //No se almacenará, puesto que está repetido
		

		//Commiteamos la transacción
		em.getTransaction().commit();
		
		//Cerramos el EntityManager
		em.close();
		
	}
}

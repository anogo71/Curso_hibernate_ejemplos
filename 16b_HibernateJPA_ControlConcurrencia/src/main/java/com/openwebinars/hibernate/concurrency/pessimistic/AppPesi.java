package com.openwebinars.hibernate.concurrency.pessimistic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import com.openwebinars.hibernate.concurrency.optimistic.AnotherUserAccount;

/**
 * Control de concurrencia (pesimista)
 * www.openwebinars.net
 * @LuisMLopezMag
 */
public class AppPesi {

	static EntityManagerFactory emf;

	static EntityManager em;

	public static void main(String[] args) {

		// Configuramos el EMF a través de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Concurrencia");

		// Generamos un EntityManager
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId(1);
		userAccount.setBalance(600);
		userAccount.setName("Luismi");
		
		em.persist(userAccount);
		
		em.getTransaction().commit();
				
		cambioDeSaldoEnHilo(700);
		cambioDeSaldoEnHilo(800);
		
		
		// Cerramos el EntityManager
		//em.close();
		//emf.close();

	}
	
	public static void cambioDeSaldoEnHilo(final double nuevoBalance) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				em.getTransaction().begin();

				UserAccount userAccount = em.find(UserAccount.class, 1, LockModeType.PESSIMISTIC_READ);
				//UserAccount  userAccount = em.find(UserAccount.class, 1);
				userAccount.setBalance(nuevoBalance);
				em.persist(userAccount);
				em.flush();
				em.refresh(userAccount);
				em.getTransaction().commit();
				
				System.out.println(userAccount);
			}
			
			
		}).start();
	}


}

package com.openwebinars.hibernate.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.openwebinars.hibernate.consultas.model.Customer;
import com.openwebinars.hibernate.consultas.model.Employee;

/**
 * Consultas (JPQL, con nombre, nativas, ....)
 * www.openwebinars.net
 * @LuisMLopezMag
 */
public class AppJoin 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Consultas");
        
        EntityManager em = emf.createEntityManager();
        

        //Query query = em.createQuery("select c FROM Customer c");

//        Query query = em.createQuery(
//        		  "select cus "
//        		+ "from Customer cus "
//        		+ "left join fetch cus.orders");
        
//        Query query = em.createQuery(
//        		  "select c "
//        		+ "from Customer c "
//        		+ "left join fetch c.employee ");

        TypedQuery<Customer> query = em.createQuery(
        		"select c "
        		+ "from Customer c "
        		+ "where c.employee = :employee", Customer.class);
       
        query.setParameter("employee", em.find(Employee.class, 1370));
        
        
        
        List<Customer> listCustomer = query.getResultList();
        for(Customer c : listCustomer) {
        	System.out.print(c.getCustomerName() + " (");
        	System.out.print(c.getContactFirstName() + " " + c.getContactLastName()+ ") (");
        	System.out.println(c.getCity() + ", " + c.getCountry() + ")");
        }
        
        
        em.close();
        emf.close();
        
    }
}

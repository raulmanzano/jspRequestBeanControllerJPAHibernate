package es.manzano.ejemplo.jspjdbc.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.manzano.ejemplo.jspjdbc.model.Primaria;

public class BusinessDelegate {	
	private static final Logger logger = LoggerFactory.getLogger(BusinessDelegate.class);
	
	  private static BusinessDelegate single_instance = null;  
	  public static BusinessDelegate getInstance() 
	    { 
	        if (single_instance == null) 
	            single_instance = new BusinessDelegate(); 	  
	        return single_instance; 
	    }
	  

		private EntityManagerFactory emFactoryObj;
		private EntityManager entityMgr;

		
	//privado para que no se pueda instanciar
	  private BusinessDelegate(){			
			this.emFactoryObj = Persistence.createEntityManagerFactory("pu");
			this.entityMgr = emFactoryObj.createEntityManager();			
	    }
	  

	  
	  
	  /*Operciones basicas*/
	public List<Primaria> getAllPrimaria() {
		  Query query = entityMgr.createNamedQuery("Primaria.findAll");
		  List results = query.getResultList();		
		return results;
	}
	
	public void insertarPrimaria(Primaria parametro) {
	    entityMgr.getTransaction().begin();
	    entityMgr.persist(parametro);
	    entityMgr.getTransaction().commit();
	    entityMgr.clear();
	}

	public Primaria getById(Integer parameter) {
		return entityMgr.find(Primaria.class, parameter);
	}

	public void delete(Integer parameter) {
	    entityMgr.getTransaction().begin();
	    Primaria primaria = entityMgr.find(Primaria.class, parameter);
	    entityMgr.remove(primaria);
		entityMgr.getTransaction().commit();
	    entityMgr.clear();
	}

	public void updatePrimaria(Primaria primaria) {
	    entityMgr.getTransaction().begin();
	    entityMgr.persist(primaria);
	    entityMgr.getTransaction().commit();
	    entityMgr.clear();
	}

	
    	
}

package miranda.cordeiro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	/* Essa classe é criada para manipulação do Hibernate
	 * e do banco de dados.
	 */

	public static EntityManagerFactory factory = null ;
	
	static {
		init ();
	}
	
	
	private static void init () {
		
		try {
		  if (factory == null) {
			  factory = Persistence.createEntityManagerFactory("jpa-hibernate-jdev");
		  }	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	//Esse método provê a conexão com banco através do HIBERNATE
	public static EntityManager getEntityManager() {
		return factory.createEntityManager() ;
	}
	
	//Método responsável por retornar a chave primária da entidade passada
	//como parametro.
	public static Object getPrimaryKey (Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}
	
	
}

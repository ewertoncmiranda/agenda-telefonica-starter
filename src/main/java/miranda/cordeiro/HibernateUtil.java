package miranda.cordeiro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	/* Essa classe � criada para manipula��o do Hibernate
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
	
	//Esse m�todo prov� a conex�o com banco atrav�s do HIBERNATE
	public static EntityManager getEntityManager() {
		return factory.createEntityManager() ;
	}
	
	//M�todo respons�vel por retornar a chave prim�ria da entidade passada
	//como parametro.
	public static Object getPrimaryKey (Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}
	
	
}

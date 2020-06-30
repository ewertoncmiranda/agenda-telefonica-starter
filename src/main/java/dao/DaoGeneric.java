package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import miranda.cordeiro.HibernateUtil;

public class DaoGeneric<T> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void salvar(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	/*
	 * Caso n�o exista no banco , salva. Se n�o,ele Atualiza. Esse m�todo retorna
	 * uma entidade.POR�M, para atualizar um registro � necess�rio primeiro BUSCAR o
	 * registro no banco, para depois atualizar.
	 */
	public T atualizarMerge(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		T entidadeNova = entityManager.merge(entidade);
		transaction.commit();
		return entidadeNova;
	}

	/*
	 * M�todo respons�vel por pesquisar um objeto no banco. Primeiro,� pesquisado o
	 * chave primaria ,que por sua vez � armazenado em id.O m�todo find recebe a
	 * entidade passada como parametro com id, retornando um objeto da entidade
	 */
	public T pesquisar(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		T t = (T) entityManager.find(entidade.getClass(), id);
		return t;
	}

	public T pesquisar_Dois(Long id, Class<T> entidade) {
		T t = (T) entityManager.find(entidade, id);
		return t;
	}

	/*
	 * M�todo respons�vel por excluir um registro na base de dados. note que foi
	 * usada SQL nativo , para fins de evitar erros e conflitos.
	 */
	public void deletarPorId(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);// Busca a chave primaria

		EntityTransaction transaction = entityManager.getTransaction();// Instancia o objeto de transa��o
		transaction.begin();
		String sql = "delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id =" + id; // atribui
																												// a sql
																												// a
																												// string
		entityManager.createNativeQuery(sql).executeUpdate(); // permite a cria��o de sql nativa de forma direta
		transaction.commit(); // executa as defini��es anteriores
	}

	public List<T> listar(Class<T> entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<T> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		transaction.commit();
		return lista;

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}

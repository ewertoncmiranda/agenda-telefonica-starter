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
	 * Caso não exista no banco , salva. Se não,ele Atualiza. Esse método retorna
	 * uma entidade.PORÉM, para atualizar um registro é necessário primeiro BUSCAR o
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
	 * Método responsável por pesquisar um objeto no banco. Primeiro,é pesquisado o
	 * chave primaria ,que por sua vez é armazenado em id.O método find recebe a
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
	 * Método responsável por excluir um registro na base de dados. note que foi
	 * usada SQL nativo , para fins de evitar erros e conflitos.
	 */
	public void deletarPorId(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);// Busca a chave primaria

		EntityTransaction transaction = entityManager.getTransaction();// Instancia o objeto de transação
		transaction.begin();
		String sql = "delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id =" + id; // atribui
																												// a sql
																												// a
																												// string
		entityManager.createNativeQuery(sql).executeUpdate(); // permite a criação de sql nativa de forma direta
		transaction.commit(); // executa as definições anteriores
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

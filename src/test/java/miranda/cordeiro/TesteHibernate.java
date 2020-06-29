package miranda.cordeiro;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.Telefone;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa user = new UsuarioPessoa();

		user.setEmail("chulapa");
		user.setLogin("lapachu");
		user.setSobrenome("picachu");
		user.setNome("ash");
		user.setSenha("123");
		user.setIdade(25L);

		daoGeneric.salvar(user);

	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa user = new UsuarioPessoa();
		user.setId(3L);

		user = daoGeneric.pesquisar(user);
		System.out.println("----------------->>");
		System.out.println(user.toString());
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa user = daoGeneric.pesquisar_Dois(3L, UsuarioPessoa.class);
		System.out.println("----------> " + user.toString());
	}

	@Test
	public void testarUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa user = daoGeneric.pesquisar_Dois(3L, UsuarioPessoa.class);
		user.setSobrenome("Batatais");
		user.setNome("Novo Nome");
		user.setLogin("Logante");

		UsuarioPessoa userRetorno = daoGeneric.atualizarMerge(user);
		System.out.println("Usuario Atualizado --> " + userRetorno.toString());

	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = dao.pesquisar_Dois(5L, UsuarioPessoa.class);
		dao.deletarPorId(pessoa);
	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = dao.listar(UsuarioPessoa.class);

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
			System.out.println("---------------------------------------");
		}
	}

	@Test
	public void testQueryList() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa where login ='Logante'")
				.getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	@Test
	public void testQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa order by id")
				.setMaxResults(3).getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	@Test
	public void testQueryListParameter() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa where login = :login ")
				.setParameter("login","poxa").getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	
	@Test
	public void testQuerySomarIdade() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) 
			dao.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		System.out.println("O RESULTADO É ---> " +somaIdade);
		
	}
	
	@Test
	public void namedQuery1() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		List <UsuarioPessoa> lista = dao.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();
		
		for (UsuarioPessoa pessoa : lista) {
			System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
			System.out.println(pessoa);
		}
		
	}
	
	@Test
	public void namedQuery2() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		List <UsuarioPessoa> lista = dao.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "ash")
				.getResultList();
		
		for (UsuarioPessoa pessoa : lista) {
			System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
			System.out.println(pessoa);
		}
	}
	
	@Test	
	public void testeGravaTelefone() {
		DaoGeneric dao = new DaoGeneric() ;
		
		UsuarioPessoa user = (UsuarioPessoa) dao.pesquisar_Dois(2L, UsuarioPessoa.class);
		
		Telefone tel = new Telefone() ;
		tel.setNumero("1247895419");
		tel.setTipo("Celular");
		tel.setPessoa(user);
		
		dao.salvar(tel);
 	}
	
	@Test	
	public void testeConsultaTelefone() {
		DaoGeneric dao = new DaoGeneric() ;
		
		UsuarioPessoa user = (UsuarioPessoa) dao.pesquisar_Dois(3L, UsuarioPessoa.class);
		
		for(Telefone fone : user.getListaTelefones()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getPessoa());
			System.out.println("--------------------------------------");
			
		}
		
	}
	
}

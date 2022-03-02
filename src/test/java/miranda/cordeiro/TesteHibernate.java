package miranda.cordeiro;

import java.util.List;

import org.junit.Test;

import miranda.agendatelefonica.dao.GenericEntityManager;
import miranda.agendatelefonica.utils.Menu;
import miranda.agendatelefonica.model.Telefone;
import miranda.agendatelefonica.model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testaFront() {
		Menu menu = new Menu();
		menu.alterarUsuario();
	}
	
	
	@Test
	public void testeHibernateUtil() {
		GenericEntityManager<UsuarioPessoa> genericEntityManager = new GenericEntityManager<UsuarioPessoa>();

		UsuarioPessoa user = new UsuarioPessoa();

		user.setEmail("chulapa");
		user.setLogin("lapachu");
		user.setSobrenome("picachu");
		user.setNome("ash");
		user.setSenha("123");
		user.setIdade(25L);

		genericEntityManager.salvar(user);

	}

	@Test
	public void testeBuscar() {
		GenericEntityManager<UsuarioPessoa> genericEntityManager = new GenericEntityManager<UsuarioPessoa>();

		UsuarioPessoa user = new UsuarioPessoa();
		user.setId(3L);

		user = genericEntityManager.pesquisar(user);
		System.out.println("----------------->>");
		System.out.println(user.toString());
	}

	@Test
	public void testeBuscar2() {
		GenericEntityManager<UsuarioPessoa> genericEntityManager = new GenericEntityManager<UsuarioPessoa>();
		UsuarioPessoa user = genericEntityManager.pesquisar_Dois(3L, UsuarioPessoa.class);
		System.out.println("----------> " + user.toString());
	}

	@Test
	public void testarUpdate() {
		GenericEntityManager<UsuarioPessoa> genericEntityManager = new GenericEntityManager<UsuarioPessoa>();
		UsuarioPessoa user = genericEntityManager.pesquisar_Dois(3L, UsuarioPessoa.class);
		user.setSobrenome("Batatais");
		user.setNome("Novo Nome");
		user.setLogin("Logante");

		UsuarioPessoa userRetorno = genericEntityManager.atualizarMerge(user);
		System.out.println("Usuario Atualizado --> " + userRetorno.toString());

	}

	@Test
	public void testeDelete() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		UsuarioPessoa pessoa = dao.pesquisar_Dois(5L, UsuarioPessoa.class);
		dao.deletarPorId(pessoa);
	}

	@Test
	public void testeConsultar() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		List<UsuarioPessoa> lista = dao.listar(UsuarioPessoa.class);

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
			System.out.println("---------------------------------------");
		}
	}

	@Test
	public void testQueryList() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa where login ='Logante'")
				.getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	@Test
	public void testQueryListMaxResult() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa order by id")
				.setMaxResults(3).getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	@Test
	public void testQueryListParameter() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();

		List<UsuarioPessoa> lista = dao.getEntityManager().createQuery("from UsuarioPessoa where login = :login ")
				.setParameter("login","poxa").getResultList();

		for (UsuarioPessoa pessoa : lista) {
			System.out.println(pessoa);
		}
	}

	
	@Test
	public void testQuerySomarIdade() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		Long somaIdade = (Long) 
			dao.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		System.out.println("O RESULTADO É ---> " +somaIdade);
		
	}
	
	@Test
	public void namedQuery1() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
		List <UsuarioPessoa> lista = dao.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();
		
		for (UsuarioPessoa pessoa : lista) {
			System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
			System.out.println(pessoa);
		}
		
	}
	
	@Test
	public void namedQuery2() {
		GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
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
		GenericEntityManager dao = new GenericEntityManager() ;
		
		UsuarioPessoa user = (UsuarioPessoa) dao.pesquisar_Dois(2L, UsuarioPessoa.class);
		
		Telefone tel = new Telefone() ;
		tel.setNumero("1247895419");
		tel.setTipo("Celular");
		tel.setPessoa(user);
		
		dao.salvar(tel);
 	}
	
	@Test	
	public void testeConsultaTelefone() {
		GenericEntityManager dao = new GenericEntityManager() ;
		
		UsuarioPessoa user = (UsuarioPessoa) dao.pesquisar_Dois(3L, UsuarioPessoa.class);
		
		for(Telefone fone : user.getListaTelefones()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getPessoa());
			System.out.println("--------------------------------------");
			
		}
		
	}
	
}

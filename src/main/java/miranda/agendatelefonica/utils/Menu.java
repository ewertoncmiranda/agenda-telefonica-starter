package miranda.agendatelefonica.utils;

import java.util.List;
import java.util.Scanner;

import miranda.agendatelefonica.dao.GenericEntityManager;
import miranda.agendatelefonica.model.Telefone;
import miranda.agendatelefonica.model.UsuarioPessoa;

public class Menu {

	private Scanner poc = new Scanner(System.in);
	private GenericEntityManager<UsuarioPessoa> dao = new GenericEntityManager<UsuarioPessoa>();
	private String dialogo;
	private UsuarioPessoa user = new UsuarioPessoa();

	public void salvarUsuarioPessoa() {

		System.out.println("Insira o nome do Usuario:\n");
		String nome = poc.nextLine();
		user.setNome(nome);

		System.out.println("Insira o SobreNome:\n");
		nome = poc.nextLine();
		user.setSobrenome(nome);

		System.out.println("Insira o Email:\n");
		nome = poc.nextLine();
		user.setEmail(nome);

		System.out.println("Insira Login:\n");
		nome = poc.nextLine();
		user.setLogin(nome);

		System.out.println("Insira Senha:\n");
		nome = poc.nextLine();
		user.setSenha(nome);

		dao.salvar(user);

		user = dao.pesquisar(user);

		GenericEntityManager<Telefone> daoTel = new GenericEntityManager<Telefone>();
		Telefone tel = new Telefone();

		System.out.println("Insira o Telefone");
		nome = poc.nextLine();
		tel.setNumero(nome);

		System.out.println("Qual tipo de telefone?");
		nome = poc.nextLine();
		tel.setTipo(nome);
		tel.setPessoa(user);

		daoTel.salvar(tel);

		System.out.println("Salvo com sucesso!");
	}

	public void listarUsuarios() {
		List<UsuarioPessoa> lista = dao.listar(UsuarioPessoa.class);
		for (UsuarioPessoa pessoa : lista) {
			System.out.println("->" + pessoa + "\n");
			System.out.println("------------------------------------------------------");
		}

	}

	public void alterarUsuario() {
		this.listarUsuarios();

		System.out.println("*****************ALTERAR USUÁRIO*****************\n");
		System.out.println("ESCOLHA O ID DO USUARIO A ALTERAR\n");

		Long select = poc.nextLong();
		System.out.println(dao.pesquisar_Dois(select, UsuarioPessoa.class));

		System.out.println("INSIRA O NOME ALTERADO\n");
		dialogo = poc.nextLine();
		user.setNome(dialogo);

		System.out.println("INSIRA O SOBRENOME A ALTERAR\n");
		dialogo = poc.nextLine();
		user.setSobrenome(dialogo);

		System.out.println("INSIRA O LOGIN A ALTERAR\n");
		dialogo = poc.nextLine();
		user.setLogin(dialogo);

		System.out.println("INSIRA O EMAIL A ALTERAR\n");
		dialogo = poc.nextLine();
		user.setEmail(dialogo);
		// Insira o Id do Usuario a alterar!
		user.setId(select);

		dao.atualizarMerge(user);
		System.out.println(dao.pesquisar_Dois(select, UsuarioPessoa.class));
		System.out.println("Usuario Alterado com Sucesso!");

	}
	
	public void excluirUsuario() {
		this.listarUsuarios();
		System.out.println("ESCOLHA O ID DO USUARIO A EXCLUIR\n");

		Long select = poc.nextLong();
		user = dao.pesquisar_Dois(select, UsuarioPessoa.class) ;
		System.out.println(user);
		
		dao.deletarPorId(user);
		this.listarUsuarios();
		System.out.println("Usuario do ID "+select+" excluido com sucesso!!");
		
	}

}

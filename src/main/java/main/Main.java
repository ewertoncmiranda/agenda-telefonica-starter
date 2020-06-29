package main;

import java.util.Scanner;

import dao.DaoGeneric;
import model.Telefone;
import model.UsuarioPessoa;

public class Main {

	public static void main(String[] args) {
	
		//Criar um menu com as opções de CRUD
		int control = -1 ;
		
		do {
			Scanner in = new Scanner(System.in); 
			Scanner poc = new Scanner(System.in);
		   	
			System.out.println("************CRUD DE USUARIO************");
			System.out.println("Selecione a opção desejada:\n");
			System.out.println(" 1-Adicionar Usuario\n 2-Alterar Usuario\n 3-Listar todos os Usuarios \n 4-Excluir Usuario\n 0-Fechar Aplicação");
			
			control= in.nextInt();
			
			switch (control) {
			
			case 1:
				DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
				UsuarioPessoa user = new UsuarioPessoa();
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
				
				DaoGeneric<Telefone> daoTel = new DaoGeneric<Telefone>() ;
				Telefone tel = new Telefone();
				
				System.out.println("Insira o Telefone");
				nome = poc.nextLine() ;
				tel.setNumero(nome);
				
				System.out.println("Qual tipo de telefone?");
				nome = poc.nextLine() ;
				tel.setTipo(nome);
				tel.setPessoa(user);
							
				daoTel.salvar(tel);
				
			break;

			default:
				break;
			}
			
			
			
			
			
		}while(control == 0);
		
		
		

	}

}

package main;

import java.util.Scanner;

import dao.DaoGeneric;
import model.Telefone;
import model.UsuarioPessoa;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		
		//Criar um menu com as opções de CRUD
		int control = -1 ;
		
		while (control != 0) {
			Scanner in = new Scanner(System.in); 
			
		   	
			System.out.println("************CRUD DE USUARIO************");
			System.out.println("Selecione a opção desejada:\n");
			System.out.println(" 1-Adicionar Usuario\n 2-Alterar Usuario\n 3-Listar todos os Usuarios \n 4-Excluir Usuario\n 0-Fechar Aplicação");
			
			control= in.nextInt();
			
			switch (control) {
			
			case 1:
				menu.salvarUsuarioPessoa();				
			break;
			
			case 2 :
				menu.alterarUsuario();
			break;
			
			case 3:
				menu.listarUsuarios();
			break;
			
			case 4:
				menu.excluirUsuario();
			break ;
			
			default:
				break;
			}//fim switch
						
			
		}//while
		
		

	}//mains

}

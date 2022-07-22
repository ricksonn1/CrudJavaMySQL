package br.com.pessoa.fisica.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestePessoaFisica {

	public static void main(String[] args) throws SQLException {

		menu();

	}

	public static void menu() throws SQLException {

		PessoaFisica pessoa = new PessoaFisica();
		Connection conexao = pessoa.recuperarConexao();

		int opcao = 1;

		while (opcao > 0) {
			Scanner input = new Scanner(System.in);
			System.out.println(
					"1: Cadastrar. " + "\n2: Listar pessoas. " + "\n3: Deletar. " + "\n4: Atualizar cadastro.");
			opcao = input.nextInt();

			switch (opcao) {

			case 1:
				cadastrar(conexao);

				break;

			case 2:
				listar(conexao);

				break;

			case 3:
				deletar(conexao);
				break;
			case 4:
				atualizar(conexao);
				break;
			}

		}
		System.out.println("Sistema fechando!...");

	}

	public static void cadastrar(Connection conexao) throws SQLException {

		Scanner input = new Scanner(System.in);

		System.out.println("Digite o nome: ");
		String nome = input.next();
		System.out.println("Digite a idade: ");
		String idade = input.next();
		System.out.println("Digite o CPF: ");
		String cpf = input.next();
		System.out.println("Digite a data de nascimento: ");
		String dataDeNascimento = input.next();
		System.out.println("Digite o telefone: ");
		String telefone = input.next();

		PreparedStatement stm = conexao.prepareStatement(
				"INSERT INTO PESSOAFISICA1 (nome, idade, cpf, datadenascimento, telefone) VALUES(?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		stm.setString(1, nome);
		stm.setString(2, idade);
		stm.setString(3, cpf);
		stm.setString(4, dataDeNascimento);
		stm.setString(5, telefone);
		stm.execute();
		stm.close();
		ResultSet rst = stm.getGeneratedKeys();

		while (rst.next()) {
			int id = rst.getInt(1);
			System.out.println("Pessoa Fisica cadastrada com sucesso! O id dela: " + id);

		}

	}

	public static void listar(Connection conexao) throws SQLException {

		PreparedStatement stm = conexao
				.prepareStatement("SELECT ID, NOME, IDADE, CPF, DATADENASCIMENTO, TELEFONE FROM PESSOAFISICA1");
		stm.execute();

		ResultSet rst = stm.getResultSet();

		while (rst.next()) {
			String id = rst.getString("ID");
			System.out.println("ID: " + id);
			String nome = rst.getString("NOME");
			System.out.println("NOME: " + nome);
			String idade = rst.getString("IDADE");
			System.out.println("IDADE: " + idade);
			String cpf = rst.getString("CPF");
			System.out.println("CPF: " + cpf);
			String dataDeNascimento = rst.getString("DATADENASCIMENTO");
			System.out.println("DATA DE NASCIMENTO: " + dataDeNascimento);
			String telefone = rst.getString("TELEFONE");
			System.out.println("TELEFONE: " + telefone);

		}
	}

	public static void deletar(Connection conexao) throws SQLException {

		Scanner input = new Scanner(System.in);
		System.out.println("Digite o id da pessoa que deseja deletar: ");
		int id = input.nextInt();

		PreparedStatement stm = conexao.prepareStatement("DELETE FROM PESSOAFISICA1 WHERE ID= ?");

		stm.setInt(1, id);
		stm.execute();

		System.out.println("Pessoa deletada com sucesso!");

	}

	public static void atualizar(Connection conexao) throws SQLException {

		Scanner input = new Scanner(System.in);

		System.out.println("Digite o id da pessoa que deseja atualizar: ");
		int id = input.nextInt();

		System.out.println("Digite o novo nome: ");
		String nome = input.next();

		System.out.println("Digite a nova idade: ");
		String idade = input.next();

		System.out.println("Digite o novo cpf: ");
		String cpf = input.next();

		System.out.println("Digite a nova data de nascimento: ");
		String dataDeNascimento = input.next();

		System.out.println("Digite o novo telefone: ");
		String telefone = input.next();

		PreparedStatement stm = conexao.prepareStatement(
				"UPDATE PESSOAFISICA1 SET NOME = ?, IDADE = ?, CPF = ?, DATADENASCIMENTO = ?, TELEFONE = ? WHERE ID=?");

		stm.setInt(6, id);
		stm.setString(1, nome);
		stm.setString(2, idade);
		stm.setString(3, cpf);
		stm.setString(4, dataDeNascimento);
		stm.setString(5, telefone);
		stm.execute();

		System.out.println("Dados atualizados com sucesso!");

	}
}

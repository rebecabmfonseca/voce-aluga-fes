package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa
{
	//nome, cpf, dataNasc, endereco, telefone e email herdados de Pessoa
	String CNH;

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc,
			int mesNasc, int anoNasc, String endereco, String cidade, int numero, String CEP)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, endereco, cidade, numero, CEP);
		this.CNH = CNH;
	}

	public Cliente(String CPF, String nome, String CNH, String telefone, String email, int diaNasc, int mesNasc, Integer anoNasc,
				   String rua, String cidade, int numero, String CEP, Integer complemento)
	{
		super(CPF, nome, telefone, email, diaNasc, mesNasc, anoNasc, rua, cidade, numero, CEP, complemento);
		this.CNH = CNH;
	}
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void getCliente(String CPF) {
		Connection connection = null;
		PreparedStatement statement;
		List<Cliente> users = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT Nome, CNH FROM Cliente WHERE CPF=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, CPF); // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Cliente user = new Cliente();
				System.out.println(resultSet.getString(1));
				user.setNome(resultSet.getString(1));
				user.setCNH(resultSet.getString(2));
				users.add(user);
			}
			System.out.println(users);
		} catch (SQLException exception) {
		} 
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void saveClient(Cliente c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Database.getDBConnection();
			String query = "insert into Cliente (Nome, Telefone, Endereco_1, Endereco_2, Data_Nasc, Lista_Negra, CNH, CPF) values (?, ?, ?, ?, ?, ?, ?, ?)\n";
			statement = connection.prepareStatement(query);
			statement.setString(1, c.getNome()); // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo
			statement.setString(2, c.getTelefone());
			statement.setString(3, c.getEndereco());
			statement.setString(4, c.getCep());
			statement.setString(5, c.getDataNasc());
			statement.setString(6, "0");
			statement.setString(7, c.getCNH());
			statement.setString(8, c.getCPF());
			statement.executeUpdate();
			connection.commit();
			resultSet = statement.getGeneratedKeys();

		} catch (SQLException exception) {
//			if (null != connection) {
//				connection.rollback();
//			}
			exception.printStackTrace();
		} finally {
			if (null != resultSet) {
				resultSet.close();
			}

			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}

	}
	
	public static void removeCliente(String CPF) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.getDBConnection();
			String query = "DELETE FROM Cliente WHERE CPF=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, CPF);

			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {

			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}
	}
	
	
	public void setCNH(String CNH) {
		this.CNH = CNH;
	}
	
	public String getCNH() {
		return this.CNH;
	}
	
//	public void setDataNasc(String dataNasc) {
//		this.dataNasc = dataNasc;
//	}

	
	public String toString() {
		return "Nome: " + this.nome + "\nCPF: " + this.CPF + "\nCNH: " + this.CNH
				+ "\nEndereco: " + this.endereco + ", " + this.numero + ", " +
				this.cidade + "\nCEP: " + this.CEP + "\nTelefone: " + this.telefone +
				"\nData de Nascimento: " + this.diaNasc + "/" + this.mesNasc + "/" + this.anoNasc;
	}
}


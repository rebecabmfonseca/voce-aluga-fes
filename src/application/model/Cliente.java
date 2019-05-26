package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	public static List<Cliente> getAll(){
		Connection connection = null;
		PreparedStatement statement;
		List<Cliente> users = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Cliente";
			statement = connection.prepareStatement(query);
	 // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cliente user = new Cliente();
				user.setNome(rs.getString("Nome"));
				user.setTelefone(rs.getString("Telefone"));
				user.setEndereco(rs.getString("Endereco"));
				user.setCEP(rs.getString("CEP"));
				user.setCNH(rs.getString("CNH"));
				String data = rs.getString("Data_Nasc");
				Date date = new Date(data);
				Calendar cal = Calendar.getInstance();
		    	cal.setTime(date);
				user.setDataNasc(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
				user.setCPF(rs.getString("CPF"));
				user.setEmail(rs.getString("Email"));
				users.add(user);

				}
		} catch (SQLException exception) {
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}

	public static Cliente getCliente(String CPF) {
		Connection connection = null;
		PreparedStatement statement;
		Cliente user = new Cliente();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Cliente WHERE CPF=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, CPF); // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				user.setNome(rs.getString("Nome"));
				user.setTelefone(rs.getString("Telefone"));
				user.setEndereco(rs.getString("Endereco"));
				user.setCEP(rs.getString("CEP"));
				user.setCNH(rs.getString("CNH"));
				String data = rs.getString("Data_Nasc");
				Date date = new Date(data);
				Calendar cal = Calendar.getInstance();
		    	cal.setTime(date);
				user.setDataNasc(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
				user.setCPF(rs.getString("CPF"));
				user.setEmail(rs.getString("Email"));
				return user;
			}


		} catch (SQLException exception) {

		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void updateClient(Cliente c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Database.getDBConnection();
			String query = "update Cliente set Nome=?, Telefone=?, Endereco=?, CEP=?, Data_Nasc=?, Email=? where CNH=?";
			statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, c.getNome());
			// O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo
			statement.setString(2, c.getTelefone());
			statement.setString(3, c.getEndereco());
			statement.setString(4, c.getCep());
			statement.setString(5, c.getDataNasc());
			statement.setString(6,c.getEmail());
			statement.setString(7, c.getCNH());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			System.out.println("atualizou");

		} catch (SQLException exception) {
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

	public static void saveClient(Cliente c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Database.getDBConnection();
			String query = "insert into Cliente (Nome, Telefone, Endereco, CEP, Data_Nasc, Lista_Negra, CNH, CPF, Email) values (?, ?, ?, ?, ?, ?, ?, ?,?)\n";
			statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, c.getNome());
			// O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo
			statement.setString(2, c.getTelefone());
			statement.setString(3, c.getEndereco());
			statement.setString(4, c.getCep());
			statement.setString(5, c.getDataNasc());
			statement.setString(6, "0");
			statement.setString(7, c.getCNH());
			statement.setString(8, c.getCPF());
			statement.setString(9,c.getEmail());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			System.out.println("salvou");

		} catch (SQLException exception) {
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



	public String toString() {
		return "Nome: " + this.nome + "\nCPF: " + this.CPF + "\nCNH: " + this.CNH
				+ "\nEndereco: " + this.endereco + ", " + this.numero + ", " +
				this.cidade + "\nCEP: " + this.CEP + "\nTelefone: " + this.telefone +
				"\nData de Nascimento: " + this.diaNasc + "/" + this.mesNasc + "/" + this.anoNasc;
	}


}


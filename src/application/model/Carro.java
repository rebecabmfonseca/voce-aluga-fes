package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Carro {
	String placa;
	long quilometragem;
	String modelo;
	String marca;
	String cor;
	int ano;
	
	public Carro(String placa, long quilometragem, String modelo, String marca, String cor, int ano) {
		this.placa = placa;
		this.quilometragem = quilometragem;
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.ano = ano;
	}
	
	public String getPlaca() {
		return placa;
	}

	public long getQuilometragem() {
		return quilometragem;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public String getCor() {
		return cor;
	}

	public int getAno() {
		return ano;
	}

	public String toString() {
		return "Modelo: " + this.modelo + "\nAno: " + this.ano + "\nCor: " + this.cor + "\nPlaca: " + this.placa +
				"\nMarca: " + this.marca + "\nQuilometragem: " + this.quilometragem;
	}
	
	public static void removeCarro(String placa) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.getDBConnection();
			String query = "DELETE FROM Carro WHERE Placa=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, placa);

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

	
	public static Carro getCarro(String placa) {
		Connection connection = null;
		PreparedStatement statement;
		Carro carro = null;

		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Carro WHERE Placa=?";
			statement = connection.prepareStatement(query);
			System.out.println("placa = " + placa);
			statement.setString(1, placa);
			ResultSet resultSet = statement.executeQuery();

//			System.out.println("1 " + resultSet.getString("Placa"));
			while (resultSet.next()) {
				System.out.println("2");
				carro = new Carro(resultSet.getString("Placa"),resultSet.getInt("KM"),resultSet.getString("Modelo"),resultSet.getString("Marca"),resultSet.getString("Cor"),resultSet.getInt("Ano"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("3");
		return carro;
	}

	
	public static void saveCarro(Carro c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Database.getDBConnection();
			String query = "insert into Carro (Placa, KM, Modelo, Marca, Cor, Ano) values (?, ?, ?, ?, ?, ?)\n";
			statement = connection.prepareStatement(query);
			statement.setString(1, c.getPlaca()); // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo
			statement.setLong(2, c.getQuilometragem());
			statement.setString(3, c.getModelo());
			statement.setString(4, c.getMarca());
			statement.setString(5, c.getCor());
			statement.setInt(6, c.getAno());
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
	
}

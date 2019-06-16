package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Carro {
	String placa;
	int quilometragem;
	String modelo;
	String marca;
	String cor;
	int ano;
	String grupo;

	public Carro(String placa, int quilometragem, String modelo, String marca, String cor, int ano, String grupo) {
		this.placa = placa;
		this.quilometragem = quilometragem;
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.ano = ano;
		this.grupo = grupo;
	}

	public Carro() {

	}

	public void setPlaca(String placa){
		this.placa = placa;
	}
	public String getPlaca() {
		return placa;
	}

	public void setQuilometragem(int Quilometragem){
		this.quilometragem = Quilometragem;
	}
	public long getQuilometragem() {
		return quilometragem;
	}

	public void setModelo(String modelo){
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setMarca(String Marca){
		this.marca = Marca;
	}

	public String getMarca() {
		return marca;
	}
	public void setCor(String cor){
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}
	public void setAno(int Ano){
		this.ano = Ano;
	}

	public int getAno() {
		return ano;
	}
	public String getGrupo() {
		return this.grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String toString() {
		return "Modelo: " + this.modelo + "\nAno: " + this.ano + "\nCor: " + this.cor + "\nPlaca: " + this.placa +
				"\nMarca: " + this.marca + "\nQuilometragem: " + this.quilometragem + "\nGrupo: "+this.grupo;
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

			while (resultSet.next()) {
				System.out.println("2");
				carro = new Carro(resultSet.getString("Placa"),resultSet.getInt("KM"),resultSet.getString("Modelo"),resultSet.getString("Marca"),resultSet.getString("Cor"),resultSet.getInt("Ano"), resultSet.getString("Grupo"));
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
		System.out.println("Entrei na fç savecarro");
		try {
			System.out.println("Tentando...");
			connection = Database.getDBConnection();
			String query = "insert into Carro (Placa, KM, Modelo, Marca, Cor, Ano, Grupo) values (?, ?, ?, ?, ?, ?, ?)\n";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, c.getPlaca()); // O parametro 1 faz referencia ao ? da string query. caso 2 ?, teriamos um setString pro primeiro e outro pro segundo
			statement.setLong(2, c.getQuilometragem());
			statement.setString(3, c.getModelo());
			statement.setString(4, c.getMarca());
			statement.setString(5, c.getCor());
			statement.setInt(6, c.getAno());
			statement.setString(7, c.getGrupo());
			System.out.println("SQL: "+query);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();

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

	public static List<Carro> getAll() {
		Connection connection = null;
		PreparedStatement statement;
		List<Carro> carros = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Carro ORDER BY Grupo";
			statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Carro car = new Carro();
				car.setCor(rs.getString("Cor"));
				car.setMarca(rs.getString("Marca"));
				car.setAno(Integer.parseInt(rs.getString("Ano")));
				car.setQuilometragem(Integer.parseInt(rs.getString("KM")));
				car.setModelo(rs.getString("Modelo"));
				car.setPlaca(rs.getString("Placa"));
				car.setGrupo(rs.getString("Grupo"));
				carros.add(car);
			}

			return carros;
		}
		catch (SQLException exception) {
			exception.getStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static List<String> OrdenaGrupos(List<String> arr){
        for(int j=0;j<arr.size();j++){
            for(int i=j+1;i<arr.size();i++){
                if((arr.get(i)).compareToIgnoreCase(arr.get(j))<0){
                    String t = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, t);
                }
            }
        }
        return arr;
	}

	public static List<String> getAllGrupos() {
		Connection connection = null;
		PreparedStatement statement;
		List<String> grupos = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT DISTINCT(Grupo) FROM Carro";
			statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				grupos.add(rs.getString("Grupo"));
			}
			return OrdenaGrupos(grupos);
		}
		catch (SQLException exception) {
			exception.getStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<String> getCarOfAGroup(String grupo) {
		Connection connection = null;
		PreparedStatement statement;
		List<String> carros = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT Marca FROM Carro WHERE Grupo=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, grupo);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				carros.add(rs.getString("Marca"));
			}

			return carros;
		}
		catch (SQLException exception) {
			exception.getStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<String> getAvailableCarOfAGroup(String grupo)	{
		List<String> carros = new ArrayList<>();
		carros = Carro.getCarOfAGroup(grupo);
		
		for(int i = 0; i < carros.size(); i++) {
			
		}
	}




	public static void updateCar(Carro c) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = Database.getDBConnection();
			String query = "update Carro set Cor=?, Marca=?, Ano=?, KM=?, Modelo=?, Grupo=? where Placa=?";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, c.getCor());
			statement.setString(2, c.getMarca());
			statement.setLong(3, c.getAno());
			statement.setLong(4, c.getQuilometragem());
			statement.setString(5, c.getModelo());
			statement.setString(6, c.getGrupo());
			statement.setString(7, c.getPlaca());

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

}

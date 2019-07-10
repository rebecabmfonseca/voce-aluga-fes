package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Manutencao {
	private int idManutencao;
	private String dataInicio;
	private String dataPrevisao;
	private String placa;
	private String descricao;

	public Manutencao(){

	}
	public Manutencao(int idManutencao, String dataInicio, String dataPrevisao, String placa, String descricao) {
		super();
		this.idManutencao = idManutencao;
		this.dataInicio = dataInicio;
		this.dataPrevisao = dataPrevisao;
		this.placa = placa;
		this.descricao = descricao;
	}
	public int getIdManutencao() {
		return idManutencao;
	}
	public void setIdManutencao(int idManutencao) {
		this.idManutencao = idManutencao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataPrevisao() {
		return dataPrevisao;
	}
	public void setDataPrevisao(String dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Manutencao> getAll() {
		Connection connection = null;
		PreparedStatement statement;
		List<Manutencao> manutencoes = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Manutencao ";
			statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Manutencao m = new Manutencao();
				m.setDataInicio(rs.getString("Data_Inicio"));
				m.setDataPrevisao(rs.getString("Data_Previsao"));
				m.setDescricao(rs.getString("Descricao"));
				m.setIdManutencao(rs.getInt("ID_Manutencao"));
				m.setPlaca(rs.getString("Placa"));
				manutencoes.add(m);
			}

			return manutencoes;
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

	public static void saveManutencao(Manutencao m) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getDBConnection();
			String query = "insert into Manutencao (Data_Inicio, Data_Previsao, Placa, Descricao) values (?, ?, ?, ?)\n";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, m.getDataInicio());
			statement.setString(2, m.getDataPrevisao());
			statement.setString(3, m.getPlaca());
			statement.setString(4, m.getDescricao());
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




}

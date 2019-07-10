package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Financeiro {
	private String formaPagamento;
	private double valor;
	private int idReserva;
	private int idFinanceiro;

	public Financeiro(){

	}

	public Financeiro(String formaPagamento, double valor, int idRes, int idFin) {
		this.formaPagamento = formaPagamento;
		this.valor = valor;
		this.idReserva = idRes;
		this.idFinanceiro = idFin;
	}



	public int getIdFinanceiro() {
		return idFinanceiro;
	}

	public void setIdFinanceiro(int idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public static void saveFinanceiro(Financeiro f) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.getDBConnection();
			String query = "INSERT INTO `Financeiro` (`ID_Reserva`, `Forma_Pagamento`, `valor`) "
					+ "VALUES (?, ?, ?)";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, f.getIdReserva());
			statement.setString(2, f.getFormaPagamento());
			statement.setDouble(3, f.getValor());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (null != rs) {
				rs.close();
			}

			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}

	}

	public static List<Financeiro> getAll(){
		Connection connection = null;
		PreparedStatement statement;
		List<Financeiro> financeiros = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Financeiro";
			statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Financeiro fin = new Financeiro();
				fin.setFormaPagamento(rs.getString("Forma_Pagamento"));
				fin.setValor(rs.getDouble("valor"));
				fin.setIdReserva(rs.getInt("ID_Reserva"));
				fin.setIdFinanceiro(rs.getInt("ID_Financeiro"));
				financeiros.add(fin);

				}
		} catch (SQLException exception) {
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return financeiros;

	}

	public static Financeiro getFinanceiro(int idFinanceiro) {
			Connection connection = null;
			PreparedStatement statement;
			Financeiro financeiro = null;

			try {
				connection = Database.getDBConnection();
				String query = "SELECT * FROM Financeiro WHERE ID_Financeiro=? LIMIT 1";
				statement = connection.prepareStatement(query);
				statement.setInt(1, idFinanceiro);
				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {
					financeiro = new Financeiro();
					financeiro.setFormaPagamento(resultSet.getString("Forma_Pagamento"));
					financeiro.setIdFinanceiro(idFinanceiro);
					financeiro.setValor(resultSet.getDouble("valor"));
					financeiro.setIdReserva(resultSet.getInt("ID_Reserva"));

				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return financeiro;
		}
	public static void estorna(int idFinanceiro) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.getDBConnection();
			String query = "DELETE FROM Financeiro WHERE ID_Financeiro=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, idFinanceiro);

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




}

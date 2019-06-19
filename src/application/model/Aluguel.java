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

public class Aluguel {
	int id;
	String avarias;
	int diaEntrega;
	int mesEntrega;
	int anoEntrega;
	int diaRetirada;
	int mesRetirada;
	int anoRetirada;
	String placa;
	String CNH;
	String apolice;

	public Aluguel(){

	}


	@Override
	public String toString() {
		return "Aluguel [id=" + id + ", avarias=" + avarias + ", diaEntrega=" + diaEntrega + ", mesEntrega="
				+ mesEntrega + ", anoEntrega=" + anoEntrega + ", diaRetirada=" + diaRetirada + ", mesRetirada="
				+ mesRetirada + ", anoRetirada=" + anoRetirada + ", placa=" + placa + ", CNH=" + CNH + ", apolice="
				+ apolice + "]";
	}




	public Aluguel( String avarias, int diaEntrega, int mesEntrega, int anoEntrega, int diaRetirada,
			int mesRetirada, int anoRetirada, String placa, String cNH, String apolice) {
		this.avarias = avarias;
		this.diaEntrega = diaEntrega;
		this.mesEntrega = mesEntrega;
		this.anoEntrega = anoEntrega;
		this.diaRetirada = diaRetirada;
		this.mesRetirada = mesRetirada;
		this.anoRetirada = anoRetirada;
		this.placa = placa;
		this.CNH = cNH;
		this.apolice = apolice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvarias() {
		return avarias;
	}

	public void setAvarias(String avarias) {
		this.avarias = avarias;
	}

	public String getDataEntrega() {
		return this.diaEntrega+"/"+this.mesEntrega+"/"+this.anoEntrega;
	}
	public void setDataEntrega(int dia, int mes, int ano){
		this.diaEntrega = dia;
		this.mesEntrega = mes;
		this.anoEntrega = ano;
	}

	public String getDataRetirada() {
		return this.diaRetirada+"/"+this.mesRetirada+"/"+this.anoRetirada;
	}

	public void setDataRetirada(int dia, int mes, int ano) {
		this.diaRetirada = dia;
		this.mesRetirada = mes;
		this.anoRetirada = ano;
	}
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getApolice() {
		return apolice;
	}

	public void setApolice(String apolice) {
		this.apolice = apolice;
	}

	public static void removeAluguel(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.getDBConnection();
			String query = "DELETE FROM Reservas WHERE ID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);

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

	public static Aluguel getAluguel(int id) {
		Connection connection = null;
		PreparedStatement statement;
		Aluguel alu = null;

		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Reservas WHERE ID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				alu = new Aluguel();
				alu.setId(Integer.parseInt(rs.getString("ID")));
				alu.setAvarias(rs.getString("Avarias"));
				String dataRetirada = rs.getString("Data_Ret");
				@SuppressWarnings("deprecation")
				Date date = new Date(dataRetirada);
				Calendar cal = Calendar.getInstance();
		    	cal.setTime(date);
		    	alu.setDataRetirada(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
		    	String dataEntrega = rs.getString("Data_Ent");
				@SuppressWarnings("deprecation")
				Date date2 = new Date(dataEntrega);
				Calendar cal2 = Calendar.getInstance();
		    	cal.setTime(date2);
		    	alu.setDataRetirada(cal2.get(Calendar.DAY_OF_MONTH), cal2.get(Calendar.MONTH), cal2.get(Calendar.YEAR));
		    	alu.setApolice(rs.getString("Apólice"));
		    	alu.setPlaca(rs.getString("Placa"));
		    	alu.setCNH(rs.getString("CNH"));


			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alu;
	}


	public static List<Aluguel> getAll(){
		Connection connection = null;
		PreparedStatement statement;
		List<Aluguel> aluguel = new ArrayList<>();
		try {
			connection = Database.getDBConnection();
			String query = "SELECT * FROM Reserva";
			statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Aluguel alu = new Aluguel();
				alu.setId(Integer.parseInt(rs.getString("ID")));
				alu.setAvarias(rs.getString("Avarias"));

				String dataRetirada = rs.getString("Data_Ret");
				@SuppressWarnings("deprecation")
				Date date = new Date(dataRetirada);
				Calendar cal = Calendar.getInstance();
		    	cal.setTime(date);
		    	alu.setDataRetirada(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

		    	String dataEntrega = rs.getString("Data_Ent");
		    	@SuppressWarnings("deprecation")
				Date date2 = new Date(dataEntrega);
				Calendar cal2 = Calendar.getInstance();
		    	cal.setTime(date2);
		    	alu.setDataEntrega(cal2.get(Calendar.DAY_OF_MONTH), cal2.get(Calendar.MONTH), cal2.get(Calendar.YEAR));

		    	alu.setApolice(rs.getString("Apolice"));
		    	alu.setPlaca(rs.getString("Placa"));
		    	alu.setCNH(rs.getString("CNH"));
				aluguel.add(alu);

				}
		} catch (SQLException exception) {
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aluguel;

	}

	public static void saveAluguel(Aluguel a) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.getDBConnection();
			String query = "INSERT INTO `Reserva` (`Avarias`, `Data_Ent`, `Data_Ret`, `Placa`, `CNH`, `Apolice`) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getAvarias());
			statement.setString(2, a.getDataEntrega());
			statement.setString(3, a.getDataRetirada());
			//statement.setInt(4, a.getId());
			statement.setString(4, a.getPlaca());
			statement.setString(5, a.getCNH());
			statement.setString(6, a.getApolice());
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




}

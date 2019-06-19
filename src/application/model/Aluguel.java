package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aluguel {
/*	String idCliente;
	String cnhCliente;
	String dataNascCliente;
	String enderecoCliente;
	String cepCliente;
	String nomeCliente;
	boolean listaNegra;

	String grupoCarro;
	String carro;
	String placaCarro;
	
	String dataRet;
	String dataEnt;
	Long seguro;
	

	
	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getCnhCliente() {
		return cnhCliente;
	}


	public void setCnhCliente(String cnhCliente) {
		this.cnhCliente = cnhCliente;
	}


	public String getDataNascCliente() {
		return dataNascCliente;
	}


	public void setDataNascCliente(String dataNascCliente) {
		this.dataNascCliente = dataNascCliente;
	}


	public String getEnderecoCliente() {
		return enderecoCliente;
	}


	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}


	public String getCepCliente() {
		return cepCliente;
	}


	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public boolean isListaNegra() {
		return listaNegra;
	}


	public void setListaNegra(boolean listaNegra) {
		this.listaNegra = listaNegra;
	}


	public String getGrupoCarro() {
		return grupoCarro;
	}


	public void setGrupoCarro(String grupoCarro) {
		this.grupoCarro = grupoCarro;
	}


	public String getCarro() {
		return carro;
	}


	public void setCarro(String carro) {
		this.carro = carro;
	}


	public String getPlacaCarro() {
		return placaCarro;
	}


	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}


	public String getDataRet() {
		return dataRet;
	}


	public void setDataRet(String dataRet) {
		this.dataRet = dataRet;
	}


	public String getDataEnt() {
		return dataEnt;
	}


	public void setDataEnt(String dataEnt) {
		this.dataEnt = dataEnt;
	}


	public Long getSeguro() {
		return seguro;
	}


	public void setSeguro(Long seguro) {
		this.seguro = seguro;
	}	*/
	
	public Aluguel(String cliente, String carro, String dataRet, String dataEnt) {
		super();
		this.cliente = cliente;
		this.carro = carro;
		this.dataRet = dataRet;
		this.dataEnt = dataEnt;
	}
	
	String cliente;
	String carro;
	String dataRet;
	String dataEnt;
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public String getDataRet() {
		return dataRet;
	}

	public void setDataRet(String dataRet) {
		this.dataRet = dataRet;
	}

	public String getDataEnt() {
		return dataEnt;
	}

	public void setDataEnt(String dataEnt) {
		this.dataEnt = dataEnt;
	}	
	
/*	public static void saveAluguel(Carro c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		System.out.println("Entrei na fç savealuguel");
		try {
			System.out.println("Tentando...");
			connection = Database.getDBConnection();
			String query = "insert into Aluguel (Data_Ret, Data_Ent, Modelo, Marca, Cor, Ano, Grupo) values (?, ?, ?, ?, ?, ?, ?)\n";
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
	}*/
}

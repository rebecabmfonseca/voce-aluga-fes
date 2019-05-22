package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	private static final Logger logger = Logger.getLogger(Database.class.getName());
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	//private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/voce_aluga?autoReconnect=true&useSSL=false";
	private static final String DB_CONNECTION = "sql10.freemysqlhosting.net";
	private static final String DB_DATABASE = "sql10292127";
	private static final String DB_USER = "sql10292127";
	private static final String DB_PASSWORD = "hrymYzd2Ef";
	private static final String URL = "jdbc:mysql://"+DB_CONNECTION +"/"+DB_DATABASE;

	private Database() {}

		public static Connection getDBConnection() throws SQLException {
			Connection connection = null;
			try {
				Class.forName(DB_DRIVER);
			} catch (ClassNotFoundException exception) {
				logger.log(Level.SEVERE, exception.getMessage());
			}

			try {
				connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
				if (connection != null) {
		            System.out.println("STATUS--->Conectado com sucesso!");
		            return connection;
		        } else {
		        	System.out.println("STATUS--->Não foi possivel realizar conexão");
		        	return null;
		        }
			} catch (SQLException exception) {
				logger.log(Level.SEVERE, exception.getMessage());
			}

			return connection;
		}
	}

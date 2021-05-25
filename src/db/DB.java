package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	//metodo para conectar no banco
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	//metodo para fechar conexão com banco
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	//metodo para carregar as credenciais do banco 
	private static Properties loadProperties() {
		//faz a leitura das credenciais do banco do arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")){
			//carrega as propriedades do banco
			Properties props = new Properties();
			//carrega as propriedades do banco, com base no arquivo informado
			props.load(fs);
			return props;
		} catch (Exception e) {
			//dispara uma exeção personalizada
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeResultSEt(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}

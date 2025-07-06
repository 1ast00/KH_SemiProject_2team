package config;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

//싱글톤 패턴
public class DBManager {
	private static DBManager instance = new DBManager();
	private OracleDataSource ods;
	private DBManager() {
		//DB 접속 처리
		try {
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:C##HIPKIKOMORI/123456@localhost:1521:xe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws SQLException {
		return ods.getConnection();
	}

	public static DBManager getInstance() {
		if(instance==null)
			instance = new DBManager();
		return instance;
	}

	
	
}
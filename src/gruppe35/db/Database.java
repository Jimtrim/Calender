package gruppe35.db;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Database {
	private String driver = "com.mysql.jdbc.Driver";
	private String host = "jdbc:mysql://mysql.stud.ntnu.no/____";
	private String user = "";
	private String pass = "";
	private Connection connect;
	
	
	/**
	 * Creates a connection to the database
	 * @throws SQLException
	 * @throws {@link ClassNotFoundException}
	 */
	public Database() {
		try {
			//Load driver and connect
			Class.forName(driver);
			connect = (Connection) DriverManager.getConnection(host, user, pass);

		} catch(ClassNotFoundException err){
			//TODO:ERROR handling
		} catch(SQLException sq) {
			//TODO: ERROR handling
		}
	}
	
	
	
}

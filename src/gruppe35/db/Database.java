package gruppe35.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	/**
	 * Login to database, takes two args
	 * @param user: String
	 * @param password : String
	 */
	public String login(String user,String password){
		String feedback = "";
		String query = "SELECT _field_user_,_field_password_ FROM "+
				"_table_ WHERE _field_user = "+user+ " AND "
				+"_field_password_ = "+password;
		
		try {
			ResultSet rs = connect.createStatement().executeQuery(query);
			if(rs.first()){
				feedback = rs.getString(1);
			}
			else{
				feedback = "No match";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		return feedback;
	}
}

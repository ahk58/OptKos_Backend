package data_loader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;

public class SqlConnection {

	private static Connection connection = null;
	private static final String url = "jdbc:db2://if-db2.hs-kempten.de:50000/ERPP:retrieveMessagesFromServerOnGetMessage=true;";

	protected SqlConnection(){
	}

	public static Connection getConnection(){
		if (connection == null){
			try {
				connection = DriverManager.getConnection(url, DbLoginData.getUsername(), DbLoginData.getPassword());
			} catch (SQLException e) {
                System.err.println("Couldn't connect to Database");
                if (DbLoginData.getUsername() == null || DbLoginData.getPassword() == null) {
					System.err.println("DB Credentials are null");
				}
				e.printStackTrace();
				exit(1);
			}
		}
		return connection;
	}
}
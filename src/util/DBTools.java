package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBTools {
	 private DataSource dataSource;

	    public DBTools(String jndiname) throws SQLException, ClassNotFoundException {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"
	                    + jndiname);
	        } catch (NamingException e) {
	            // Handle error that it's not configured in JNDI.
	            throw new SQLException(jndiname + " is missing in JNDI! : " + e.getMessage());
	        }
	    }

	    public Connection getConnection() throws SQLException {
	        return dataSource.getConnection();
	    }

	    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.jdbc.Driver");
	        if (DBStatic.mysql_pooling == false) {
	            return (DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host
	                    + "/"
	                    + DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
	        } else {
	            DBTools database = new DBTools("jdbc/db");
	            return (database.getConnection());
	        }
	    }

}

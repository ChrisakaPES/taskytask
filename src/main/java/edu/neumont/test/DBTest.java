package edu.neumont.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	private static Connection accessDB() throws URISyntaxException, SQLException {

	//go to your heroku directory and run heroku config to find your database url.

	        String myUrl = "postgres://ddcnuhjimxlzao:o4bZRVYj2eRaFa0ZdCH88gM1rC@ec2-50-19-233-111.compute-1.amazonaws.com:5432/db80s9u2v37c74";

	        

	        URI dbUri = new URI(myUrl);

	        String[] values = dbUri.getUserInfo().split(":");

	 

	        String username = values[0];

	        String password = values[1];

	 

	        int port = dbUri.getPort();

	 

	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath()+"?user="+username+"&password="+password+"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	 

	        return DriverManager.getConnection(dbUrl, username, password);

	    }

	public static void testDB() {

		try {

			// In order for this line of code to work you'll need to download
			// the postgresql jdbc drive here:
			// https://jdbc.postgresql.org/download.html (Links to an external
			// site.)

			// add the jar to the project dependencies.
			System.out.println("start test");
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Could not locate PostgreSQL Driver.");

		}

		Connection conn = null;

		try {

			conn = accessDB();

			Statement stm = conn.createStatement();
			
			// This is a simple query test.
			
			ResultSet set = stm.executeQuery("Select * from users;");
			
			while (set.next()) {
				System.out.println("Test info = " + set.getString("username"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (URISyntaxException e) {

			e.printStackTrace();

		}

	}
}
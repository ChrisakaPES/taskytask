package edu.neumont.dal;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection accessDB() throws URISyntaxException, SQLException {

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

}

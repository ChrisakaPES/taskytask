package edu.neumont.dal;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import edu.neumont.models.User;

public class UserServiceImpl implements UserService{

	private Connection connection;
	
	public UserServiceImpl() {
		try {
			this.connection = DbConnection.accessDB();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean CreateUser(String userName, String password, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void DeleteUser(long user_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User UpdateUser(User userToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User RetrieveUser(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}

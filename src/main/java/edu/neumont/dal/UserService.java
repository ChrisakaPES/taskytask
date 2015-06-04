package edu.neumont.dal;

import edu.neumont.models.User;

public interface UserService {

	boolean CreateUser(String userName, String password,String email);
	void DeleteUser(long user_id);
	User UpdateUser(User userToUpdate);
	User RetrieveUser(long user_id);
}

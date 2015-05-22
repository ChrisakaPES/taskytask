package edu.neumont.dal;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import edu.neumont.models.Slate;
public class SlateHandler 
{ 
	private Map<Integer,Slate> slateMap = new HashMap<Integer,Slate>();
	private int nextSlateID = 1;
	private Connection connection;
	public SlateHandler() {
		try {
			connection = DbConnection.accessDB();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see edu.neumont.dal.SlateDAL#createSlate(String, String, LocalDateTime)
	 */
	public boolean createSlate(String name, String description, LocalDateTime dueDate) 
	{ 
		Slate newSlate = new Slate(name,description, dueDate);
		synchronized(this){
			slateMap.put(nextSlateID, newSlate);
			nextSlateID++;
		}
		return true;
	} 
	/* (non-Javadoc)
	 * @see edu.neumont.dal.SlateDAL#retrieveSlate(int)
	 */
	public Slate retrieveSlate(int index) 
	{ 
//		Statement stm = connection.createStatement();
//		ResultSet set = stm.executeQuery("Select * from slates Where slate_id = " + index);
		Slate retrievingSlate = new Slate();
//		if(retrievingSlate == null)
//		{
//			
//			retrievingSlate = new Slate("Non Existant Slate",
//					"This slate was created in order to demonstrate the creation of new slates with actual data",LocalDateTime.now());
//		}
		return retrievingSlate;
	} 
	
	/* (non-Javadoc)
	 * @see edu.neumont.dal.SlateDAL#updateSlate(int, edu.neumont.models.Slate)
	 */
	public void updateSlate(int index, Slate updatedSlate) 
	{ 
		slateMap.replace(index, updatedSlate);
	} 
	public void deleteSlate(int index) 
	{ 
		slateMap.remove(index);
	} 
}

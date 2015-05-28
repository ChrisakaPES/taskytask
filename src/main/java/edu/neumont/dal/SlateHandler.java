package edu.neumont.dal;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import edu.neumont.models.Slate;

public class SlateHandler implements SlateDAL
{ 
	private Map<Integer,Slate> slateMap = new HashMap<Integer,Slate>();
	public static final Logger shlogger = (Logger) LoggerFactory.getLogger(SlateHandler.class);
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
		String sql = "Insert into slates(slate_id,user_id,slate_name,slate_description,deadline) values (?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			int slateID = generateSlateId() + 1;
			statement.setInt(1, slateID);
			shlogger.debug("new slate id = " + generateSlateId());
			statement.setInt(2, 1);
			statement.setString(3, name);
			statement.setString(4, description);
			statement.setTimestamp(5, Timestamp.from(dueDate.toInstant(ZoneOffset.ofHours(0))));
			int rowsInserted = statement.executeUpdate();
			shlogger.debug("Rows changed:" + rowsInserted);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	} 
	/* (non-Javadoc)
	 * @see edu.neumont.dal.SlateDAL#retrieveSlate(int)
	 */
	public Slate retrieveSlate(int index) 
	{ 
		Slate retrievingSlate = new Slate();
		Statement stm;
			try {
				stm = connection.createStatement();
				ResultSet set;
				
				set = stm.executeQuery("Select * from slates Where slate_id=" + index);
				while(set.next()) {
					retrievingSlate.setDescription(set.getString("slate_description"));
					retrievingSlate.setDueDate((set.getTimestamp("deadline").toLocalDateTime()));
					retrievingSlate.setName(set.getString("slate_name"));		

				}
				stm.close();

			} catch (SQLException e) {
				shlogger.debug(e.getMessage());
			}

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
	private int generateSlateId() {
		String query = "select MAX(slate_id) from slates";
		int id = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt(1);
			}
			shlogger.debug(id + "");
			stmt.close();
		} catch (SQLException e) {
			shlogger.debug(e.getMessage());
		}
		return id;
	}
}

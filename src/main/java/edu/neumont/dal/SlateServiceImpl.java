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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import edu.neumont.models.Slate;

//@Service("slateService")
public class SlateServiceImpl implements SlateService
{ 
	private Map<Integer,Slate> slateMap = new HashMap<Integer,Slate>();
	public static final Logger shlogger = (Logger) LoggerFactory.getLogger(SlateServiceImpl.class);
	private Connection connection;
	public SlateServiceImpl() {

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
	public List<Slate> retrieveUserSlates(long index) 
	{ 
		List<Slate> slates = new ArrayList<>();
		Slate retrievingSlate = null;
		Statement stm;
			try {
				stm = connection.createStatement();
				ResultSet set;
				
				set = stm.executeQuery("Select * from slates Where user_id=" + index);
				while(set.next()) {
					retrievingSlate = new Slate();
					retrievingSlate.setId(set.getLong("slate_id"));
					retrievingSlate.setUserId(set.getInt("user_id"));
					retrievingSlate.setDescription(set.getString("slate_description"));
					retrievingSlate.setDueDate((set.getTimestamp("deadline").toLocalDateTime()));
					retrievingSlate.setName(set.getString("slate_name"));		
					slates.add(retrievingSlate);
				}
				stm.close();

			} catch (SQLException e) {
				shlogger.debug(e.getMessage());
			}

		return slates;

	} 
	
	/* (non-Javadoc)
	 * @see edu.neumont.dal.SlateDAL#updateSlate(int, edu.neumont.models.Slate)
	 */
	public void updateSlate(long index, Slate updatedSlate) 
	{ 
		String sql = "UPDATE slates SET slate_description=?, slate_name=?, deadline=? WHERE slate_id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, updatedSlate.getDescription());
			statement.setString(2,updatedSlate.getName());
			statement.setTimestamp(3, Timestamp.from(updatedSlate.getDueDate().toInstant(ZoneOffset.ofHours(0))));
			statement.setLong(4, index);
			int rowsUpdated = statement.executeUpdate();
			shlogger.debug("rows updated:" + rowsUpdated);

		} catch (SQLException e){
			shlogger.debug(e.getMessage());

		}
	} 
	public void deleteSlate(long index) 
	{ 
		String sql = "Delete from slates where slate_id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, index);
			int rowsDeleted = statement.executeUpdate();
			shlogger.debug("rows effected:" + rowsDeleted);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	@Override
	public Slate retrieveSlate(long index) {
		Slate retrievingSlate = null;
		Statement stm;
			try {
				stm = connection.createStatement();
				ResultSet set;
				
				set = stm.executeQuery("Select * from slates Where slate_id=" + index);
				while(set.next()) {
					retrievingSlate = new Slate();
					retrievingSlate.setId(set.getLong("slate_id"));
					retrievingSlate.setUserId(set.getInt("user_id"));
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
}

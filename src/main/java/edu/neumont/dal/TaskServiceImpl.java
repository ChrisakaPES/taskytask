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
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import edu.neumont.models.Task;

//@Service("taskService")
public class TaskServiceImpl implements TaskService{

	private Connection connection;
	public static final Logger tasklogger = (Logger) LoggerFactory.getLogger(TaskServiceImpl.class);

	public TaskServiceImpl() {
		try {
			connection = DbConnection.accessDB();
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean create(long slate_id, String name, String description,
			LocalDateTime deadline) {
		String sql = "insert into task(slate_id,slate_name,slate_description,deadline) values (?,?,?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, slate_id);
			statement.setString(2, name);
			statement.setString(3, description);
			statement.setTimestamp(4, Timestamp.from(deadline.toInstant(ZoneOffset.ofHours(0))));
			int rowsUpdated = statement.executeUpdate();
			tasklogger.debug("rows updated:" + rowsUpdated);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
	}

	@Override
	public Task update(Task t) {
		String sql = "UPDATE task SET slate_description=?, slate_name=?, deadline=? WHERE task_id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, t.getTask_description());
			statement.setString(2,t.getTask_name());
			statement.setTimestamp(3, Timestamp.from(t.getDeadline().toInstant(ZoneOffset.ofHours(0))));
			statement.setLong(4, t.getTask_id());
			int rowsUpdated = statement.executeUpdate();
			tasklogger.debug("rows updated:" + rowsUpdated);

		} catch (SQLException e){
			tasklogger.debug(e.getMessage());

		}		
		return t;
	}

	@Override
	public void delete(long task_id) {
		String sql = "Delete from task where task_id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, task_id);
			int rowsDeleted = statement.executeUpdate();
			tasklogger.debug("rows effected:" + rowsDeleted);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	@Override
	public List<Task> retrieveTasks(long slate_id) {
		Task retrievingTask = null;
		List<Task> tasks = new ArrayList<>();
		Statement stm;
			try {
				stm = connection.createStatement();
				ResultSet set;
				
				set = stm.executeQuery("Select * from task Where slate_id=" + slate_id);
				while(set.next()) {
					retrievingTask = new Task();
					retrievingTask.setSlate_id(set.getLong("slate_id"));
					retrievingTask.setTask_id(set.getLong("task_id"));
					retrievingTask.setTask_description(set.getString("slate_description"));
					retrievingTask.setDeadline((set.getTimestamp("deadline").toLocalDateTime()));
					retrievingTask.setTask_name(set.getString("slate_name"));		
					tasks.add(retrievingTask);
				}
				stm.close();

			} catch (SQLException e) {
				tasklogger.debug(e.getMessage());
			}

		return tasks;

	}
	public Task retrieveTask(long task_id) {
		Task retrievingTask = null;
		List<Task> tasks = new ArrayList<>();
		Statement stm;
			try {
				stm = connection.createStatement();
				ResultSet set;
				
				set = stm.executeQuery("Select * from task Where task_id=" + task_id);
				while(set.next()) {
					retrievingTask = new Task();
					retrievingTask.setSlate_id(set.getLong("slate_id"));
					retrievingTask.setTask_id(set.getLong("task_id"));
					retrievingTask.setTask_description(set.getString("slate_description"));
					retrievingTask.setDeadline((set.getTimestamp("deadline").toLocalDateTime()));
					retrievingTask.setTask_name(set.getString("slate_name"));		
					tasks.add(retrievingTask);
				}
				stm.close();

			} catch (SQLException e) {
				tasklogger.debug(e.getMessage());
			}

		return retrievingTask;

	}
}

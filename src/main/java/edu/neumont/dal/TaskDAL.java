package edu.neumont.dal;

import java.time.LocalDateTime;
import java.util.List;

import edu.neumont.models.Task;

public interface TaskDAL {

	boolean create(long slate_id,String name,String description,LocalDateTime deadline);
	Task update(Task t);
	void delete(long task_id);
	List<Task> retrieveTasks(long slate_id);
}

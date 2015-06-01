package edu.neumont.dal;

import java.time.LocalDateTime;
import java.util.List;

import edu.neumont.models.Task;

public interface TaskDAL {

	Task create(int slate_id,String name,String description,LocalDateTime deadline);
	Task update(Task t);
	void delete(Task t);
	List<Task> retrieveTasks(int slate_id);
}

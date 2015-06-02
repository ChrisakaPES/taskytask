package edu.neumont.dal;

import java.time.LocalDateTime;
import java.util.List;

import edu.neumont.models.Slate;

public interface SlateDAL {

	
	public abstract boolean createSlate(String name, String description,
			LocalDateTime dueDate);

	public abstract List<Slate> retrieveUserSlates(long index);

	Slate retrieveSlate(long index);
	public abstract void updateSlate(long index, Slate updatedSlate);
	
	public abstract void deleteSlate(long index);

}
package edu.neumont.dal;

import java.time.LocalDateTime;

import edu.neumont.models.Slate;

public interface SlateDAL {

	
	public abstract boolean createSlate(String name, String description,
			LocalDateTime dueDate);

	public abstract Slate retrieveSlate(int index);

	public abstract void updateSlate(int index, Slate updatedSlate);
	
	public abstract void deleteSlate(int index);

}
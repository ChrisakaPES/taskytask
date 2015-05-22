package edu.neumont.models;
import java.time.LocalDateTime; 

public class Slate 
{ 
	private int slate_id;
	private String name; 
	private String description; 
	private LocalDateTime dueDate; 
	public Slate() 
	{
		
	}
	public Slate(String name, String description, LocalDateTime dueDate) 
	{ 
		this.name = name; 
		this.description = description; 
		this.dueDate = dueDate; 
	} 
	public String getName() 
	{ 
		return name; 
	} 
	public void setName(String name) 
	{ 
		this.name = name;
	} 
	public String getDescription() 
	{ 
		return description; 
	} 
	public void setDescription(String description) 
	{ 
		this.description = description; 
	} 
	public LocalDateTime getDueDate() 
	{ 
		return dueDate;
	} 
	public void setDueDate(LocalDateTime dueDate) 
	{ 
		this.dueDate = dueDate; 
	} 
}

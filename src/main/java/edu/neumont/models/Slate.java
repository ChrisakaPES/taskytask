package edu.neumont.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="slates")
public class Slate implements Serializable
{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="slate_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long slate_id;
	
	@Column
	private long user_id;
	
	@Column(name="slate_name")
	private String name; 
	
	@Column(name="slate_description")
	private String description;
	
	@Column(name="deadline")
	private LocalDateTime dueDate; 
	public Slate() 
	{
		
	}
	public Slate(String name, String description, LocalDateTime dueDate, long user_id) 
	{ 
		this.name = name; 
		this.description = description; 
		this.dueDate = dueDate; 
		this.user_id = user_id;
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
	public long getId()
	{
		return slate_id;
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
	public void setId(Long id) {
		this.slate_id = id;
	}
	public void setUserId(int id) {
		this.user_id = id;
	}
	@Override
	public String toString() {
		return name + " " + description;
	}
}

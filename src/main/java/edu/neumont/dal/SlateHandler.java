package edu.neumont.dal;

import java.time.LocalDateTime; 
import java.util.HashMap;
import java.util.Map;

import edu.neumont.models.Slate;
public class SlateHandler implements SlateDAL 
{ 
	private Map<Integer,Slate> slateMap = new HashMap<Integer,Slate>();
	private int nextSlateID = 1;
	/**
	 * @see edu.neumont.dal.SlateDAL#createSlate(String, String, LocalDateTime)
	 */
	@Override
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
	@Override
	public Slate retrieveSlate(int index) 
	{ 
		Slate retrievingSlate = slateMap.get(index);
		if(retrievingSlate == null)
		{
			retrievingSlate = new Slate("Non Existant Slate",
					"This slate was created in order to demonstrate the creation of new slates with actual data",LocalDateTime.now());
		}
		return retrievingSlate;
	} 
	
	/* (non-Javadoc)
	 * @see edu.neumont.dal.SlateDAL#updateSlate(int, edu.neumont.models.Slate)
	 */
	@Override
	public void updateSlate(int index, Slate updatedSlate) 
	{ 
		slateMap.replace(index, updatedSlate);
	} 
	@Override
	public void deleteSlate(int index) 
	{ 
		slateMap.remove(index);
	} 
}

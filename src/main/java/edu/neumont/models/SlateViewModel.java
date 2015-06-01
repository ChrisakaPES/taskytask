package edu.neumont.models;

import java.util.ArrayList;
import java.util.List;

public class SlateViewModel {

	public List<Task> tasks = new ArrayList<Task>();
	
	public SlateViewModel(List<Task> tasks)
	{
		this.tasks = tasks;
	}
	
	
	
}

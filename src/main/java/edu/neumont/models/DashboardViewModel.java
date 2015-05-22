package edu.neumont.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DashboardViewModel {
	
	private List<Slate> slates = new ArrayList<Slate>();
	
	public DashboardViewModel addSlate(Slate toBeAdded)
	{
		slates.add(toBeAdded);
		return this;
	}
	public DashboardViewModel addSlates(Collection<Slate> toBeAdded)
	{
		for(Slate s: toBeAdded)
		{
			slates.add(s);
		}
		return this;
	}
	
	
	public List<Slate> getSlates()
	{
		return slates;
	}

}

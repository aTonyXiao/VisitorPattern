package hw1;

import java.util.HashMap;

public class SourceObserver implements Observer{
	
	private String name;
	private HashMap<Subject, String> pastStates;
	
	public SourceObserver(String n)
	{
		name = n;
		pastStates = new HashMap<Subject, String>(); 
	}

	@Override
	public void Update(Subject o) {
		if(pastStates.containsKey(o))
		{
			System.out.println(name + " OBSERVED " + o + " LEAVING STATE: " + pastStates.get(o));
		}
		else
		{
			System.out.println(name + " OBSERVED " + o + " LEAVING STATE: UNOBSERVED");

		}
		pastStates.put(o, o.getState());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		
		if(!(obj instanceof SourceObserver))
		{
			return false;
		}
		
		SourceObserver other = (SourceObserver)obj;
		
		return other.name.equals(this.name);
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	@Override
	public String toString()
	{
		return name;
	}

}

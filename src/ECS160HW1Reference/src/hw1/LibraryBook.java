package hw1;
import java.util.*;

public class LibraryBook implements Subject{
	
	private List<Observer> observers;
	private LBState currentState;
	private String name;
	
	public LibraryBook(String n)
	{
		name = n;
		observers = new ArrayList<Observer>();
		currentState = Shelved.getInst();
	}
	
	@Override
	public void attach(Observer obs)
	{
		System.out.println(obs + " is now watching " + name);
		observers.add(obs);
	}
	
	@Override
	public void detach(Observer obs)
	{
		if(observers.contains(obs))
		{
			System.out.println(obs + " is no longer watching " + name);
			observers.remove(obs);
		}
	}
	
	public void Notify()
	{
		for(Observer obs:observers)
		{
			obs.Update(this);
		}
	}
	
	public void returnBook()
	{
		try
		{
			currentState.returnBook(this);
			Notify();
		}
		catch(NotAllowedException e)
		{
			System.out.println(e);
		}
	}
	
	public void borrow()
	{
		try
		{
			currentState.borrow(this);
			Notify();
		}
		catch(NotAllowedException e)
		{
			System.out.println(e);
		}
	}
	
	public void extend()
	{
		try
		{
			currentState.extend(this);
			//We don't call Notify() when moving to the same state again.
		}
		catch(NotAllowedException e)
		{
			System.out.println(e);
		}
	}
	
	public void shelf()
	{
		try
		{
			currentState.shelf(this);
			Notify();
		}
		catch(NotAllowedException e)
		{
			System.out.println(e);
		}
	}
	
	public void setState(LBState newState)
	{
		currentState = newState;
	}
	
	@Override
	public String getState()
	{
		return currentState.toString();
	}
	

	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		
		if(!(obj instanceof LibraryBook))
		{
			return false;
		}
		
		LibraryBook other = (LibraryBook)obj;
		
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

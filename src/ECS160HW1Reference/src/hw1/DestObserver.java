package hw1;

public class DestObserver implements Observer{
	
	private String name;
	
	public DestObserver(String n)
	{
		name = n;
	}

	@Override
	public void Update(Subject o) {
		System.out.println(name + " OBSERVED " + o + " REACHING STATE: " + o.getState());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		
		if(!(obj instanceof DestObserver))
		{
			return false;
		}
		
		DestObserver other = (DestObserver)obj;
		
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

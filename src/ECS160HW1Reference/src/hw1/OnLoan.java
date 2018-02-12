package hw1;

public final class OnLoan implements LBState{
	
	private static OnLoan instance = null;
	
	public synchronized static OnLoan getInst()
	{
		if(instance == null)
		{
			instance = new OnLoan();
		}
		return instance;
	}
	
	private OnLoan()
	{
		System.out.println(this + " Instance Created");
	}

	@Override
	public void returnBook(LibraryBook book) throws NotAllowedException  {
		book.setState(Returned.getInst());
		System.out.println("Leaving state " + this + " for State " + book.getState());
	}

	@Override
	public void borrow(LibraryBook book) throws NotAllowedException 
	{
		throw new NotAllowedException("Can't use borrow in " + this + " state");
		
	}

	@Override
	public void extend(LibraryBook book) throws NotAllowedException  {
		System.out.println("Leaving State " + this + " for State " + this);
		
	}

	@Override
	public void shelf(LibraryBook book) throws NotAllowedException  {
		throw new NotAllowedException("Can't use shelf in " + this + " state.");
	}
	
	@Override
	public String toString()
	{
		return "OnLoan";
	}

}

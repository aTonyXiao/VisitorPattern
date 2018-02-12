package hw1;

public final class Returned implements LBState{
	
	private static Returned instance = null;
	
	public synchronized static Returned getInst()
	{
		if(instance == null)
		{
			instance = new Returned();
		}
		return instance;
	}
	
	private Returned()
	{
		System.out.println(this + " Instance Created");
	}

	@Override
	public void returnBook(LibraryBook book) throws NotAllowedException {
		throw new NotAllowedException("Can't use returnBook in " + this + " state");
		
	}

	@Override
	public void borrow(LibraryBook book) throws NotAllowedException {
		throw new NotAllowedException("Can't use borrow in " + this + " state");
	}

	@Override
	public void extend(LibraryBook book) throws NotAllowedException {
		throw new NotAllowedException("Can't use extend in " + this + " state");

		
	}

	@Override
	public void shelf(LibraryBook book) throws NotAllowedException  {
		book.setState(Shelved.getInst());
		System.out.println("Leaving State " + this + " for State " + book.getState());
	}
	
	@Override
	public String toString()
	{
		return "Returned";
	}

}

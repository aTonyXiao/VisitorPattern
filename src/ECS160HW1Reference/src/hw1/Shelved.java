package hw1;

public final class Shelved implements LBState{
	
	private static Shelved instance = null;
	
	public synchronized static Shelved getInst()
	{
		if(instance == null)
		{
			instance = new Shelved();
		}
		return instance;
	}
	
	private Shelved()
	{
		System.out.println(this + " Instance Created");
	}

	@Override
	public void returnBook(LibraryBook book) throws NotAllowedException {
		throw new NotAllowedException("Can't use returnBook in " + this + " state");
		
	}

	@Override
	public void borrow(LibraryBook book) throws NotAllowedException {
		book.setState(OnLoan.getInst());
		System.out.println("Leaving State " + this + " for State " + book.getState());
	}

	@Override
	public void extend(LibraryBook book) throws NotAllowedException {
		throw new NotAllowedException("Can't use extend in " + this + " state");

	}

	@Override
	public void shelf(LibraryBook book) throws NotAllowedException  {
		throw new NotAllowedException("Can't use shelf in " + this + " state");
	}
	
	@Override
	public String toString()
	{
		return "Shelved";
	}

}

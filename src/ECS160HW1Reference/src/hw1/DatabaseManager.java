package hw1;

/**
 * This example is not accounting for serialization
 * and is not a solution that can be sub-classed.
 * It is thread safe, but has potentially poor performance.
 * It implements a few more conditions than were strictly 
 * required for the assignment.
 * 
 * Notes on the trickiness of implementing Singletons with 
 * Java in real world situations can be found here:
 * https://www.javaworld.com/article/2073352/core-java/simply-singleton.html
 *
 */
public final class DatabaseManager {
	
	private static DatabaseManager instance = null;
	
	public synchronized static DatabaseManager TheDatabaseManager()
	{
		if(instance == null)
		{
			instance = new DatabaseManager();
		}
		else
		{
			System.out.println("Previously Created instance returned");
		}

		return instance;
	}
	
	private DatabaseManager()
	{
		System.out.println("Instance Created");
	}

}

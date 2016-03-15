package thread;

public class Singleton
{
	private static volatile Singleton singleton = null;

	private Singleton()
	{
		// to prevent instantiation via reflection
		if (singleton != null) { throw new InstantiationError("Singleton object can be instantiated more than once!"); }
	}

	public static Singleton getInstance() // double checked locking
	{
		if (singleton == null)
		{
			synchronized (Singleton.class)
			{
				if (singleton == null) // lazy instantiation
					singleton = new Singleton();
			}
		}
		return singleton;
	}

	private Object readResolve()
	{
		return singleton;
	}

	protected void UtilityMethod()
	{
		System.out.println("Singleton's utility method");
	}

	public static void main(String[] args) throws CloneNotSupportedException
	{
		Singleton s = Singleton.getInstance();
		s.UtilityMethod();
	}

}

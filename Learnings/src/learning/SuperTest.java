package learning;

class SuperBase
{
	static final int bfinal;

	static
	{
		bfinal = 10; // can be done only here
		System.out.println("Base static");
	}

	public SuperBase()
	{
		System.out.println("Base constructor");
	}

	{
		System.out.println("base Instance Initializer " + bfinal);
	}
}

public class SuperTest extends SuperBase
{
	final int dfinal;

	static
	{
		System.out.println("Derived static");
	}

	public SuperTest()
	{
		dfinal = 10; // can be done only here
		System.out.println("Derived constructor");
	}

	{
		System.out.println("derived Instance Initializer");
	}

	public static void main(String[] args)
	{
		new SuperTest();
	}
}

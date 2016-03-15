package learning;

class SuperBase
{
	static final int bfinal;
	
	static
	{
		bfinal = 10; // can be done only here
		System.out.println("Super Base static");
	}

	public SuperBase()
	{
		System.out.println("Super Base");
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
		System.out.println("Super Test static");
	}

	public SuperTest()
	{
		dfinal = 10; // can be done only here
		System.out.println("Super Test");
	}

	{
		System.out.println("derived Instance Initializer");
	}

	public static void main(String[] args)
	{
		new SuperTest();
	}
}

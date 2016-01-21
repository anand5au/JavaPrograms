package learning;

class SuperBase
{
	static final int bfinal;  
	
	static
	{
		bfinal = 0;  // can be done only here
		System.out.println("Super Base static");
	}
	
	public SuperBase()
	{
		System.out.println("Super Base");
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
		dfinal = 0; // can be done only here
		System.out.println("Super Test");
	}
	
	{
		System.out.println("Instance Initializer");
	}
	
	public static void main(String[] args)
	{
		new SuperTest();
	}
}

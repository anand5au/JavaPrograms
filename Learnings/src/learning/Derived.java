package learning;

import java.io.FileNotFoundException;
import java.io.IOException;

class Base
{
	public static void testClassMethod()
	{
		System.out.println("The static method in Animal");
	}

	public void testInstanceMethod() throws IOException
	{
		System.out.println("The instance method in Animal");
	}
}

public class Derived extends Base
{

	public static void testClassMethod()
	{
		System.out.println("The static method in Cat");
	}

	public void testInstanceMethod() throws FileNotFoundException
	{
		System.out.println("The instance method in Cat");
	}

	public static void main(String[] args)
	{
		Derived derived = new Derived();
		Base base = new Base();
		Base base1 = derived;
		Derived.testClassMethod();
		Base.testClassMethod();
		try
		{
			derived.testInstanceMethod();
			base.testInstanceMethod();
			base1.testInstanceMethod();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

package learning;

class TestFinallyBlock
{
	public static void main(String args[])
	{
		try
		{
			int data = 25 / 10;
			System.out.println(data);
			// System.exit(0); // no finally
			return; // finally executes
		}
		catch (ArithmeticException e)
		{
			System.out.println("Exception caught");
		}
		finally
		{
			System.out.println("finally block is always executed");
		}
		System.out.println("rest of the code...");
	}
}

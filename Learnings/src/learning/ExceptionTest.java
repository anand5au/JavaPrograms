package learning;

public class ExceptionTest
{
  // this program skips the 1st division in loop that will throw 
  // "/ by 0" exception and continues the execution
  public static void main(String []args)
  {
    int b=100;
    for(int i=0;i<100;i++)
    {
      try
      {
        System.out.println(b/i);
      }
      catch(ArithmeticException e) 
      { 
		System.out.println("Math tutor needed!");
      }
    }
  }
}
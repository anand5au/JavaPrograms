package learning;

import java.lang.reflect.*;

class Student
{
    int num1;
    int num2;
    String str1;
    
    public Student() 
    {
        num1 = 1;
        num2 = 2;
        str1 = "privateStr";
    }
    
    public Student(int a, int b, String str) 
    {
        num1 = a;
        num2 = b;
        str1 = str;
    }
    
    private void privateMethod(int a)
    {
        System.out.println("Private Method! " + a);
    }
    
    public void publicMethod()
    {
        System.out.println("Public Method!");        
    }
    
    public static void staticMethod()
    {
        System.out.println("Static Method!");
    }
}

public class Reflection
{
	public static void main(String[] args)
	{
	      try 
	      {
	          Class s = Student.class;
	          System.out.println(s.getName() + " " + s.getSuperclass());
	          
	          /* Method[] methods = s.getMethods();
	          Constructor[] cons = s.getDeclaredConstructors();
	          for(Method m : methods)
	          {
	              System.out.println(m.getName());
	              m.invoke(s.newInstance());
	          }
	          for(Constructor m : cons)
	          {
	              System.out.println(m.getName());
	          } */
	          
	          Method m = s.getDeclaredMethod("privateMethod", Integer.TYPE);
	          m.setAccessible(true);
	          m.invoke(s.newInstance(), 123);
	          
	          Field f1 = s.getDeclaredField("str1");
	          f1.setAccessible(true);
	          Object o = s.newInstance();
	          f1.set(o,"newValue");
	          System.out.println(f1.get(o));
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();    
	      }
	}
}

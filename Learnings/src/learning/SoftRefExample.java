package learning;

import java.lang.ref.*;

public class SoftRefExample
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Object obj = new Object();
	    //Reference<Object> ref = new SoftReference<Object>(obj);
	    Reference<Object> ref = new WeakReference<Object>(obj);
	    
	    obj = null;
	    System.gc();
	    
	    System.out.println("before max memory allocation: " + ref.get());
	    
	    try 
	    {
	    	System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
	        Object[] maxMemory = new Object[(int) Runtime.getRuntime().maxMemory()];
	    } 
	    catch (Throwable e) { } 

	    System.out.println("after max memory allocation: " + ref.get());
	    System.out.println();
	}

}

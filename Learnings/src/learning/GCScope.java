package learning;

import java.lang.ref.WeakReference;

public class GCScope 
{
	GCScope t;
    int i;

    public GCScope(int i)
    {
    	this.i = i;
    }
    
	public static void main(String args[]) 
	{
		GCScope t1 = new GCScope(1);
		GCScope t2 = new GCScope(2);
		GCScope t3 = new GCScope(3);

		// No Object Is Eligible for GC

		t1.t = t2; // No Object Is Eligible for GC
		t2.t = t3; // No Object Is Eligible for GC
		t3.t = t1; // No Object Is Eligible for GC
		
		System.out.println("First attempt to GC");
		t1 = null;
		// No Object Is Eligible for GC (t3.t still has a reference to t1)
		System.gc();
		
		System.out.println("Second attempt to GC");
		t2 = null;		
		// No Object Is Eligible for GC (t3.t.t still has a reference to t2)
		System.gc();

		System.out.println("Third attempt to GC");
		t3 = null;
		// All the 3 Object Is Eligible for GC (None of them have a reference.
		// only the variable t of the objects are referring each other in a
		// rounded fashion forming the Island of objects with out any external
		// reference)
		System.gc();
		
		String str = new String("hello world");
		WeakReference<String> ref = new WeakReference<String>(str); // ref count for str not incremented
		// String s1 = str; // ref count for str increases
		str = null;
	    if (ref != null) 
	    {
	    	// if there is no weak ref and only s1 was present, then making str=null 
	    	// doesnt garbage collect s1 as it is strong ref.
	        
	    	System.gc(); // if s1 is uncommented, then ref wont be garbage collected
	        System.out.println(str + " " + ref.get());
	    }
	}

	protected void finalize() 
	{
		System.out.println("Garbage collected from object" + i);
	}
}
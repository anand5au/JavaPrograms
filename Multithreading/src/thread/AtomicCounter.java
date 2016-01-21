package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter 
{
	private AtomicInteger a;
	
	public void Increment()
	{
		a.incrementAndGet();
	}
	
	public void decrement()
	{
		a.decrementAndGet();
	}
	
	public int getA()
	{
		return a.get();
	}
}

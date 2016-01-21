package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample 
{
	static class Problem
	{
		final String cause;
		final Lock Lock = new ReentrantLock();
		
		public Problem(String str)
		{
			cause = str;
		}
		
		public String getCause()
		{
			return this.cause;
		}
		
		public boolean CanTrigger(Problem p)
		{
			boolean myLock = Lock.tryLock();
			boolean yourLock = p.Lock.tryLock();
			
			if(!myLock && !yourLock)
				System.out.println("Both Lock failed");
			else if(!myLock)
				p.Lock.unlock();
			else if(!yourLock)
				Lock.unlock();
				
			return(myLock && yourLock);
		}
		
		public synchronized void trigger1(Problem p)
		{
			if(CanTrigger(p))
			{
				System.out.println(Thread.currentThread().getName() + ":" + cause + " trigger1 " + p.getCause());
				p.trigger2(this);
			}
		}
		
		public synchronized void trigger2(Problem p)
		{
			System.out.println(Thread.currentThread().getName() + ":" + cause + " trigger2 " + p.getCause());
			Lock.unlock();
			p.Lock.unlock();
		}
	}
	
	
	public static void main(String[] args) 
	{
		final Problem p1 = new Problem("Problem-A");
		final Problem p2 = new Problem("Problem-B");
		
		Thread t2 = new Thread(new ProblemLoop(p2, p1),"L1");
		t2.start();
		Thread t1 = new Thread(new ProblemLoop(p1,p2),"L2");
		t1.start();
	}
}

class ProblemLoop implements Runnable
{
	private LockExample.Problem p1;
	private LockExample.Problem p2;
	
	public ProblemLoop(LockExample.Problem p1, LockExample.Problem p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void run()
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName());
			p1.trigger1(p2);
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException ex) {}
		}
	}
}
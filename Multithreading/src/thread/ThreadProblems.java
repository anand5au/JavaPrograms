package thread;

public class ThreadProblems 
{
	static class Problem
	{
		final String cause;
		public Problem(String str)
		{
			cause = str;
		}
		
		public String getCause()
		{
			return this.cause;
		}
		
		public synchronized void trigger1(Problem p)
		{

			System.out.println(cause + " trigger1 " + p.getCause());
			
			try {
			Thread.sleep(1000);
			}
			catch(InterruptedException  ex) { return; }
			System.out.println(cause + " waiting on " + p.getCause());
			p.trigger2(this);
		}
		
		public synchronized void trigger2(Problem p)
		{
			System.out.println(cause + " trigger2 " + p.getCause());
		}
	}
	
	public static void main(String[] args) 
	{
		Problem p1 = new Problem("Problem-A");
		Problem p2 = new Problem("Problem-B");
		
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		
		Thread t1 = new Thread(new Runnable()
					{
						public void run()
						{
							p1.trigger1(p2);
						}
					});

		t1.start();
		
		Thread t2 = new Thread(new Runnable()
					{
						public void run()
						{
							p2.trigger1(p1);
						}
					});
				
		t2.start();
	}

}

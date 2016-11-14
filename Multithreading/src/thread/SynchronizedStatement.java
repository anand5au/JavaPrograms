package thread;

class t1 implements Runnable
{
	SynchronizedStatement obj;

	public t1(SynchronizedStatement s)
	{
		obj = s;
	}

	@Override
	public void run()
	{
		obj.func1();
	}

}

class t2 implements Runnable
{
	SynchronizedStatement obj;

	public t2(SynchronizedStatement s)
	{
		obj = s;
	}

	@Override
	public void run()
	{
		obj.func2();
	}

}

public class SynchronizedStatement
{
	int shared;

	public void func1()
	{
		// synchronized statement locks the object passed to it. So whenever
		// some other thread tries to lock the same object it should wait for
		// the synchronized block to end
		synchronized (this)
		{
			shared++;

			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("After increment " + shared);
	}

	public void func2()
	{
		synchronized (this)
		{
			shared--;
		}
		System.out.println("After decrement " + shared);
	}

	public static void main(String[] args)
	{
		SynchronizedStatement s = new SynchronizedStatement();
		t1 c1 = new t1(s);
		t2 c2 = new t2(s);
		Thread tt1 = new Thread(c1);
		Thread tt2 = new Thread(c2);
		tt1.start();
		tt2.start();
	}
}

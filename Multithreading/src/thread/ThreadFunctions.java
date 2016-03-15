package thread;

public class ThreadFunctions implements Runnable
{
	private String name;

	public ThreadFunctions(String name)
	{
		this.name = name;
	}

	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			// handling the interrupt.
			// It can also be handled by calling a method that throws
			// InterruptedException
			// Eg. sleep(), join()
			if (Thread.interrupted())
			{
				System.out.println(name + " is interrupted!");
				return;
			}
			System.out.println(name + " loop number: " + i);
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Mai thread priority: " + Thread.currentThread().getPriority());

		Thread t1 = new Thread(new ThreadFunctions("Thread-A"));
		System.out.println("Priority of Thread-A: " + t1.getPriority());
		t1.start();

		Thread.sleep(1);
		System.out.println("Interrupting Thread-A");
		// interrupts t1 given that t1 has handled the interrupt.
		t1.interrupt();

		try
		{
			t1.join(); // main thread waits for t1 to complete.
		}
		catch (InterruptedException ex)
		{
			return;
		}

		Thread t2 = new Thread(new ThreadFunctions("Thread-B"));
		System.out.println("Priority of Thread-B: " + t2.getPriority());
		t2.start();

		// main thread waits for all other threads of same priority
		// added at the end of queue for same priority
		// OS can ignore this call (which happens most of the times!)
		Thread.yield();

		for (int i = 0; i < 100; i++)
		{
			System.out.println("Main Thread loop number " + i);
		}

	}
}

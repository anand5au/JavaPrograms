package thread;

import java.util.Random;

class GaurdedBlock
{
	private String resource;
	private boolean available;

	public GaurdedBlock(String str, boolean avail)
	{
		resource = str;
		available = avail;
	}

	public GaurdedBlock()
	{
		available = false;
	}

	public synchronized void produce(String res)
	{
		if (available)
		{
			try
			{
				System.out.println("producer waiting...");
				wait();
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		System.out.println("Resource consumed, so producing: " + res);
		resource = res;
		available = true;
		notifyAll();
	}

	public synchronized String consume()
	{
		if (!available)
		{
			try
			{
				System.out.println("consumer waiting...");
				wait();
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		available = false;
		notifyAll();
		return resource;
	}
}

class Producer implements Runnable
{
	GaurdedBlock g;

	public Producer(GaurdedBlock g)
	{
		this.g = g;
	}

	public void run()
	{
		String[] resources = { "anand", "kumar", "dhandapani" };
		for (int i = 0; i < 3; i++)
		{
			g.produce(resources[i]);
			try
			{
				Thread.sleep(new Random().nextInt(5000));
			}
			catch (InterruptedException ex)
			{
			}
		}
		g.produce("done");
	}
}

class Consumer implements Runnable
{
	GaurdedBlock g;

	public Consumer(GaurdedBlock g)
	{
		this.g = g;
	}

	public void run()
	{
		System.out.println("Running Cosumer");
		for (String s = g.consume(); !s.equalsIgnoreCase("done"); s = g.consume())
		{
			System.out.println("Resource produced, so consuming resource: " + s);
			try
			{
				Thread.sleep(new Random().nextInt(5000));
			}
			catch (InterruptedException ex)
			{
			}
		}
	}
}

public class GaurdedBlockTest
{

	public static void main(String[] args)
	{
		// GaurdedBlock g = new GaurdedBlock("Empty_Resource",true);
		GaurdedBlock g = new GaurdedBlock();
		new Thread(new Producer(g)).start();
		new Thread(new Consumer(g)).start();
	}

}

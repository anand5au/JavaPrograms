package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdConBlockingQ
{

	public static void main(String[] args)
	{
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);
		new Thread(new ProducerBQ(bq)).start();
		new Thread(new ConsumerBQ(bq)).start();
	}

}

class ProducerBQ implements Runnable
{
	BlockingQueue<String> bq;

	public ProducerBQ(BlockingQueue<String> q)
	{
		bq = q;
	}

	public void run()
	{

		for (int i = 0; i < 12; i++)
		{
			try
			{
				System.out.println("Producer Started");
				bq.put("resource" + i);
				System.out.println("Produced Resource " + i);
			}
			catch (InterruptedException ex)
			{
			}
		}
	}
}

class ConsumerBQ implements Runnable
{
	BlockingQueue<String> bq;

	public ConsumerBQ(BlockingQueue<String> q)
	{
		bq = q;
	}

	public void run()
	{

		for (int i = 0; i < 12; i++)
		{
			try
			{
				System.out.println("Consumer Started");
				System.out.println("Consumed " + bq.take());
			}
			catch (InterruptedException ex)
			{
			}
		}
	}
}

package learning;

import java.lang.ref.*;
import java.util.*;

public final class ReferencesTest
{

	// if true, program will test Soft references
	private static boolean testSoft = false;

	// if true, program will test resurrection in finalize()
	private static boolean testResurrection = false;

	private static volatile Object strong;
	private static volatile Reference<?> soft, weak, phantom;

	private static void pass(String descr, Runnable command) throws InterruptedException
	{
		System.out.println();
		System.out.println(descr);

		if (command != null)
			command.run();

		System.gc();
		Thread.sleep(100);
	}

	private static void fillMemory()
	{
		try
		{
			List<Object> list = new ArrayList<>();
			while (true)
				list.add(new byte[10000]);
		}
		catch (OutOfMemoryError ex)
		{
			System.out.println("  Memory full");
		}
	}

	public static void main(String[] args) throws Exception
	{

		final ReferenceQueue<Object> queue = new ReferenceQueue<>();

		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (!Thread.currentThread().isInterrupted())
				{
					try
					{
						Reference<?> ref = queue.remove();
						System.out.println("  Enqueued: " + ref);
					}
					catch (InterruptedException ex)
					{
						Thread.currentThread().interrupt();
					}
				}
			}
		});

		thread.start();

		strong = new Object()
		{
			@Override
			protected void finalize() throws Throwable
			{
				System.out.println("  Object finalized");

				if (testResurrection)
				{
					strong = this;
					weak = new MyWeakReference(strong, queue);
					if (testSoft)
						soft = new MySoftReference(strong, queue);
				}
			}
		};

		phantom = new MyPhantomReference(strong, queue);
		weak = new MyWeakReference(strong, queue);
		if (testSoft)
			soft = new MySoftReference(strong, queue);

		System.out.println();
		System.out.println("Start:");
		System.out.println("  " + soft);
		System.out.println("  " + weak);
		System.out.println("  " + phantom);

		pass("Clear variable and first GC pass:", new Runnable()
		{
			public void run()
			{
				strong = null;
			}
		});

		pass("Fill memory and second GC pass:", new Runnable()
		{
			public void run()
			{
				fillMemory();
			}
		});

		pass("Third GC pass:", null);

		if (testResurrection)
		{
			pass("Kill resurrected object and fourth GC pass:", new Runnable()
			{
				public void run()
				{
					strong = null;
				}
			});

			pass("Fill memory and fifth GC pass:", new Runnable()
			{
				public void run()
				{
					fillMemory();
				}
			});

			pass("Sixth GC pass:", null);
		}

		thread.interrupt();
	}

	private static final class MySoftReference extends SoftReference<Object>
	{

		private MySoftReference(Object referent, ReferenceQueue<Object> queue)
		{
			super(referent, queue);
		}

		public String toString()
		{
			return "Soft --> " + get();
		}
	}

	private static final class MyWeakReference extends WeakReference<Object>
	{

		private MyWeakReference(Object referent, ReferenceQueue<Object> queue)
		{
			super(referent, queue);
		}

		public String toString()
		{
			return "Weak --> " + get();
		}
	}

	private static final class MyPhantomReference extends PhantomReference<Object>
	{

		private MyPhantomReference(Object referent, ReferenceQueue<Object> queue)
		{
			super(referent, queue);
		}

		public String toString()
		{
			return "Phantom --> " + get();
		}
	}
}

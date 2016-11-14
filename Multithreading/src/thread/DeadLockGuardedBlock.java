package thread;

class DeadLockGuardedBlock
{
	static class Friend
	{
		private final String name;

		public volatile boolean bowing;
		// if bowing is not volatile, the thread uses its working copy in
		// "while(bowing);" which will never see the updated value

		public Friend(String name)
		{
			this.name = name;
			bowing = false;
		}

		public String getName()
		{
			return this.name;
		}

		public void bow(Friend bower)
		{
			while (bower.bowing);

			bowing = true;
			System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
			bower.bowBack(this);
			bowing = false;
		}

		public void bowBack(Friend bower)
		{
			System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
		}
	}

	public static void main(String[] args)
	{
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				alphonse.bow(gaston);
			}
		}).start();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				gaston.bow(alphonse);
			}
		}).start();
	}
}

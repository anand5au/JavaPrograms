/*

single expression c++ can be decomposed into three steps:

    Retrieve the current value of c.
    Increment the retrieved value by 1.
    Store the incremented value back in c.

The expression c-- can be decomposed the same way, except that the second step decrements instead of increments.

Suppose Thread A invokes increment at about the same time Thread B invokes decrement. 
If the initial value of c is 0, their interleaved actions might follow this sequence:

    Thread A: Retrieve c.
    Thread B: Retrieve c.
    Thread A: Increment retrieved value; result is 1.
    Thread B: Decrement retrieved value; result is -1.
    Thread A: Store result in c; c is now 1.
    Thread B: Store result in c; c is now -1.

Thread A's result is lost, overwritten by Thread B. This particular interleaving is only one possibility. 
Under different circumstances it might be Thread B's result that gets lost, or there could be no error at all. 
Because they are unpredictable, thread interference bugs can be difficult to detect and fix.

 */


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

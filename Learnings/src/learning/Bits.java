package learning;

public class Bits
{

	public static void main(String[] args)
	{
		int i = -(2 << 30);
		int j = i >> 4; // right shifting -ve numbers add 1 to left after each shift!!!
		
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));	
	}

}

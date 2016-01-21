package learning;

enum Enum
{
	one(1) 
	{
		@Override
		public String color() 
		{
			return "one color";
		}
	},
	two(2) 
	{
		@Override
		public String color() 
		{
			return "two color";
		}
	};
	
	int value;
	public abstract String color();
	
	private Enum(int i) // constructor can only be private; it is used only for initialization
	{
		this.value = i;
	}
}

public class EnumTest
{
	public Enum en;
	
	public EnumTest()
	{
		en = Enum.one;
	}
	
	public static void main(String[] args)
	{
		EnumTest et = new EnumTest();
		System.out.println(et.en.color());
		et.en = Enum.two;
		System.out.println(et.en.color());
		
	}
}
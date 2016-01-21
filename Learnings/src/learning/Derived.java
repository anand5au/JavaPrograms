package learning;

class Base 
{
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    
    public void testInstanceMethod() throws NullPointerException {
        System.out.println("The instance method in Animal");
    }
}

public class Derived extends Base {
	
    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    
    public void testInstanceMethod() throws RuntimeException 
    {
        System.out.println("The instance method in Cat");
    }

    public static void main(String[] args) {
    	Derived derived = new Derived();
        Base base = new Base();
        Base base1 = derived;
        derived.testClassMethod();
        Base.testClassMethod();
        derived.testInstanceMethod();
        base.testInstanceMethod();
        base1.testInstanceMethod();
    }
}

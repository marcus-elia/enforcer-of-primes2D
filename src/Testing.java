public class Testing
{
    public static void main(String[] args)
    {
        double tolerance = 0.01;
        testPositiveInteger(tolerance);
    }

    // ------------------------------------------
    // ==========================================
    //
    //            Positive Integer
    //
    // ==========================================
    // ------------------------------------------

    public static void testPositiveInteger(double tolerance)
    {
        System.out.println("\nTesting the PositiveInteger Class");
        testIsPrime();
        //testNextPrime();
    }

    public static boolean testIsPrime()
    {
        boolean passed = true;
        System.out.println("\nTesting the isPrime() function.");

        boolean obs, exp;

        // p = 2
        obs = PositiveInteger.isPrime(2);
        exp = true;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = 2.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 3
        obs = PositiveInteger.isPrime(3);
        exp = true;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = 3.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 5
        obs = PositiveInteger.isPrime(5);
        exp = true;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = 5.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 11
        obs = PositiveInteger.isPrime(11);
        exp = true;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = 11.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 101
        obs = PositiveInteger.isPrime(101);
        exp = true;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = 101.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 4
        obs = PositiveInteger.isPrime(4);
        exp = false;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 4.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 8
        obs = PositiveInteger.isPrime(8);
        exp = false;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 8.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p = 91
        obs = PositiveInteger.isPrime(91);
        exp = false;
        if(exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 91.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        if(passed)
        {
            System.out.println("All tests passed.");
            return true;
        }
        else
        {
            System.out.println("ERROR!");
            return false;
        }
    }
}

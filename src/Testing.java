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
        testNextPrime();
        testMaxPowerDividing();
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

    public static boolean testNextPrime()
    {
        boolean passed = true;
        System.out.println("\nTesting the nextPrime() function.");

        int obs, exp;

        // n = 2
        obs = PositiveInteger.nextPrime(2);
        exp = 3;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 2.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // n = 3
        obs = PositiveInteger.nextPrime(3);
        exp = 5;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 3.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // n = 4
        obs = PositiveInteger.nextPrime(4);
        exp = 5;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 4.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // n = 44
        obs = PositiveInteger.nextPrime(44);
        exp = 47;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n = 44.");
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

    public static boolean testMaxPowerDividing()
    {
        boolean passed = true;
        System.out.println("\nTesting the maxPowerDividing() function.");

        int exp, obs;
        int p, n;

        // p = n
        p = 2;
        n = 2;
        obs = PositiveInteger.maxPowerDividing(p, n);
        exp = 1;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p = n.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // power = 1
        p = 2;
        n = 6;
        obs = PositiveInteger.maxPowerDividing(p, n);
        exp = 1;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of power = 1.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // power = 2
        p = 2;
        n = 12;
        obs = PositiveInteger.maxPowerDividing(p, n);
        exp = 2;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of power = 2.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // n is a power of p
        p = 3;
        n = 243;
        obs = PositiveInteger.maxPowerDividing(p, n);
        exp = 5;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of n is a power of p.");
            System.out.println("Expected " + exp + ", observed " + obs);
        }

        // p does not divide n
        p = 5;
        n = 28;
        obs = PositiveInteger.maxPowerDividing(p, n);
        exp = 0;
        if (exp != obs)
        {
            passed = false;
            System.out.println("FAILED test case of p does not divide n.");
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

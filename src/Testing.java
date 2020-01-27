import java.util.HashMap;

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
        testFactorNumber();
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

    public static boolean testFactorNumber()
    {
        boolean passed = true;
        System.out.println("\nTesting the factorNumber() function.");

        HashMap<Integer, Integer> factorization;

        // Factoring 2
        int n = 2;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(2) != 1 || factorization.containsKey(3))
        {
            passed = false;
            System.out.println("FAILED test case of n = 2.");
        }

        // Factoring 11
        n = 11;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(11) != 1 || factorization.containsKey(2) || factorization.containsKey(3))
        {
            passed = false;
            System.out.println("FAILED test case of n = odd prime.");
        }

        // Factoring 2p
        n = 26;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(2) != 1 || factorization.containsKey(5) || factorization.get(13) != 1)
        {
            passed = false;
            System.out.println("FAILED test case of n = 2 * odd prime.");
        }

        // Factoring power of 2
        n = 64;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(2) != 6 || factorization.containsKey(3) || factorization.containsKey(4))
        {
            passed = false;
            System.out.println("FAILED test case of n = power of 2.");
        }

        // Factoring p * p
        n = 91;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(7) != 1 || factorization.containsKey(2) || factorization.get(13) != 1)
        {
            passed = false;
            System.out.println("FAILED test case of n = p * p.");
        }

        // Factoring number with many prime factors
        n = 300;
        factorization = PositiveInteger.factorNumber(n);
        if(factorization.get(2) != 2 || factorization.get(3) != 1 || factorization.get(5) != 2)
        {
            passed = false;
            System.out.println("FAILED test case of n has many prime factors.");
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

import java.util.HashMap;

/*
This class stores a positive integer and its prime factorization, along with
all math helper methods.
 */

public class PositiveInteger
{
    private int value;
    private boolean isPrime;
    private HashMap<Integer, Integer> factorization;

    public PositiveInteger(int inputValue)
    {
        value = inputValue;
        isPrime = PositiveInteger.isPrime(value);
        factorization = PositiveInteger.factorNumber(value);
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Getters
    //
    // ==========================================
    // ------------------------------------------
    public int getValue()
    {
        return value;
    }
    public boolean getIsPrime()
    {
        return isPrime;
    }
    public HashMap<Integer, Integer> getFactorization()
    {
        return factorization;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------

    // Call this setter when you have already called divideBy(), meaning the
    // factorization was already updated
    public void setValue(int input)
    {
        value = input;
        isPrime = PositiveInteger.isPrime(value);
    }
    public void setValueAndUpdateFactorization(int input)
    {
        value = input;
        isPrime = PositiveInteger.isPrime(value);
        factorization = PositiveInteger.factorNumber(value);
    }



    // ------------------------------------------
    // ==========================================
    //
    //                 Math
    //
    // ==========================================
    // ------------------------------------------

    public static boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
        {
            return true;
        }
        if(n % 2 == 0)
        {
            return false;
        }
        int upperBound = (int)Math.floor(Math.sqrt(n)) + 1;
        for(int i = 3; i < upperBound; i += 2)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    // Integer exponentiation
    public static int exp(int a, int m)
    {
        if(m == 0)
        {
            return 1;
        }
        return a * exp(a, m-1);
    }


    // Returns the largest power a of p such that p^a divides n
    // For example, maxPowerDividing(3, 36) = 2.
    public static int maxPowerDividing(int p, int n) {
        int pow = 0;
        while (n % p == 0) {
            pow++;
            n /= p;
        }
        return pow;
    }

    // Return the smallest prime integer that is greater than n
    // (not including n)
    public static int nextPrime(int n)
    {
        int cur = n + 1;
        while(!PositiveInteger.isPrime(cur))
        {
            cur++;
        }
        return cur;
    }

    public static HashMap<Integer, Integer> factorNumber(int n)
    {
        HashMap<Integer, Integer> factorization = new HashMap<Integer, Integer>();
        int p = 2;
        int pow;
        while(n > 1)
        {
            pow = PositiveInteger.maxPowerDividing(p, n);
            if(pow != 0)
            {
                factorization.put(p, pow);
                n = n / exp(p, pow);
            }
            p = PositiveInteger.nextPrime(p);
        }
        return factorization;
    }

    // This tries to divide a power of n from the factorization of this positive integer.
    // Returns true if n divides value, false otherwise.
    // Note: n must be a prime.
    public boolean divideBy(int n)
    {
        if(factorization.containsKey(n))
        {
            // If we are dividing out the last power of n, remove it from the factorization
            if(factorization.get(n) == 1)
            {
                factorization.remove(n);
            }
            // Otherwise, just subtract 1 from n's power
            else
            {
                factorization.replace(n, factorization.get(n) - 1);
            }
            this.setValue(value / n);
            return true;
        }
        return false;
    }



    // ------------------------------------------
    // ==========================================
    //
    //                Output
    //
    // ==========================================
    // ------------------------------------------
    public String toString()
    {
        return Integer.toString(value);
    }
}

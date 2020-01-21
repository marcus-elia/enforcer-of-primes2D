import java.awt.*;

// The Number class is the enemy, a circle with a number on it

public class Number extends ActiveCircle
{
    private int number;
    private boolean isPrime;
    private int fontSize;  // changes based on the number of digits

    // Where it is trying to go
    private Point target;

    public Number(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                  double inputCurSpeed, double inputMaxSpeed, double inputAcceleration,
                  int inputNumber)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
        number = inputNumber;
        fontSize = Number.getFontSize(number);
    }

    @Override
    public void tick() {

    }


    // ------------------------------------------
    // ==========================================
    //
    //                Rendering
    //
    // ==========================================
    // ------------------------------------------

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.RED);
        g2d.fill(circle);
        this.drawNumber(g2d);
    }

    // Determine what the font size should be based on how many digits long the number is
    public static int getFontSize(int n)
    {
        if(n < 10)
        {
            return 32;
        }
        else if(n < 100)
        {
            return 24;
        }
        else if(n < 1000)
        {
            return 18;
        }
        return -1;
    }

    public void drawNumber(Graphics2D g2d)
    {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        int pixelLength = g2d.getFontMetrics().stringWidth(Integer.toString(number));
        g2d.drawString(Integer.toString(number), (int)center.x - pixelLength/2, (int)center.y + 5);
    }

    @Override
    public void removeSelf() {

    }

    // ------------------------------------------
    // ==========================================
    //
    //                 Getters
    //
    // ==========================================
    // ------------------------------------------
    public Point getTarget()
    {
        return target;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setTarget(Point input)
    {
        target = input;
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
}

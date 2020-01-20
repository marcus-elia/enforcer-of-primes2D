import java.awt.*;

public class GameManager
{
    private int width, height;

    public GameManager(int inputWidth, int inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
    }

    public void tick()
    {

    }
    public void render(Graphics2D g2d)
    {

    }

    // ==========================================
    //
    //                Getters
    //
    // ==========================================
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }

    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    public void setWidth(int input)
    {
        width = input;
    }
    public void setHeight(int input)
    {
        height = input;
    }
}

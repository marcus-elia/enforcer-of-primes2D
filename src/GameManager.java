import java.awt.*;
import java.util.ArrayList;

public class GameManager
{
    private int width, height;

    private GameMode currentMode;

    private ArrayList<GameObject> gameObjects;

    private Enforcer enforcer;

    public GameManager(int inputWidth, int inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
        currentMode = GameMode.Playing;
        gameObjects = new ArrayList<GameObject>();
        this.initializeEnforcer();
    }

    public void tick()
    {
        if(currentMode == GameMode.Playing)
        {
            for(GameObject obj : gameObjects)
            {
                obj.tick();
            }
        }
    }
    public void render(Graphics2D g2d)
    {
        if(currentMode == GameMode.Playing)
        {
            for(GameObject obj : gameObjects)
            {
                obj.render(g2d);
            }
        }
    }

    // ------------------------------------------
    // ==========================================
    //
    //         Initialization Functions
    //
    // ==========================================
    // ------------------------------------------

    public void initializeEnforcer()
    {
        enforcer = new Enforcer(this, new Point(width / 2, height / 2),
                32, 0, 0, 2, .1, 96);
        gameObjects.add(enforcer);
    }



    // ------------------------------------------
    // ==========================================
    //
    //                Getters
    //
    // ==========================================
    // ------------------------------------------
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public GameMode getCurrentMode()
    {
        return currentMode;
    }
    public ArrayList<GameObject> getGameObjects()
    {
        return gameObjects;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setWidth(int input)
    {
        width = input;
    }
    public void setHeight(int input)
    {
        height = input;
    }
    public void setCurrentMode(GameMode input)
    {
        currentMode = input;
    }
}

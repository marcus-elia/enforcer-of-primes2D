import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private GameManager manager;

    // Is the user holding down a WASD key?
    private boolean leftArrowKey;
    private boolean rightArrowKey;
    private boolean upArrowKey;
    private boolean downArrowKey;

    public KeyInput(GameManager inputManager)
    {
        manager = inputManager;

        leftArrowKey = false;
        rightArrowKey = false;
        upArrowKey = false;
        downArrowKey = false;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A)
        {
            leftArrowKey = true;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_D)
        {
            rightArrowKey = true;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_W)
        {
            upArrowKey = true;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_S)
        {
            downArrowKey = true;
            setManagerKeyDirection();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A)
        {
            leftArrowKey = false;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_D)
        {
            rightArrowKey = false;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_W)
        {
            upArrowKey = false;
            setManagerKeyDirection();
        }
        if(key == KeyEvent.VK_S)
        {
            downArrowKey = false;
            setManagerKeyDirection();
        }
    }


    public void setManagerKeyDirection()
    {
        if(upArrowKey || downArrowKey || leftArrowKey || rightArrowKey)
        {
            // Up
            if((upArrowKey && !leftArrowKey && !downArrowKey && !rightArrowKey) ||
                    (upArrowKey && leftArrowKey && !downArrowKey && rightArrowKey))
            {
                manager.setCurrentKeyDirection(KeyDirection.Up);
            }
            // Down
            else if((!upArrowKey && !leftArrowKey && downArrowKey && !rightArrowKey) ||
                    (!upArrowKey && leftArrowKey && downArrowKey && rightArrowKey))
            {
                manager.setCurrentKeyDirection(KeyDirection.Down);
            }
            // Left
            else if((!upArrowKey && leftArrowKey && !downArrowKey && !rightArrowKey) ||
                    (upArrowKey && leftArrowKey && downArrowKey && !rightArrowKey))
            {
                manager.setCurrentKeyDirection(KeyDirection.Left);
            }
            // Right
            else if((!upArrowKey && !leftArrowKey && !downArrowKey && rightArrowKey) ||
                    (upArrowKey && !leftArrowKey && downArrowKey && rightArrowKey))
            {
                manager.setCurrentKeyDirection(KeyDirection.Right);
            }
            // UpLeft
            else if(upArrowKey && leftArrowKey && !downArrowKey) // don't need to check right
            {
                manager.setCurrentKeyDirection(KeyDirection.UpLeft);
            }
            // DownLeft
            else if(!upArrowKey && leftArrowKey && downArrowKey) // don't need to check right
            {
                manager.setCurrentKeyDirection(KeyDirection.DownLeft);
            }
            // DownRight
            else if(!upArrowKey && !leftArrowKey && downArrowKey) // don't need to check right
            {
                manager.setCurrentKeyDirection(KeyDirection.DownRight);
            }
            // UpRight
            else if(upArrowKey && !leftArrowKey && !downArrowKey) // don't need to check right
            {
                manager.setCurrentKeyDirection(KeyDirection.UpRight);
            }
            else
            {
                manager.setCurrentKeyDirection(KeyDirection.None);
            }
        }
        else
        {
            manager.setCurrentKeyDirection(KeyDirection.None);
        }
    }
}
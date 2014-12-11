import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;

public class FP_Panar implements FP_Moving_Object
{

   //since the game is updated every 60 seconds, we subtract 1/60 * the acceleration amount every time the move function is called
   public final float  GRAVITY = 9.8f;
   public final int  TERMINAL_VELOCITY = 300;
   public final int CLICK_VELOCITY = -6;
   private float velocity;

   private int HEIGHT = 75;
   private int WIDTH = 75;
   private Image face_of_the_all_knowing;
   private Rectangle bounding_box;
   private float xPos;
   private float yPos;
   private boolean movingUp;

   public FP_Panar()
   {
      bounding_box = new Rectangle(0, 0, HEIGHT, WIDTH);
      velocity = 0;
   }

   public FP_Panar(int x, int y)
   {
      this();
      xPos = x;
      yPos = y;
      bounding_box.setLocation((int)xPos, (int)yPos);
   }

   public void move()
   {
      velocity += (GRAVITY * 1/60);
      yPos += velocity;
      bounding_box.setLocation((int)xPos, (int)yPos);
   }

   public void draw(Graphics2D g2)
   {
      g2.draw(bounding_box);
   }

   public boolean intersects(FP_Moving_Object o)
   {
      return bounding_box.intersects(o.get_bounding_box());
   }

   public Rectangle2D get_bounding_box()
   {
      return bounding_box;
   }
   public void recieveClick()
   {
      System.out.println("Panar recieved click!1!1!1!");
      velocity = CLICK_VELOCITY;
   }
}

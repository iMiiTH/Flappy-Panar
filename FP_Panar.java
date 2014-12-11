import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;

public class FP_Panar implements FP_Moving_Object
{
   private Image face_of_the_all_knowing;
   private Rectangle bounding_box;
   private int xPos;
   private int yPos;

   public FP_Panar()
   {
      bounding_box = new Rectangle(0, 0);
   }

   public FP_Panar(int x, int y)
   {
      xPos = x;
      yPos = y;
   }

   public void move()
   {
   }

   public void draw(Graphics2D g2)
   {
   }

   public boolean intersects(FP_Moving_Object o)
   {
      return bounding_box.intersects(o.get_bounding_box());
   }

   public Rectangle2D get_bounding_box()
   {
      return bounding_box;
   }
}

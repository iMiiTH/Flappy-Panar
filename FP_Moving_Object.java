import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface FP_Moving_Object
{
   void move();
   void draw(Graphics2D g2);
   boolean intersects(FP_Moving_Object o);
   Rectangle2D[] get_bounding_box();
}

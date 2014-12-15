import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class FP_Sequence_Diagram implements FP_Moving_Object
{
   private Rectangle top_activation_box;
   private Rectangle bottom_activation_box;
   private Stroke lifeline_stroke;
   private Stroke activation_box_stroke;

   private int xPos;
   private int yPos;

   private final int HEIGHT = 200; 
   private final int WIDTH = 50;
   private final int BOX_SEPARATION_DISTANCE = 450; // remember it's the number here - HEIGHT

   public FP_Sequence_Diagram()
   {
      Random ran = new Random();
      xPos = FP_Viewer.WIDTH;
      yPos = ran.nextInt(200) - 200;

      top_activation_box = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
      bottom_activation_box = new Rectangle(xPos, yPos+BOX_SEPARATION_DISTANCE, WIDTH, HEIGHT);

      lifeline_stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
      activation_box_stroke = new BasicStroke();
   }

   public void move()
   {
      xPos -= 3;
      updatePositionsOfElements();
   }

   private void updatePositionsOfElements()
   {
      top_activation_box.setLocation(xPos, yPos);
      bottom_activation_box.setLocation(xPos, yPos+BOX_SEPARATION_DISTANCE);
   }

   public void draw(Graphics2D g2)
   {
      g2.setStroke(lifeline_stroke);
      g2.setColor(Color.BLACK);
      g2.drawLine(xPos+WIDTH/2, 0, xPos+WIDTH/2, FP_Viewer.HEIGHT);

      g2.setColor(Color.WHITE);
      g2.fill(top_activation_box);
      g2.fill(bottom_activation_box);

      g2.setStroke(activation_box_stroke);
      g2.setColor(Color.BLACK);
      g2.draw(top_activation_box);
      g2.draw(bottom_activation_box);
   }

   public boolean intersects(FP_Moving_Object o)
   {
      for(Rectangle2D r : o.get_bounding_box() ) {
         if(top_activation_box.intersects(r) || bottom_activation_box.intersects(r) )
            return true;
      } 
      return false;
   }

   public Rectangle2D[] get_bounding_box()
   {
      return new Rectangle2D[] { top_activation_box, bottom_activation_box };
   }

   public boolean inBounds()
   {
      return (xPos < (0 + WIDTH));
   }
}

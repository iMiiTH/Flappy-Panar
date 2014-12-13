import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class FP_Panar implements FP_Moving_Object
{

   //since the game is updated every 60 seconds, we subtract 1/60 * the acceleration amount every time the move function is called
   public static final float  GRAVITY = 9.8f;
   public final int  TERMINAL_VELOCITY = 300;
   public final int CLICK_VELOCITY = -6;
   private final int MAX_Y;
   private float velocity;

   public static final int HEIGHT = 75;
   public static final int WIDTH = 75;
   private BufferedImage face_of_the_all_knowing;
   private BufferedImageOp face_of_the_all_knowing_op;
   private Rectangle bounding_box;
   private float xPos;
   private float yPos;
   private boolean movingUp;

   public FP_Panar()
   {
      MAX_Y = FP_Viewer.HEIGHT - HEIGHT;
      bounding_box = new Rectangle(0, 0, HEIGHT, WIDTH);
      velocity = CLICK_VELOCITY;
      try {
         BufferedImage before = ImageIO.read(new File("panar.jpg"));

         float xTransformation = (float)WIDTH/(float)before.getWidth();
         float yTransformation = (float)HEIGHT/(float)before.getHeight();
         face_of_the_all_knowing = new BufferedImage((int) Math.floor((float)before.getWidth() * xTransformation), (int)Math.floor((float)before.getHeight() * yTransformation), BufferedImage.TYPE_INT_ARGB);

         AffineTransform a = AffineTransform.getScaleInstance(xTransformation, yTransformation);
         face_of_the_all_knowing_op = new AffineTransformOp(a, AffineTransformOp.TYPE_BILINEAR);
         face_of_the_all_knowing = face_of_the_all_knowing_op.filter(before, face_of_the_all_knowing);
      } catch(IOException e) {
         System.out.println("PANAR'S FACE IS MISSING WHAT ARE YOU DOING 0/10 BE HAPPY I DON'T REPORT YOU FOR PLAGARISM.");
         System.exit(1);
      }
      face_of_the_all_knowing_op = null;
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
      if(yPos > MAX_Y) velocity = CLICK_VELOCITY;
      bounding_box.setLocation((int)xPos, (int)yPos);
   }

   public void draw(Graphics2D g2)
   {
      g2.draw(bounding_box);
      g2.drawImage(face_of_the_all_knowing, face_of_the_all_knowing_op, (int)xPos, (int)yPos);
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

import javax.swing.plaf.LayerUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/* Example imports */
import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Color;

/* Required for blurring */
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class FP_Start_Layer_UI extends LayerUI<JComponent>
{
   public static final String start_string = "Click to start the rocket science!";
   private BufferedImage mOffscreenImage;
   private BufferedImageOp mOperation;

   public FP_Start_Layer_UI()
   {
      float ninth = 1.0f / 9.0f;
      float[] blurKernel = {
         ninth, ninth, ninth,
         ninth, ninth, ninth,
         ninth, ninth, ninth
      };
      mOperation = new ConvolveOp(
            new Kernel(3, 3, blurKernel),
            ConvolveOp.EDGE_NO_OP, null);
   }

   public void paint(Graphics g, JComponent c)
   {
      super.paint(g, c);

      Graphics2D g2 = (Graphics2D) g.create();

      g2.drawString(start_string, 100, 100);

      int w = c.getWidth();
      int h = c.getHeight();
      g2.setComposite(AlphaComposite.getInstance(
               AlphaComposite.SRC_OVER, .5f));
      g2.setPaint(new GradientPaint(0, 0, Color.green, 0, h, Color.yellow));
      g2.fillRect(0, 0, w, h);

      g2.dispose();
   }
}

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
import java.awt.event.MouseEvent;
import javax.swing.JLayer;

/**
 * Divided into 5 phases of colour changes, this layer draws horrible hideous and eye bunring colours on the game to make it horrible to look at.
 */

public class FP_Start_Layer_UI extends LayerUI<JComponent>
{
   int phase;
   int phase_counter;
   private Color top;
   private Color bottom;
   public FP_Start_Layer_UI()
   {
      phase = 1;
      phase_counter = 0;

      top = Color.green;
      bottom = Color.yellow;
      System.out.println(top+" "+bottom);
   }

   protected void processMouseEvent(MouseEvent e, JLayer<? extends JComponent> l)
   {
      System.out.println(e);
   }

   public void paint(Graphics g, JComponent c)
   {
      super.paint(g, c);

      if(phase_counter == 255){
         phase_counter = 0;
         if(phase == 4) 
            phase = 1;
         else 
            phase++;
      }

      switch(phase) {
         case 1: 
            top = new Color(top.getRed()+1, top.getGreen(), top.getBlue());
            bottom = new Color(bottom.getRed()-1, bottom.getGreen()-1, bottom.getBlue()+1);
            phase_counter++;
            break; 
         case 2:
            top = new Color(top.getRed()-1, top.getGreen()-1, top.getBlue()+1);
            bottom = new Color(bottom.getRed()+1, bottom.getGreen(), bottom.getBlue()-1);
            phase_counter++;
            break;
         case 3:
            top = new Color(top.getRed()+1, top.getGreen(), top.getBlue()-1);
            bottom = new Color(bottom.getRed()-1, bottom.getGreen()+1, bottom.getBlue());
            phase_counter++;
            break;
         case 4:
            top = new Color(top.getRed()-1, top.getGreen()+1, top.getBlue());
            bottom = new Color(bottom.getRed()+1, bottom.getGreen(), bottom.getBlue());
            phase_counter++;
            break;
      }



      Graphics2D g2 = (Graphics2D) g.create();

      int w = c.getWidth();
      int h = c.getHeight();
      g2.setComposite(AlphaComposite.getInstance(
               AlphaComposite.SRC_OVER, .5f));
      g2.setPaint(new GradientPaint(0, 0, top, 0, h, bottom));
      g2.fillRect(0, 0, w, h);

      g2.dispose();
   }
}

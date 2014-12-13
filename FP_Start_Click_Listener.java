import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class FP_Start_Click_Listener extends FP_Click_Listener
{
   public FP_Start_Click_Listener()
   {
   }

   public FP_Start_Click_Listener(FP_Component c)
   {
      parent_component = c;
   }

   public void mousePressed(MouseEvent event)
   {
      System.out.println("Starting Game!");
      parent_component.startGame();
   }
   
   public void setComponent(FP_Component c)
   {
      parent_component = c;
   }
}

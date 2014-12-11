import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class FP_Click_Listener extends MouseAdapter
{
   private FP_Component parent_component;
   public FP_Click_Listener()
   {
   }

   public FP_Click_Listener(FP_Component c)
   {
      parent_component = c;
   }

   public void mousePressed(MouseEvent event)
   {
      System.out.println("Mouse Clicked!");
      parent_component.mouseClicked();
   }

   public void setComponent(FP_Component c)
   {
      parent_component = c;
   }
}

import javax.swing.JFrame;
import javax.swing.JComponent;


public class FP_Frame extends JFrame
{
   private JComponent component;

   public FP_Frame()
   {
      component = new FP_Component();
      this.add(component);
   }
}

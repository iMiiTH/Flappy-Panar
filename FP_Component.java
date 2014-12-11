import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;

public class FP_Component extends JComponent
{
   private JFrame parent_frame;
   private MouseAdapter m_adapter;
   private FP_Moving_Object panar;

   public FP_Component() 
   {
      m_adapter = new FP_Click_Listener();
      this.addMouseListener(m_adapter);
   }
}

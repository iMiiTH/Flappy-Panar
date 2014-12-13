import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;
import javax.swing.JLayer;

public class FP_Frame extends JFrame
{
   private JComponent component;
   private LayerUI <JComponent> component_layer_UI;
   private JLayer<JComponent> component_layer;

   public FP_Frame()
   {
      component = new FP_Component();
      component_layer_UI = new FP_Start_Layer_UI();
      component_layer = new JLayer<JComponent>(component, component_layer_UI);

      this.add(component_layer);
   }
}

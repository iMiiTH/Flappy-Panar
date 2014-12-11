import javax.swing.JFrame;

public class FP_Viewer
{
   public static final int WIDTH = 800;
   public static final int HEIGHT = 600;
   public static void main(String[] args) 
   {
      JFrame frame = new FP_Frame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(WIDTH, HEIGHT);
      frame.setTitle("Flappy Panar");
      frame.setVisible(true);
   }
}

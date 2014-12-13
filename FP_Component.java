import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class FP_Component extends JComponent
{
   private BufferedImage background;
   private boolean playing;
   private Timer frame_counter;
   private Timer sequence_counter;
   private final int DELAY = 16;          //Timer delay in ms.
   private FP_Frame parent_frame;     //The JFrame that owns this
   private MouseAdapter m_adapter;
   private FP_Panar panar;

   public FP_Component() 
   {
      ActionListener taskPerformer = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            moveObjects();
            repaint();
         }
      };

      frame_counter = new Timer(DELAY, taskPerformer);

      m_adapter = new FP_Start_Click_Listener(this);
      this.addMouseListener(m_adapter);
      /*
      m_adapter = new FP_Click_Listener(this);
      this.addMouseListener(m_adapter);
      */


      playing = false;
      panar = new FP_Panar(100, FP_Viewer.HEIGHT / 2 - FP_Panar.HEIGHT / 2 );
   }
   public FP_Component(FP_Frame frame)
   {
      this();
      parent_frame = frame;
   }

   public void startGame()
   {
      playing = true;
      this.removeMouseListener(m_adapter);
      m_adapter = new FP_Click_Listener(this);
      this.addMouseListener(m_adapter);
      frame_counter.start();
   }

   public void mouseClicked()
   {
      panar.recieveClick();
   }

   public void moveObjects()
   {
      panar.move();
   }

   public boolean isPlaying()
   {
      return playing;
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      setBackground(new Color(30, 30, 30));
      Graphics2D g2 = (Graphics2D) g;

      panar.draw(g2);
   }
}

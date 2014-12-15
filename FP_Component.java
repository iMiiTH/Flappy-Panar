import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;
import java.awt.RenderingHints;
import java.util.Iterator;

public class FP_Component extends JComponent
{
   private BufferedImage background;
   private boolean playing;
   private Timer frame_counter;
   private Timer sequence_counter;
   private final int DELAY = 16;          //Timer delay in ms.
   private final int SEQUENCE_COUNTER_FREQUENCY =  120;//this is based on the framerate, so 60 = 1 created every second.
   private int sequence_diagram_counter;;
   private FP_Frame parent_frame;     //The JFrame that owns this
   private MouseAdapter m_adapter;
   private FP_Panar panar;
   private float score = 101.0f;
   private RenderingHints rh;
   private FP_Sound_Box sounds;
   private boolean lose;

   private ArrayList<FP_Sequence_Diagram> sequence_diagrams;

   public FP_Component() 
   {
      lose = false;
      ActionListener taskPerformer = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            sequence_diagram_counter ++;
            if(sequence_diagram_counter >= SEQUENCE_COUNTER_FREQUENCY && !lose) {
               addNewSequenceDiagram();
               sequence_diagram_counter = 0; 
               score -= 1.0f;
               sounds.playSequence();
            }
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


      rh = new RenderingHints(
                         RenderingHints.KEY_TEXT_ANTIALIASING,
                                      RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      sequence_diagrams = new ArrayList<FP_Sequence_Diagram>();
      sequence_diagram_counter = 300;
      playing = false;
      panar = new FP_Panar(100, FP_Viewer.HEIGHT / 2 - FP_Panar.HEIGHT / 2 );
      sounds = new FP_Sound_Box();
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
      sounds.playFlap();
      panar.recieveClick();
   }

   public void moveObjects()
   {
      panar.move();
      Iterator <FP_Sequence_Diagram> it = sequence_diagrams.iterator();
      while( it.hasNext() ) {
         FP_Sequence_Diagram s = it.next();
         s.move();
         if(panar.intersects(s)){
            sounds.playDeath();
            score = -1.0f;
            lose = true;
         }
      }
      if(lose) sequence_diagrams.clear();
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
      g2.setRenderingHints(rh);
      if( !lose) {

         for(FP_Sequence_Diagram s : sequence_diagrams) {
            s.draw(g2);
         }
         panar.draw(g2);

         g2.setColor(Color.BLACK);
         g2.drawString("Mark: "+score+"/100 thx", 10, 10);
      } else {
         g2.setColor(Color.BLACK);
         g2.drawString("Mark: "+score+"/100 thx", 10, 10);
         panar.deathMarch();
         panar.draw(g2);
      }
   }

   public void addNewSequenceDiagram()
   {
      sequence_diagrams.add(new FP_Sequence_Diagram());
   }

}

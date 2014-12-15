import java.io.File;
import javax.sound.sampled.*;
import java.util.Random;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.JarURLConnection;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class FP_Sound_Box
{
   private AudioInputStream flap_sound_file;
   private AudioInputStream death_sound_file;
   private AudioInputStream smalltalk_sound_file;
   private AudioInputStream unary_sound_file;
   private AudioInputStream binary_sound_file;
   private AudioInputStream keyword_sound_file;
   private AudioInputStream wake_him_up_sound;
   private AudioInputStream UML_sound;
   private AudioInputStream dead_guy_sound;
   private String[] flap_sounds;
   private String[] sequence_sounds;

   private Random ran;

   public FP_Sound_Box()
   {
      //InputStream in = FP_Viewer.class.getClassLoader().getResourceAsStream("resources/panar.jpg");   

      URL sound;
      try {

         sound = getClass().getResource("resources/FP_FLAP_SOUND.wav");
         flap_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_DEATH_SOUND.wav");
         death_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_SMALLTALK.wav");
         smalltalk_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_UNARY.wav");
         unary_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_BINARY.wav");
         binary_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_KEYWORD.wav");
         keyword_sound_file = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_WAKE_HIM_UP.wav");
         wake_him_up_sound = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_UML.wav");
         UML_sound = AudioSystem.getAudioInputStream(sound);

         sound = getClass().getResource("resources/FP_DEAD_GUY.wav");
         dead_guy_sound = AudioSystem.getAudioInputStream(sound);
      } catch (UnsupportedAudioFileException | IOException e) {
         System.out.println(e);
      }

      // flap_sound_file = new File("FP_FLAP_SOUND.wav");
      // death_sound_file = new File("FP_DEATH_SOUND.wav");
      //smalltalk_sound_file = new File("FP_SMALLTALK.wav");
      /*
      unary_sound_file = new File("FP_UNARY.wav");
      binary_sound_file = new File("FP_BINARY.wav");
      keyword_sound_file = new File("FP_KEYWORD.wav");
      wake_him_up_sound = new File("FP_WAKE_HIM_UP.wav");
      UML_sound = new File("FP_UML.wav");
      dead_guy_sound = new File("FP_DEAD_GUY.wav");
      */

      flap_sounds = new String[] { "resources/FP_FLAP_SOUND.wav", "resources/FP_SMALLTALK.wav", "resources/FP_WAKE_HIM_UP.wav", "resources/FP_UML.wav", "resources/FP_DEAD_GUY.wav" };
      sequence_sounds = new String[] { "resources/FP_UNARY.wav", "resources/FP_BINARY.wav", "resources/FP_KEYWORD.wav" };
      //flap_sounds = new AudioInputStream[] {flap_sound_file };// , smalltalk_sound_file, wake_him_up_sound, UML_sound, dead_guy_sound};
      //sequence_sounds = new AudioInputStream[] { unary_sound_file, binary_sound_file, keyword_sound_file } ;
      ran = new Random(flap_sounds.length);
   }
   public void playFlap()
{
   try {
      String s;
      s = flap_sounds[ran.nextInt(flap_sounds.length)];
      playSound( AudioSystem.getAudioInputStream(getClass().getResource(s)));
   } catch (UnsupportedAudioFileException | IOException e) {
      System.out.println(e);
   }
}
public void playDeath()
{
   playSound(death_sound_file);
}
public void playSmallTalk()
{
   playSound(smalltalk_sound_file);
}
public void playUnary()
{
   playSound(unary_sound_file);
}
public void playBinary()
{
   playSound(binary_sound_file);
}
public void playKeyword()
{
   playSound(keyword_sound_file);
}
public void playSequence()
{
   try {
      String s; 
      s = sequence_sounds[ran.nextInt(sequence_sounds.length)];
      playSound( AudioSystem.getAudioInputStream(getClass().getResource(s)));
   } catch (UnsupportedAudioFileException | IOException e) {
      System.out.println(e);
   }
}

public synchronized void playSound(AudioInputStream f) {
   new Thread(new Runnable() {
      // The wrapper thread is unnecessary, unless it blocks on the
      //   // Clip finishing; see comments.
      public void run() {
         try {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = f;
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
         } catch (Exception e) {
            System.err.println(e.getMessage());
         }
      }
   }).start();
}
}

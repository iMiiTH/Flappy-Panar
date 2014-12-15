FP_Viewer.class : FP_Viewer.java FP_Frame.class 
	javac FP_Viewer.java

FP_Frame.class : FP_Frame.java FP_Component.class FP_Start_Layer_UI.class
	javac FP_Frame.java

FP_Start_Layer_UI.class : FP_Start_Layer_UI.java
	javac FP_Start_Layer_UI.java

FP_Component.class : FP_Component.java FP_Click_Listener.class FP_Panar.class FP_Sequence_Diagram.class FP_Sound_Box.class
	javac FP_Component.java

FP_Click_Listener.class : FP_Click_Listener.java
	javac FP_Click_Listener.java

FP_Panar.class : FP_Panar.java FP_Moving_Object.class
	javac FP_Panar.java

FP_Moving_Object.class : FP_Moving_Object.java
	javac FP_Moving_Object.java

FP_Sequence_Diagram.class : FP_Sequence_Diagram.java FP_Moving_Object.class
	javac FP_Sequence_Diagram.java

FP_Sound_Box.class : FP_Sound_Box.java
	javac FP_Sound_Box.java

run : FP_Viewer.class
	clear
	jar cvfm FlappyPanar.jar manifest.txt *.class resources/*
	java -jar FlappyPanar.jar

FP_Viewer.class : FP_Viewer.java FP_Frame.class 
	javac FP_Viewer.java

FP_Frame.class : FP_Frame.java FP_Component.class
	javac FP_Frame.java

FP_Component.class : FP_Component.java FP_Click_Listener.class FP_Panar.class
	javac FP_Component.java

FP_Click_Listener.class : FP_Click_Listener.java
	javac FP_Click_Listener.java

FP_Panar.class : FP_Panar.java FP_Moving_Object.class
	javac FP_Panar.java

FP_Moving_Object.class : FP_Moving_Object.java
	javac FP_Moving_Object.java

run : FP_Viewer.class
	clear
	java FP_Viewer
